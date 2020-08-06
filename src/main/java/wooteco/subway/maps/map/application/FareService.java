package wooteco.subway.maps.map.application;

import org.springframework.stereotype.Service;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.DiscountStrategy;
import wooteco.subway.maps.map.domain.FareDistanceStrategy;

import java.util.List;

@Service
public class FareService {
    protected static final int DEFAULT_FARE = 1250;

    private List<FareDistanceStrategy> fareDistanceStrategies;
    private DiscountStrategy discountStrategy;

    public FareService(List<FareDistanceStrategy> fareDistanceStrategies) {
        this.fareDistanceStrategies = fareDistanceStrategies;
        this.discountStrategy = new DiscountStrategy();
    }

    public int findFare(int distance, List<Line> lines) {
        int fare = DEFAULT_FARE
                + findAdditionalFare(distance)
                + findAdditionalFare(lines);
        return fare - discountStrategy.discountFare(fare);
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
