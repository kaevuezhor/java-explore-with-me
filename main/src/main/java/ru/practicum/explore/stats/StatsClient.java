package ru.practicum.explore.stats;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore.stats.dto.EndpointHitDto;
import ru.practicum.explore.stats.dto.ViewsStats;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatsClient {

    private final WebClient webClient;

    public StatsClient() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .build();
    }

    public void hit(HttpServletRequest request) {
        webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/hit").build())
                .bodyValue(
                        new EndpointHitDto(
                                0,
                                "explore-with-me",
                                request.getRequestURI(),
                                request.getRemoteAddr(),
                                LocalDateTime.now()
                        )
                )
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public long getViews(long eventId) {
        List<ViewsStats> views = getStats(LocalDateTime.now().minusYears(100), LocalDateTime.now().plusYears(100), List.of(eventId), false);
        if (views.isEmpty()) {
            return 0;
        }
        return views.get(0).getHits();
    }

    private List<ViewsStats> getStats(LocalDateTime start, LocalDateTime end, List<Long> ids, Boolean unique) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stats")
                        .queryParam("start", start)
                        .queryParam("end", end)
                        .queryParam("uris", ids.stream().map(i -> "/events/" + i).collect(Collectors.toList()))
                        .queryParam("unique", unique)
                        .build())
                .retrieve()
                .toEntityList(ViewsStats.class)
                .block()
                .getBody();
    }
}