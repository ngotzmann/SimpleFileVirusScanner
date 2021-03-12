package net.ngotzmann.SimpleFileVirusScan.file;

import lombok.SneakyThrows;
import net.ngotzmann.SimpleFileVirusScan.virusscanner.ScanResult;
import net.ngotzmann.SimpleFileVirusScan.virusscanner.VirusScanner;
import net.ngotzmann.SimpleFileVirusScan.virusscanner.VirusScannerFactory;
import net.ngotzmann.SimpleFileVirusScan.virusscanner.VirusScannerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private VirusScannerFactory virusScannerFactory;

    @Autowired
    private VirusScannerProperties properties;

    /**
     * Validate file and check if configured virusScanner is reachable.
     * Send file to virusScanner.
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @Override
    public ScanResult isFileInfected(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is not valid!");
        }
        Optional<VirusScanner> optionalVirusScanner = virusScannerFactory.getVirusScanner(properties.getBeanName());
        if (optionalVirusScanner.isEmpty()) {
            throw new IllegalStateException("Virus scanner is not configured, set bean name in properties file");
        }
        if(!optionalVirusScanner.get().isReachable()) {
            throw new IllegalStateException("Virus scanner is not reachable");
        }
        return optionalVirusScanner.get().isFileInfected(file.getBytes());
    }
}
