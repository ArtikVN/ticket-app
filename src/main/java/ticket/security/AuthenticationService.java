package ticket.security;

import ticket.exception.AuthenticationException;
import ticket.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
