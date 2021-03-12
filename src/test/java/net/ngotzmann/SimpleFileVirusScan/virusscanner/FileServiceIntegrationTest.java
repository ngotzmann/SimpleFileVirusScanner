package net.ngotzmann.SimpleFileVirusScan.virusscanner;

import net.ngotzmann.SimpleFileVirusScan.file.FileService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
@RunWith(SpringRunner.class )
@SpringBootTest
public class FileServiceIntegrationTest {

    @Autowired
    private FileService fileService;

//    @ClassRule
//    public static DockerComposeContainer dockerCompose =
//            new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("clamav.host", () -> "127.0.0.1");
        registry.add("clamav.port", () -> 4410);
    }

//    @Before
//    public void before() {
//        dockerCompose.start();
//    }
//
//    @AfterClass
//    public static void afterClass() {
//        dockerCompose.stop();
//    }

    @Test
    public void isFileInfected_FileIsNotInfected() {
        DockerComposeContainer dockerCompose =
                new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));
        dockerCompose.start();
        MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
        ScanResult fileInfected = fileService.isFileInfected(multipartFile);
        Assert.assertFalse(fileInfected.isFileInfected());
        dockerCompose.stop();
    }

    @Test
    public void isFileInfected_FileIsInfected() {
        MultipartFile multipartFile = new MockMultipartFile("test-virus.tmp", "X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*".getBytes());
        ScanResult fileInfected = fileService.isFileInfected(multipartFile);
        Assert.assertTrue(fileInfected.isFileInfected());
    }
}