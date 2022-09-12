package ru.practicum.explore.event.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.EventSort;
import ru.practicum.explore.event.service.PublicEventService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {

    private final WebClient webClient;

    private static final String API_PREFIX = "/events";

    @Autowired
    public PublicEventServiceImpl(@Value("${explore-server-public.url}") String serverUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(serverUrl + API_PREFIX)
                .build();
    }

    @Override
    public List<EventShortDto> getEvents(
            String text,
            List<Long> categories,
            Boolean paid,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            boolean onlyAvailable,
            EventSort sort,
            int from,
            int size
    ) {
        return webClient.get()
                .uri(
                uriBuilder -> uriBuilder
                        .queryParam("text", text)
                        .queryParam("categories", categories)
                        .queryParam("paid", paid)
                        .queryParam("rangeStart", rangeStart)
                        .queryParam("rangeEnd", rangeEnd)
                        .queryParam("onlyAvailable", onlyAvailable)
                        .queryParam("sort", sort)
                        .queryParam("from", from)
                        .queryParam("size", size)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EventShortDto>>() {})
                .block();
    }

    @Override
    public EventFullDto getEvent(long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{id}")
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EventFullDto.class)
                .block();
    }
}
