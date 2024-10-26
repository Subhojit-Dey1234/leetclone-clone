package leetcode.clone.leetcode.constants;

import lombok.Getter;

@Getter
public enum Language {
    PYTHON("python:3.9-slim",".py", "python /app/codefile.py"),
    JAVA("openjdk:17.0.2-oracle", ".java","javac /app/codefile.java && cd app && java -ea CodeFile"),
    JAVASCRIPT("node:20.18-slim", ".js","node /app/codefile.js"),;

    private final String dockerImage;
    private final String fileExtension;
    private final String executionCommand;

    Language(String dockerImage, String fileExtension, String executionCommand) {
        this.dockerImage = dockerImage;
        this.fileExtension = fileExtension;
        this.executionCommand = executionCommand;
    }


    public static Language getLanguage(String language) {
        return switch (language) {
            case "python"-> PYTHON;
            case "java" -> JAVA;
            case "javascript" -> JAVASCRIPT;
            default -> throw new IllegalArgumentException("Unknown language: " + language);
        };
    }
}
