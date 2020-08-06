package wooteco.subway.maps.map.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wooteco.subway.members.favorite.domain.MemberFinder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FareAgeStrategyTest {
    @Mock
    public MemberFinder memberFinder;
    private FareAgeStrategy fareAgeStrategy;

    @BeforeEach
    private void setUp() {
        fareAgeStrategy = new FareAgeStrategy(10, 20,
                100, 10, memberFinder);
    }

    @DisplayName("로그인했을 때 적절한 나이의 사용자는 할인 받는다.")
    @ParameterizedTest
    @CsvSource({"9,1000,0", "10,1000,190", "19,2000,290", "20,2000,0"})
    public void fareAgeStrategyTest_discount(int age, int originFare, int expected) {
        when(memberFinder.isLogin()).thenReturn(true);
        when(memberFinder.findMemberAge()).thenReturn(age);

        assertThat(fareAgeStrategy.discountFare(originFare)).isEqualTo(expected);
    }

    @DisplayName("로그인하지 않았을 때 모든 사용자는 할인 받지 못한다.")
    @ParameterizedTest
    @CsvSource({"10,1000", "19,2000"})
    public void fareAgeStrategyTest_noDiscount(int age, int originFare) {
        when(memberFinder.isLogin()).thenReturn(false);

        assertThat(fareAgeStrategy.discountFare(originFare)).isZero();
    }
}