package ru.practicum.explore.event.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.service.PublicEventService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {

    private final WebClient webClient;

    private static final String API_PREFIX = "/events";

    //TODO - маппинг параметров
    @Override
    public List<EventShortDto> getEvents(
            String text,
            List<Integer> categories,
            Boolean paid,
            String rangeStart,
            String rangeEnd,
            boolean onlyAvailable,
            String sort,
            int from,
            int size
    ) {
        return webClient.get().uri(
                //API_PREFIX + "?text={}&paid={}&rangeStart={}&rangeEnd={}&onlyAvailable={}&sort={}&from={}&size={}",
                API_PREFIX + "?from=0&size=10",
                        text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EventShortDto>>() {})
                .block();
    }

    @Override
    public EventFullDto getEvent(long id) {
        return webClient.get()
                .uri(API_PREFIX + "/{}", id)
                .retrieve()
                .bodyToMono(EventFullDto.class)
                .block();
    }
}
