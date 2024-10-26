package leetcode.clone.leetcode.dao;

import leetcode.clone.leetcode.repository.UserRepository;
import leetcode.clone.leetcode.response.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao implements IDao<Optional<User>> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Optional<User>> getAll() {
        List<Optional<User>> userList = new ArrayList<>();
        userRepository.findAll().forEach(user -> userList.add(Optional.of(user)));
        return userList;
    }

    @Override
    public void save(Optional<User> user) {

    }

    @Override
    public void update(Optional<User> user) {

    }

    @Override
    public void delete(Integer id) {

    }
}
