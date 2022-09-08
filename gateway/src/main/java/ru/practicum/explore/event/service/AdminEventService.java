package ru.practicum.explore.event.service;

import ru.practicum.explore.event.dto.AdminUpdateEventRequest;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventState;

import java.util.List;

public interface AdminEventService {

    List<EventFullDto> getEvents(
            List<Long> users,
            List<EventState> states,
            List<Long> categories,
            String rangeStart,
            String rangeEnd,
            int from,
            int size
    );

    EventFullDto updateEvent(long eventId, AdminUpdateEventRequest adminUpdateEventRequest);

    EventFullDto publishEvent(long eventId);

    EventFullDto rejectEvent(long eventId);
}
