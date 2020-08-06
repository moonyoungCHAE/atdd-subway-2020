package wooteco.subway.maps.map.application;

import wooteco.subway.maps.map.domain.FareDistanceStrategy;

public class FareService {
    private static final int DEFAULT_FARE = 1250;

    private final FareDistanceStrategy fareDistanceStrategy;

    public FareService(FareDistanceStrategy fareDistanceStrategy) {
        this.fareDistanceStrategy = fareDistanceStrategy;
    }

    public int findFare(int distance) {
        int fare = DEFAULT_FARE + fareDistanceStrategy.findAdditionalFare(distance);
        return fare;
    }
}
