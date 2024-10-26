package leetcode.clone.leetcode.service;

import leetcode.clone.leetcode.dao.SubmissionDao;
import leetcode.clone.leetcode.response.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionDao submissionDao;

    public Submission getSubmissionById(Long id) {
        return submissionDao.get(id).orElse(null);
    }

    public List<Optional<Submission>> getAllSubmissions() {
        return submissionDao.getAll();
    }

    public void save(Submission submission) {
        submissionDao.save(Optional.of(submission));
    }
}
