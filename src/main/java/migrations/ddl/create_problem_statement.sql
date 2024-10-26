CREATE TABLE problem
(
    id         BIGINT PRIMARY KEY,
    fn         VARCHAR(255), -- The function name or description
    basic_code TEXT,         -- The basic code or solution
    header     VARCHAR(255),
    metadata   TEXT
);


INSERT INTO problem (id, fn, basic_code, header, metadata)
VALUES (1, 'twoSum', '', 'Sum of Two Numbers', 'Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.\nYou may assume that each input would have exactly one solution, and you may not use the same element twice.\nYou can return the answer in any order.');