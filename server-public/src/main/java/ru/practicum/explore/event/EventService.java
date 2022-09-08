package ru.practicum.explore.event;

import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;

import java.util.List;

public interface EventService {
    List<EventShortDto> getEvents(
            String text,
            List<Integer> categories,
            Boolean paid,
            String rangeStart,
            String rangeEnd,
            boolean onlyAvailable,
            String sort,
            int from,
            int size
    );

    EventFullDto getEvent(long id);
}
