package ru.practicum.explore.event.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explore.event.service.PrivateEventService;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventRequest;

import java.util.List;

@Service
public class PrivateEventServiceImpl implements PrivateEventService {
    @Override
    public List<EventShortDto> getEvents(long userId, int from, int size) {
        return null;
    }

    @Override
    public EventFullDto patchEvent(long userId, UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public EventFullDto postEvent(long userId, NewEventDto newEventDto) {
        return null;
    }

    @Override
    public EventFullDto getEvent(long userId, long eventId) {
        return null;
    }

    @Override
    public EventFullDto patchEvent(long userId, long eventId, UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getEventRequests(long userId, long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) {
        return null;
    }

    @Override
    public ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) {
        return null;
    }
}
