package net.ngotzmann.SimpleFileVirusScan.virusscanner.clamav;

import fi.solita.clamav.ClamAVClient;
import lombok.SneakyThrows;
import net.ngotzmann.SimpleFileVirusScan.virusscanner.ScanResult;
import net.ngotzmann.SimpleFileVirusScan.virusscanner.VirusScanner;

import java.nio.charset.StandardCharsets;

public class ClamAVVirusScanner implements VirusScanner {

    private final ClamAVClient clamAVClient;

    public ClamAVVirusScanner(
            String host,
            int port
    ) {
        clamAVClient = new ClamAVClient(host, port);
    }


    @SneakyThrows
    @Override
    public boolean isReachable() {
        return clamAVClient.ping();
    }

    @SneakyThrows
    @Override
    public ScanResult isFileInfected(byte[] file) {
        byte[] reply = clamAVClient.scan(file);
        if (!ClamAVClient.isCleanReply(reply)) {
            String result = new String(reply, StandardCharsets.US_ASCII);
            return new ScanResult(true, result);
        }
        return new ScanResult(false, null);
    }
}
