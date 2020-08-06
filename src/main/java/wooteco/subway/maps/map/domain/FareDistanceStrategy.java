package wooteco.subway.maps.map.domain;

import lombok.Builder;

import java.util.Optional;

public class FareDistanceStrategy {
    private static final int FREE = 0;

    private int minDistance;
    private Optional<Integer> maxDistance;
    private int section;
    private int sectionFare;

    @Builder
    public FareDistanceStrategy(int minDistance, Integer maxDistance, int section, int sectionFare) {
        this.minDistance = minDistance;
        this.maxDistance = Optional.ofNullable(maxDistance);
        this.section = section;
        this.sectionFare = sectionFare;
    }

    public int findAdditionalFare(int distance) {
        if (isNotInArrange(distance)) {
            return FREE;
        }
        distance = formatDistance(distance);
        int sectionCount = findSectionCount(distance);
        return sectionCount * sectionFare;
    }

    private boolean isNotInArrange(int distance) {
        return distance <= minDistance;
    }

    private int formatDistance(int distance) {
        if (maxDistance.isPresent() && maxDistance.get() < distance) {
            return maxDistance.get();
        }
        return distance;
    }

    private int findSectionCount(int distance) {
        distance -= minDistance;
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
