package net.ngotzmann.SimpleFileVirusScan.virusscanner.clamav;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value=ClamAVVirusScannerProperties.class)
public class ClamAVVirusScannerConfiguration {

    @Bean("clamAVVirusScanner")
    public ClamAVVirusScanner clamAVVirusScanner(ClamAVVirusScannerProperties properties) {
        return new ClamAVVirusScanner(properties.getHost(), properties.getPort());
    }
}
