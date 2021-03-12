package net.ngotzmann.SimpleFileVirusScan.virusscanner.clamav;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="clamav")
@Getter @Setter
public class ClamAVVirusScannerProperties {
    private String host = "127.0.0.1";
    private int port = 3310;
}
