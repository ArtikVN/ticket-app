package ticket.service;

import ticket.model.User;

import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
