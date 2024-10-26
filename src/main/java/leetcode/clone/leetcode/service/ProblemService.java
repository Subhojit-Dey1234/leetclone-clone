package leetcode.clone.leetcode.service;

import leetcode.clone.leetcode.constants.Language;
import leetcode.clone.leetcode.dao.ProblemDao;
import leetcode.clone.leetcode.response.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    ProblemDao problemDao;

    @Autowired
    CodeExecutionService codeExecutionService;

    private static final String JAVA_CODE = "class CodeFile {\n" +
            "    public static void main(String[] args) {\n" +
            "        for (String arg : args) {\n" +
            "            \n" +
            "        }\n" +
            "        Solution solution = new Solution();\n" +
            "    }\n" +
            "}";

    private static final String PYTHON_CODE = "for i in range(0,10):\n" +
            "    print(i)";

    private static final String JAVASCRIPT_CODE = "console.log('Hello World');";

    public Problem getProblemById(Long id) {
        return problemDao.get(id).orElse(null);
    }

    public List<Optional<Problem>> getAllProblems() {
        return problemDao.getAll();
    }

    public void submitProblem(String code, String inputParams) throws Exception {
        String d = "class Solution {\n" +
                "public int[] twoSum(int[] nums, int target) {\n" +
                "return nums;}}";
        String output = codeExecutionService.runCodeInDocker(Language.getLanguage("java"), d + "\n" + JAVA_CODE, inputParams);
        System.out.println(output);
    }
}
