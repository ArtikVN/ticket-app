package ticket.service;

import java.util.Optional;
import ticket.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
