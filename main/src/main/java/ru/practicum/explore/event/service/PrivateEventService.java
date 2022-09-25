package ru.practicum.explore.event.service;

import org.springframework.expression.AccessException;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventRequest;
import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

public interface PrivateEventService {

    List<EventShortDto> getEvents(long userId, int from, int size);

    EventFullDto patchEvent(long userId, UpdateEventRequest updateEventRequest);

    EventFullDto postEvent(long userId, NewEventDto newEventDto);

    EventFullDto getEvent(long userId, long eventId);

    EventFullDto patchEvent(long userId, long eventId) throws AccessException;

    List<ParticipationRequestDto> getEventRequests(long userId, long eventId);

    ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) throws AccessException;

    ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) throws AccessException;
}
