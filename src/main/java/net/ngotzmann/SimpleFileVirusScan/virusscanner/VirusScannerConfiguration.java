package net.ngotzmann.SimpleFileVirusScan.virusscanner;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(VirusScannerProperties.class)
public class VirusScannerConfiguration {

    @Autowired
    private VirusScannerProperties virusScannerProperties;
}
