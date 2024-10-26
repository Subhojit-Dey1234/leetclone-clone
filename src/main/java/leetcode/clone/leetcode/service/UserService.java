package leetcode.clone.leetcode.service;

import leetcode.clone.leetcode.dao.UserDao;
import leetcode.clone.leetcode.response.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserDao userDao;

    public Optional<User> getUserById(Long id) {
        return userDao.get(id);
    }

    public List<Optional<User>> getAllUsers() {
        return userDao.getAll();
    }
}
