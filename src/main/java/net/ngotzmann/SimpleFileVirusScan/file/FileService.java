package net.ngotzmann.SimpleFileVirusScan.file;

import net.ngotzmann.SimpleFileVirusScan.virusscanner.ScanResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ScanResult isFileInfected(MultipartFile file);
}
