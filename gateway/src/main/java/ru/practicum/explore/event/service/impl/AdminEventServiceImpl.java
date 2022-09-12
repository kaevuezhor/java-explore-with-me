package ru.practicum.explore.event.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore.event.dto.AdminUpdateEventRequest;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventState;
import ru.practicum.explore.event.service.AdminEventService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminEventServiceImpl implements AdminEventService {

    private final WebClient webClient;

    private static final String API_PREFIX = "/events";

    public AdminEventServiceImpl(@Value("${explore-server-admin.url}") String serverUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(serverUrl + API_PREFIX)
                .build();
    }
    @Override
    public List<EventFullDto> getEvents(
            List<Long> users,
            List<EventState> states,
            List<Long> categories,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            int from, int size
    ) {
        return webClient.get()
                .uri(
                        uriBuilder -> uriBuilder
                                .queryParam("users", users)
                                .queryParam("states", states)
                                .queryParam("categories", categories)
                                .queryParam("rangeStart", rangeStart)
                                .queryParam("rangeEnd", rangeEnd)
                                .queryParam("from", from)
                                .queryParam("size", size)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EventFullDto>>() {
                })
                .block();
    }

    @Override
    public EventFullDto updateEvent(long eventId, AdminUpdateEventRequest adminUpdateEventRequest) {
        return null;
    }

    @Override
    public EventFullDto publishEvent(long eventId) {
        return null;
    }

    @Override
    public EventFullDto rejectEvent(long eventId) {
        return null;
    }
}
