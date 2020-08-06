package wooteco.subway.maps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import wooteco.subway.maps.map.domain.FareAgeStrategy;
import wooteco.subway.maps.map.domain.FareDistanceStrategy;

@Component
public class FareStrategyConfiguration {
    @Bean
    public FareDistanceStrategy fareDistanceStrategy_5km() {
        return FareDistanceStrategy.builder()
                .minDistance(10)
                .maxDistance(50)
                .section(5)
                .sectionFare(100)
                .build();
    }

    @Bean
    public FareDistanceStrategy fareDistanceStrategy_8km() {
        return FareDistanceStrategy.builder()
                .minDistance(50)
                .section(8)
                .sectionFare(100)
                .build();
    }

    @Bean
    public FareAgeStrategy fareAgeStrategy_youth() {
        return FareAgeStrategy.builder()
                .minAge(13)
                .maxAge(19)
                .basicDiscount(350)
                .discountPercentage(20)
                .build();
    }

    @Bean
    public FareAgeStrategy fareAgeStrategy_kid() {
        return FareAgeStrategy.builder()
                .minAge(6)
                .maxAge(13)
                .basicDiscount(350)
                .discountPercentage(50)
                .build();
    }
}
