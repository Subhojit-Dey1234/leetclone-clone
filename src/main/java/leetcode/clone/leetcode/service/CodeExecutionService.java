package leetcode.clone.leetcode.service;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import jakarta.annotation.PostConstruct;
import leetcode.clone.leetcode.constants.Language;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class CodeExecutionService {

    private static final String APP_FOLDER = "app";
    private static final String FOLDER_PATH = new File(APP_FOLDER).getAbsolutePath();
    private static final String SLASH = "/";
    private static final String FILE_NAME = "codefile";

    private final DockerClient dockerClient;
    public CodeExecutionService() {
        DefaultDockerClientConfig defaultDockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

        DockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(defaultDockerClientConfig.getDockerHost())
                .sslConfig(defaultDockerClientConfig.getSSLConfig())
                .build();

        this.dockerClient = DockerClientBuilder.getInstance(defaultDockerClientConfig)
                .withDockerHttpClient(dockerHttpClient)
                .build();
    }

    @PostConstruct
    public void init() throws InterruptedException {
        log.info("Pulling docker images.");
        dockerClient.pullImageCmd(Language.PYTHON.getDockerImage()).exec(new PullImageResultCallback()).awaitCompletion();
        log.info("Docker image {} successfully pulled.", Language.PYTHON.getDockerImage());

        dockerClient.pullImageCmd(Language.JAVA.getDockerImage()).exec(new PullImageResultCallback()).awaitCompletion();
        log.info("Docker image {} successfully pulled.", Language.JAVA.getDockerImage());

        dockerClient.pullImageCmd(Language.JAVASCRIPT.getDockerImage()).exec(new PullImageResultCallback()).awaitCompletion();
        log.info("Docker image {} successfully pulled.", Language.JAVASCRIPT.getDockerImage());
    }

    public String runCodeInDocker(Language language, String code, String input) throws Exception {
        String imageName = language.getDockerImage();
        String uniqueId = UUID.randomUUID().toString();

        dockerClient.pullImageCmd(imageName).exec(new PullImageResultCallback()).awaitCompletion();
        writeToFile(uniqueId, language.getFileExtension(), code);

        Volume volume = new Volume("/app");
        HostConfig hostConfig = HostConfig.newHostConfig().withBinds(
                new Bind(FOLDER_PATH + SLASH + uniqueId, volume)
        );

        CreateContainerResponse container = dockerClient
                .createContainerCmd(imageName)
                .withHostConfig(hostConfig)
                .withCmd("sh", "-c", language.getExecutionCommand() + " " + input)
                .exec();

        dockerClient.startContainerCmd(container.getId()).exec();
        dockerClient.logContainerCmd(container.getId())
                .withStdOut(true)
                .withStdErr(true)
                .withFollowStream(true)
                .exec(new DockerLogHandler());

        dockerClient.stopContainerCmd(container.getId()).exec();
        dockerClient.removeContainerCmd(container.getId()).exec();

        return DockerLogHandler.output.toString().trim();
    }

    private static void writeToFile(final String uniqueId,String pathEx, final String context) throws IOException {
        String folderPath = APP_FOLDER + SLASH + uniqueId;
        Path folder = Paths.get(folderPath);

        Files.createDirectories(folder);

        File file = new File(folderPath + SLASH + FILE_NAME + pathEx);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(context);
            log.info("File Create and content written successfully");
        }
    }
}
