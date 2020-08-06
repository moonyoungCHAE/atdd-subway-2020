package wooteco.subway.maps.map.domain;

import lombok.Builder;

public class FareDistanceStrategy {
    private static final int FREE = 0;

    private int startDistance;
    private int endDistance;
    private int section;
    private int sectionFare;

    @Builder
    public FareDistanceStrategy(int startDistance, int endDistance, int section, int sectionFare) {
        this.startDistance = startDistance;
        this.endDistance = endDistance;
        this.section = section;
        this.sectionFare = sectionFare;
    }

    public int findAdditionalFare(int distance) {
        if (isNotInArrange(distance)) {
            return FREE;
        }
        int sectionCount = findSectionCount(distance);
        return sectionCount * sectionFare;
    }

    private boolean isNotInArrange(int distance) {
        return distance <= startDistance || distance > endDistance;
    }

    private int findSectionCount(int distance) {
        distance -= startDistance;
        int sectionCount = distance / section;
        if (hasRemainder(distance)) {
            return sectionCount + 1;
        }
        return sectionCount;
    }

    private boolean hasRemainder(int distance) {
        return distance % section > 0;
    }
}
