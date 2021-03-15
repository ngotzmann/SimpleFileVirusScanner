package net.ngotzmann.SimpleFileVirusScan.virusscanner;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import net.ngotzmann.SimpleFileVirusScan.file.FileService;
import org.junit.jupiter.api.Test;


@Testcontainers
@SpringBootTest
public class FileServiceIntegrationTest {

    @Container
    static GenericContainer<?> container =
            new GenericContainer<>(DockerImageName.parse("mkodockx/docker-clamav:alpine"));//.withExposedPorts(3310);

    @Autowired
    private FileService fileService;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        Integer mappedPort = container.getMappedPort(3310);
        registry.add("clamav.host", () -> "0.0.0.0");
        registry.add("clamav.port", () -> mappedPort);
    }

    @Test
    public void hisFileInfected_FileIsNotInfected() {
        MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
        ScanResult fileInfected = fileService.isFileInfected(multipartFile);
        Assert.assertFalse(fileInfected.isFileInfected());
    }

    @Test
    public void isFileInfected_FileIsInfected() {
        MultipartFile multipartFile = new MockMultipartFile("test-virus.tmp", "X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*".getBytes());
        ScanResult fileInfected = fileService.isFileInfected(multipartFile);
        Assert.assertTrue(fileInfected.isFileInfected());
    }
}