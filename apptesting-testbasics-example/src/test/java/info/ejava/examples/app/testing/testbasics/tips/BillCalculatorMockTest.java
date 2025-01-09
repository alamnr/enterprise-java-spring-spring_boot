package info.ejava.examples.app.testing.testbasics.tips;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.and;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Add mockito extension to JUnit
@Tag("tips")
public class BillCalculatorMockTest {
// Subject under test 

    private BillCalculator billCalculator;

    @Mock
    private TipCalculator tipCalculatorMock; // Identify which interfaces to mock

    @BeforeEach
    void init() {
        // we are manually wiring up the subject under test
        // act as acontainer 
        billCalculator = new BillCalculatorImpl(tipCalculatorMock);
    }

    @Test
    public void calc_shares_for_people_including_tip() {
        // given
        BigDecimal billTotal = new BigDecimal(100.0);
        ServiceQuality service = ServiceQuality.GOOD;
        BigDecimal tip = billTotal.multiply(new BigDecimal(0.18));
        int numPeople = 4;

        // configure mock
        // configuring response behavior of mock
        given(tipCalculatorMock.calcTip(billTotal, service)).willReturn(tip);

        // When - call method under test
        BigDecimal shareResult = billCalculator.calcShares(billTotal, service, numPeople);

        // then tipCalculator should be called once to get result
        then(tipCalculatorMock).should(times(1)).calcTip(billTotal, service);

        //verify correct result
        BigDecimal expectedShare = billTotal.add(tip).divide(new BigDecimal(numPeople));
        and.then(shareResult).isEqualTo(expectedShare);

    }

}
