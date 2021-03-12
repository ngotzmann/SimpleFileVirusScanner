package net.ngotzmann.SimpleFileVirusScan.virusscanner;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="virusscanner")
@Getter @Setter
public class VirusScannerProperties {
    private String beanName = "clamAVVirusScanner";
}
