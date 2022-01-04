package ticket.service.impl;

import ticket.dao.UserDao;
import ticket.lib.Inject;
import ticket.lib.Service;
import ticket.model.User;
import ticket.service.UserService;
import ticket.util.HashUtil;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}