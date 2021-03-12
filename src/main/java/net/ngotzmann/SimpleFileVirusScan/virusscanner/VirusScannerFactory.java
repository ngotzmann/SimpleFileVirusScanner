package net.ngotzmann.SimpleFileVirusScan.virusscanner;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Factory to return exists beans in ApplicationContext of {@link VirusScanner()} implementaions.
 */
@Component
@AllArgsConstructor
public class VirusScannerFactory {

    @Autowired
    private ApplicationContext ctx;

    /**
     * Give a beanName of an exist bean which implements {@link VirusScanner()} and expect the return of this.
     * You can set the bean name dynamiclly at a application propertie, see {@link VirusScannerProperties}
     *
     * @param virusScannerBeanName
     * @return {@link VirusScanner()}
     */
    public Optional<VirusScanner> getVirusScanner(String virusScannerBeanName) {
        VirusScanner virusScanner = (VirusScanner)ctx.getBean(virusScannerBeanName);
        Optional<VirusScanner> optionalVirusScanner = Optional.ofNullable(virusScanner);
        return optionalVirusScanner;
    }
}
