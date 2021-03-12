package net.ngotzmann.SimpleFileVirusScan.virusscanner;

/**
 * Strategy for virusScanner, implement these interface for your virusScanner
 * You can set the virusScanner in the application properties see {@link VirusScannerProperties}
 */
public interface VirusScanner {

    boolean isReachable();

    ScanResult isFileInfected(byte[] file);

}
