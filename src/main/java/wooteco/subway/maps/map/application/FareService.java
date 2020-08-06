package wooteco.subway.maps.map.application;

import org.springframework.stereotype.Service;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.FareAgeStrategy;
import wooteco.subway.maps.map.domain.FareDistanceStrategy;

import java.util.List;

@Service
public class FareService {
    protected static final int DEFAULT_FARE = 1250;

    private List<FareDistanceStrategy> fareDistanceStrategies;
    private List<FareAgeStrategy> fareAgeStrategies;

    public FareService(List<FareDistanceStrategy> fareDistanceStrategies,
                       List<FareAgeStrategy> fareAgeStrategies) {
        this.fareDistanceStrategies = fareDistanceStrategies;
        this.fareAgeStrategies = fareAgeStrategies;
    }

    public int findFare(int distance, List<Line> lines) {
        int fare = findAdditionalFare(distance, lines);
        int discount = fareAgeStrategies.stream()
                .mapToInt(strategy -> strategy.discountFare(fare))
                .sum();
        return fare - discount;
    }

    private int findAdditionalFare(int distance, List<Line> lines) {
        return DEFAULT_FARE
                + findAdditionalFare(distance)
                + findAdditionalFare(lines);
    }

    private int findAdditionalFare(int distance) {
        return fareDistanceStrategies.stream()
                .mapToInt(strategy -> strategy.findAdditionalFare(distance))
                .sum();
    }

    private int findAdditionalFare(List<Line> lines) {
        return lines.stream()
                .map(Line::getExtraFare)
                .max(Integer::compareTo)
                .orElse(0);
    }
}
