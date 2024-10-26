package leetcode.clone.leetcode.controller;


import leetcode.clone.leetcode.response.CodeSnippets;
import leetcode.clone.leetcode.response.Problem;
import leetcode.clone.leetcode.response.Submission;
import leetcode.clone.leetcode.response.User;
import leetcode.clone.leetcode.service.ProblemService;
import leetcode.clone.leetcode.service.SubmissionService;
import leetcode.clone.leetcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/api", "/api/"})
public class MainController {

    @Autowired
    ProblemService problemService;

    @Autowired
    UserService userService;

    @Autowired
    SubmissionService submissionService;

    @GetMapping
    public String getWhetherServiceIsRunning() {
        return "Service is running";
    }

    @GetMapping({"/problem/{id}", "/problem/{id}/"})
    public Problem getProblemById(@PathVariable("id") Long id) {
        return problemService.getProblemById(id);
    }

    @GetMapping({"/submission/{id}", "/submission/{id}/"})
    public Submission getSubmissionById(@PathVariable("id") Long id) {
        return submissionService.getSubmissionById(id);
    }

    @GetMapping({"/problem/all", "/problem/all/"})
    public List<Problem> getProblemList() {

        List<Optional<Problem>> problems = problemService.getAllProblems();
        List<Problem> allProblems = new ArrayList<>();
        problems.forEach(problem -> {
            problem.ifPresent(allProblems::add);
        });
        return allProblems;
    }

    @GetMapping({"/submission/all", "/submission/all/"})
    public List<Submission> getAllSubmissionList() {
        List<Optional<Submission>> submissions = submissionService.getAllSubmissions();
        List<Submission> allSubmissions = new ArrayList<>();
        submissions.forEach(submission -> {
            submission.ifPresent(allSubmissions::add);
        });
        return allSubmissions;
    }

    @GetMapping({"/submission/"})
    public void getSubmissionList() throws Exception {
        Problem problem = problemService.getProblemById(1L);
        CodeSnippets codeSnippets = problem.getCodeSnippets().get(0);
        String code = codeSnippets.getCode();
        String inputParams = String.join("\t",problem.getTestCases().get(0).getInputParams().split("\n"));

        problemService.submitProblem(code, inputParams);
    }

    @PostMapping({"/submission/"})
    public void addSubmission(@RequestBody Submission submission) {
        submissionService.save(submission);
    }



    @GetMapping({"/users/{id}", "/users/{id}/"})
    public User getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.orElse(null);
    }

    @GetMapping({"/users/all", "/users/all/"})
    public List<User> getAllUsers() {
        List<Optional<User>> users = userService.getAllUsers();
        List<User> allUsers = new ArrayList<>();
         users.forEach(user -> {
             user.ifPresent(allUsers::add);
        });

        return allUsers;
    }
}