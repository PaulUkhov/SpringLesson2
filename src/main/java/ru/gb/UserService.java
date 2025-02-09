package ru.gb;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void deleteById(int id) throws SQLException {
        userRepository.deleteById(id);

    }

    public Optional<User> getUser(int id) throws SQLException {
        return userRepository.getById(id);
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

}
