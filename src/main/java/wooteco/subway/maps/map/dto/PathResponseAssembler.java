package wooteco.subway.maps.map.dto;

import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.station.domain.Station;
import wooteco.subway.maps.station.dto.StationResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathResponseAssembler {
    public static PathResponse assemble(SubwayPath subwayPath, Map<Long, Station> stations, int fare) {
        List<StationResponse> stationResponses = subwayPath.extractStationId().stream()
                .map(it -> StationResponse.of(stations.get(it)))
                .collect(Collectors.toList());

        int distance = subwayPath.calculateDistance();

        return PathResponse.builder()
                .stations(stationResponses)
                .duration(subwayPath.calculateDuration())
                .distance(distance)
                .fare(fare)
                .build();
    }
}
