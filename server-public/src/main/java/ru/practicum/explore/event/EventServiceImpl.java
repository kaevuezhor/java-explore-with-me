package ru.practicum.explore.event;

import org.springframework.stereotype.Service;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

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
        return List.of(new EventShortDto("loh", null, 1, null, 1, null, true, "loh", 1));
    }

    @Override
    public EventFullDto getEvent(long id) {
        return null;
    }
}
