package wooteco.subway.maps.map.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wooteco.subway.maps.map.domain.FareDistanceStrategy;

import static org.assertj.core.api.Assertions.assertThat;

public class FareServiceTest {
    private FareService fareService;
    private FareDistanceStrategy fareDistanceStrategy;

    @BeforeEach
    private void setUp() {
        fareDistanceStrategy = FareDistanceStrategy.builder()
                .startDistance(10)
                .endDistance(50)
                .section(5)
                .sectionFare(100)
                .build();
        fareService = new FareService(fareDistanceStrategy);
    }

    @DisplayName("거리를 기준으로 요금을 계산한다. (5km 마다 부과되는 범위)")
    @ParameterizedTest
    @CsvSource({"10,1250", "11,1350", "15,1350", "16,1450", "50,2050"})
    void findPathTest_5km(int distance, int expected) {
        int actual = fareService.findFare(distance);
        assertThat(actual).isEqualTo(expected);
    }
}

