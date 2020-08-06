package wooteco.subway.maps.map.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.FareDistanceStrategy;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class FareServiceTest {
    private FareService fareService;

    @BeforeEach
    private void setUp() {
        FareDistanceStrategy fareDistanceStrategy_5km = FareDistanceStrategy.builder()
                .minDistance(10)
                .maxDistance(50)
                .section(5)
                .sectionFare(100)
                .build();
        FareDistanceStrategy fareDistanceStrategy_8km = FareDistanceStrategy.builder()
                .minDistance(50)
                .section(8)
                .sectionFare(100)
                .build();
        fareService = new FareService(
                Arrays.asList(fareDistanceStrategy_5km, fareDistanceStrategy_8km)
        );
    }

    @DisplayName("거리를 기준으로 요금을 계산한다. (5km 마다 부과되는 범위)")
    @ParameterizedTest
    @CsvSource({"10,1250", "11,1350", "15,1350", "16,1450", "50,2050"})
    void findFareTest_5km(int distance, int expected) {
        int actual = fareService.findFare(distance, Collections.emptyList());
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("거리를 기준으로 요금을 계산한다. (8km 마다 부과되는 범위)")
    @ParameterizedTest
    @CsvSource({"51,2150", "58,2150", "59,2250"})
    void findFareTest_8km(int distance, int expected) {
        int actual = fareService.findFare(distance, Collections.emptyList());
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("추가 요금이 있는 노선의 요금을 계산한다.")
    @Test
    void findFareTest_line() {
        Line line_2 = Line.builder()
                .name("2호선")
                .extraFare(100)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now())
                .intervalTime(10)
                .color("Green")
                .build();
        Line line_bundang = Line.builder()
                .name("분당선")
                .extraFare(900)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now())
                .intervalTime(10)
                .color("Gold")
                .build();

        int expected = fareService.DEFAULT_FARE + Math.max(line_2.getExtraFare(), line_bundang.getExtraFare());
        int actual = fareService.findFare(0, Arrays.asList(line_2, line_bundang));
        assertThat(actual).isEqualTo(expected);
    }
}

