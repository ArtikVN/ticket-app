package ticket.dao;

import java.util.Optional;
import ticket.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
