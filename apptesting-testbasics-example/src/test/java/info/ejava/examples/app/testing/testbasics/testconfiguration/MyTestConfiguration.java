package info.ejava.examples.app.testing.testbasics.testconfiguration;

//@Configuration //picked up in standard component scan
import java.math.BigDecimal;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import info.ejava.examples.app.testing.testbasics.tips.ServiceQuality;
import info.ejava.examples.app.testing.testbasics.tips.TipCalculator;

//@Configuration //picked up in standard component scan
@TestConfiguration(proxyBeanMethods = false) //skipped in component scan -- must be manually identified
public class MyTestConfiguration {

    @Bean
    public TipCalculator standardTippingImpl() {
        return new TipCalculator() {
            @Override
            public BigDecimal calcTip(BigDecimal amount, ServiceQuality serviceQuality) {
                return BigDecimal.ZERO;
            }
        };
    }
}
