CREATE TABLE testcases
(
    id              BIGINT PRIMARY KEY,
    explanation     TEXT,         -- Detailed explanation of the test case
    input_params    TEXT,         -- Input parameters for the test case
    expected_output TEXT,         -- The expected output for the test case
    problem_id      BIGINT,       -- Foreign key reference to the Problem table
    CONSTRAINT fk_problem_testcases
        FOREIGN KEY (problem_id) REFERENCES problem (id)
            ON DELETE CASCADE
);


INSERT INTO testcases (id, explanation, input_params, expected_output, execution_code, problem_id)
VALUES
(1, 'Because nums[0] + nums[1] == 9, we return [0, 1].', '[2,7,11,15]\n9','[0,1]', 1),
(2, '', '[3,2,4]\n6','[1,2]', 1),
(3, '', '[3,3]\n6','[0,1]', 1);