package info.ejava.examples.app.testing.testbasics.tips;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.and;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BillCalculatorImplTest {

    @Mock
    private TipCalculator tipCalculatorMock;

    // Mockito is instanting this implementation class for us an injecting Mocks
    // Instantiate and inject out subject under test
    @InjectMocks
    private BillCalculatorImpl billCalculator;

    @Test
    public void calc_shares_for_people_including_tip() {
        //given - we have a bill for 4 people and tip calculator that returns amount for tip
        BigDecimal billTotal = new BigDecimal(100.0);
        ServiceQuality service = ServiceQuality.GOOD;
        BigDecimal tip = new BigDecimal(50.00); //50% tip!!!
        int numPeople = 4;

        //configure mock
        given(tipCalculatorMock.calcTip(billTotal, service)).willReturn(tip);

        //when - call method under test
        BigDecimal result = billCalculator.calcShares(billTotal, service, numPeople);

        //then - verify tip calculator called once to get result
        then(tipCalculatorMock).should(times(1)).calcTip(billTotal, service);
        //and then verify correct result
        BigDecimal expectedShare = billTotal.add(tip).divide(new BigDecimal(numPeople));
        and.then(result).isEqualTo(expectedShare);

    }

}
