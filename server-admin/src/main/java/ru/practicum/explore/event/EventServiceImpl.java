package ru.practicum.explore.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.event.dto.AdminUpdateEventRequest;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventState;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventFullDto> getEvents(List<Long> users, List<EventState> states, List<Long> categories, String rangeStart, String rangeEnd, int from, int size) {
        return null;
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
