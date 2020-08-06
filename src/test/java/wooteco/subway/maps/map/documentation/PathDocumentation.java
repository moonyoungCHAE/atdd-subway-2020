package wooteco.subway.maps.map.documentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.web.context.WebApplicationContext;
import wooteco.subway.common.documentation.Documentation;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.domain.PathType;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.maps.map.ui.MapController;
import wooteco.subway.maps.station.dto.StationResponse;

import java.time.LocalDateTime;
import java.util.Arrays;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;


@WebMvcTest(controllers = {MapController.class})
public class PathDocumentation extends Documentation {
    @MockBean
    private MapService mapService;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
    }

    @Test
    void findPath() {
        PathResponse pathResponse = PathResponse.builder()
                .stations(Arrays.asList(
                        new StationResponse(1L, "잠실새내역", LocalDateTime.now(), LocalDateTime.now()),
                        new StationResponse(2L, "잠실역", LocalDateTime.now(), LocalDateTime.now())
                ))
                .distance(10)
                .duration(10)
                .fare(1250)
                .build();
        Mockito.when(mapService.findPath(anyLong(), anyLong(), any())).thenReturn(pathResponse);

        given().
                when().
                get("/paths?source={sourceId}&target={targetId}&type={type}",
                        1L, 2L, PathType.DISTANCE).
                then().
                log().all().
                apply(document("/paths/find",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("source").description("출발역 아이디"),
                                parameterWithName("target").description("도착역 아이디"),
                                parameterWithName("type").description("경로 유형")
                        )
                )).
                extract();
    }
}
