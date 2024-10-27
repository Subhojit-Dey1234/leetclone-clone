CREATE TABLE code_snippets
(
    id       BIGINT PRIMARY KEY,
    lang     VARCHAR(255),
    code     VARCHAR(255),
    lang_slug VARCHAR(255),
    execution_code  TEXT,
    problem_id  BIGINT,
    CONSTRAINT fk_code_snippet
        FOREIGN KEY (problem_id) REFERENCES problem (id)
            ON DELETE CASCADE
);


INSERT INTO code_snippets (id, lang, code, lang_slug, execution_code, problem_id)
VALUES
    (1, 'Java', 'class Solution {\n    public int[] twoSum(int[] nums, int target) {\n        \n    }\n}','java', '', 1),
    (2, 'Python3', 'class Solution:\n    def twoSum(self, nums: List[int], target: int):\n        ','python3', 'solution = Solution()\nsolution.twoSum', 1),
    (3, 'JavaScript', '/**\n * @param {number[]} nums\n * @param {number} target\n * @return {number[]}\n */\nvar twoSum = function(nums, target) {\n    \n};','javascript', '', 1)
;

