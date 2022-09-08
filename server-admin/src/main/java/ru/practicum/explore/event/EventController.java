package ru.practicum.explore.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.AdminUpdateEventRequest;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventState;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    //Поиск событий
    @GetMapping
    public List<EventFullDto> getEvents(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<EventState> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info(
                "Get events users={}, states={}, categories={}, rangeStart={}, rangeEnd={}, from={}, size={}",
                users, states, categories, rangeStart, rangeEnd, from, size
        );
        return eventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    //Редактирование события
    @PutMapping("/{eventId}")
    public EventFullDto updateEvent(
            @PathVariable long eventId,
            @RequestBody AdminUpdateEventRequest adminUpdateEventRequest
    ) {
        log.info("Update event id={}, update={}", eventId, adminUpdateEventRequest);
        return eventService.updateEvent(eventId, adminUpdateEventRequest);
    }

    //Публикация события
    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEvent(
            @PathVariable long eventId
    ) {
        log.info("Publish event id={}", eventId);
        return eventService.publishEvent(eventId);
    }

    //Отклонение события
    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEvent(
            @PathVariable long eventId
    ) {
        log.info("Reject event id={}", eventId);
        return eventService.rejectEvent(eventId);
    }
}
