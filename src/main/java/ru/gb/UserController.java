package ru.gb;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/users")
public class UserController {

//   REST
//   GET /users/{id} => 404 (Not Found)
//   GET /users?nameLike='Igor%' => 204 (No Content)
//
//   DELETE /users/{id} - удаляет
//   POST   /users/      {"id": "1", "name": "newName"} - создает
//   PUT    /users/{id}  {"id": "1", "name": "newName"} - изменение
//
//   HTTP HyperText Transfer Protocol
//   GET POST PUT PATCH DELETE ... HEAD OPTIONS
//
//   http://ip-address/users/all -> List<User>
//   http://ip-address/users/1 -> User(1, Igor)
//   http://ip-address/users?name=Igor -> User(1, Igor)

    private final UserRepository repository;
    private final UserService userService;
    private final User user;

    public UserController(UserRepository repository, UserService userService, User user) {
        this.repository = repository;
        this.userService = userService;
        this.user = user;
    }

    @GetMapping("/test") // Убрали consumes, так как метод возвращает HTML-текст
    public String test() {
        return """
                <h1>Title</h1>
                """;
    }


    //  @RequestMapping(path = "/users", method = RequestMethod.GET)
    @GetMapping("/all")
    public List<User> getUsers() {
        return repository.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public Optional<User> getUser(@PathVariable long id) {
        return repository.getById(id);
    }

    @GetMapping
    public User getUserByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @PatchMapping("/{id}")
    public User updateUser( @RequestBody User user) {
         repository.updateUser(user);
        return user;
    }

    @GetMapping("user-delete/{id}")
    public void deleteUser(@PathVariable("id") int id) throws SQLException {
        userService.deleteById(id);
    }

    @GetMapping("/user-update/{id}")
    public Optional<User> updateWeb(@PathVariable("id") int id) throws SQLException {
        return userService.getUser(id);
    }
    @PutMapping("/user-update")
   public User getOne(@RequestBody User updateUser){
       return userService.updateUser(updateUser);

    }
}
