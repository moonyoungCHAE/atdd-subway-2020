package wooteco.subway.maps.map.application;

import wooteco.subway.maps.map.domain.FareDistanceStrategy;

public class FareService {
    private static final int DEFAULT_FARE = 1250;

    private final FareDistanceStrategy fareDistanceStrategy_5km;
    private final FareDistanceStrategy fareDistanceStrategy_8km;

    public FareService(FareDistanceStrategy fareDistanceStrategy_5km,
                       FareDistanceStrategy fareDistanceStrategy_8km) {
        this.fareDistanceStrategy_5km = fareDistanceStrategy_5km;
        this.fareDistanceStrategy_8km = fareDistanceStrategy_8km;
    }

    public int findFare(int distance) {
        int fare = DEFAULT_FARE;
        fare += fareDistanceStrategy_5km.findAdditionalFare(distance);
        fare += fareDistanceStrategy_8km.findAdditionalFare(distance);
        return fare;
    }
}
