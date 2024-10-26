package leetcode.clone.leetcode.dao;

import leetcode.clone.leetcode.repository.ProblemRepository;
import leetcode.clone.leetcode.response.Problem;
import leetcode.clone.leetcode.response.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
For getting data from the database and update the data.
 */
@Component
public class ProblemDao implements IDao<Optional<Problem>> {

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public Optional<Problem> get(Long id) {
        return problemRepository.findById(id);
    }

    @Override
    public List<Optional<Problem>> getAll() {
        List<Optional<Problem>> problemList = new ArrayList<>();
        problemRepository.findAll().forEach(problem -> problemList.add(Optional.of(problem)));
        return problemList;
    }

    @Override
    public void save(Optional<Problem> problem) {

    }

    @Override
    public void update(Optional<Problem> problem) {

    }

    @Override
    public void delete(Integer id) {

    }
}
