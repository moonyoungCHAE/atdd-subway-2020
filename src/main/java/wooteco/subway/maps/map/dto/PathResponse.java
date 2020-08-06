package wooteco.subway.maps.map.dto;

import lombok.Builder;
import lombok.Getter;
import wooteco.subway.maps.station.dto.StationResponse;

import java.util.List;

@Getter
public class PathResponse {
    private List<StationResponse> stations;
    private int duration;
    private int distance;
    private int fare;

    public PathResponse() {
    }

    public PathResponse(List<StationResponse> stations, int duration, int distance) {
        this.stations = stations;
        this.duration = duration;
        this.distance = distance;
    }

    @Builder
    public PathResponse(List<StationResponse> stations, int duration, int distance, int fare) {
        this.stations = stations;
        this.duration = duration;
        this.distance = distance;
        this.fare = fare;
    }
}
