package leetcode.clone.leetcode.dao;

import leetcode.clone.leetcode.repository.SubmissionRepository;
import leetcode.clone.leetcode.response.Problem;
import leetcode.clone.leetcode.response.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionDao implements IDao<Optional<Submission>> {

    @Autowired
    SubmissionRepository submissionRepository;

    @Override
    public Optional<Submission> get(Long id) {
        return submissionRepository.findById(id);
    }

    @Override
    public List<Optional<Submission>> getAll() {
        List<Optional<Submission>> submissionList = new ArrayList<>();
        submissionRepository.findAll().forEach(problem -> submissionList.add(Optional.of(problem)));
        return submissionList;
    }

    @Override
    public void save(Optional<Submission> submission) {
        submission.ifPresent(value -> submissionRepository.save(value));
    }

    @Override
    public void update(Optional<Submission> submission) {

    }

    @Override
    public void delete(Integer id) {

    }
}
