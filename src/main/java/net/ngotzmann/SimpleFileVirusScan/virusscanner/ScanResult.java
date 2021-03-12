package net.ngotzmann.SimpleFileVirusScan.virusscanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ScanResult {

    private boolean isFileInfected;

    private String virusDetails;

}
