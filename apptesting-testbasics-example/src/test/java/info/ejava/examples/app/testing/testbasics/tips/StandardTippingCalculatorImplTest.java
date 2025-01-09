package info.ejava.examples.app.testing.testbasics.tips;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("tips")
public class StandardTippingCalculatorImplTest {

    //  Subject under test
    private TipCalculator tipCalculator;

    // BeforeEach plays a role of a container - wiring up objects under test
    @BeforeEach
    void setUp() {
        // simulating a complex  initialization
        tipCalculator = new StandardTippingImpl();
    }

    @Test
    public void given_fair_service() {
        //given - a $100 bill with FAIR service
        BigDecimal billTotal = new BigDecimal(100);
        ServiceQuality serviceQuality = ServiceQuality.FAIR;

        //when - calculating tip
        BigDecimal resultTip = tipCalculator.calcTip(billTotal, serviceQuality);

        //then - expect a result that is 15% of the $100 total
        BigDecimal expectedTip = billTotal.multiply(BigDecimal.valueOf(0.15));
        then(resultTip).isEqualTo(expectedTip); // Assertj assertion with BDD syntax
    }

}
