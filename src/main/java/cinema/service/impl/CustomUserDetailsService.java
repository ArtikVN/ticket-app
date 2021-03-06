package cinema.service.impl;

import cinema.model.User;
import cinema.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Can't find user by email " + email));
        org.springframework.security.core.userdetails.User
                .UserBuilder userBuilder = org.springframework.security.core
                .userdetails.User.withUsername(email);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRoles().stream()
                .map(role -> role.getName().name())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
