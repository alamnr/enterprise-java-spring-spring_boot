package info.ejava.examples.app.testing.testbasics.tips;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.and;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BillCalculatorImpl.class)
@ActiveProfiles("test")
@Tag("springboot")
@Tag("tips")
public class BillCalculatorMockNTest {

    // Subject under test
    @Autowired
    private BillCalculator billCalculator;

    @MockBean // will satisfy Autowired injection point within BillCalculatorImpl
    private TipCalculator tipCalculatorMock;

    @Test
    public void calc_shares_for_people_including_tip() {
        //given - we have a bill for 4 people and tip calculator that returns tip amount
        BigDecimal billTotal = new BigDecimal(100.0);
        ServiceQuality service = ServiceQuality.GOOD;
        BigDecimal tip = billTotal.multiply(new BigDecimal(0.18));
        int numPeople = 4;
        //configure mock
        //instruct the Mockito mock to return a tip result
        given(tipCalculatorMock.calcTip(billTotal, service)).willReturn(tip);

        //when - call method under test
        // call method on subject under test
        BigDecimal shareResult = billCalculator.calcShares(billTotal, service, numPeople);

        //then - tip calculator should be called once to get result
        // verify mock was invoked N times with the value of the bill and service
        then(tipCalculatorMock).should(times(1)).calcTip(billTotal, service);

        //verify correct result
        // verify with AssertJ that the resulting share value was the expected share value
        BigDecimal expectedShare = billTotal.add(tip).divide(new BigDecimal(numPeople));
        and.then(shareResult).isEqualTo(expectedShare);
    }

}
