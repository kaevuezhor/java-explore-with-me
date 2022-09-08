package ru.practicum.explore.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.AdminUpdateEventRequest;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventState;
import ru.practicum.explore.event.service.AdminEventService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {

    private final AdminEventService adminEventService;

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
                "[ADMIN] Get events users={}, states={}, categories={}, rangeStart={}, rangeEnd={}, from={}, size={}",
                users, states, categories, rangeStart, rangeEnd, from, size
        );
        return adminEventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    //Редактирование события
    @PutMapping("/{eventId}")
    public EventFullDto updateEvent(
            @PathVariable long eventId,
            @RequestBody AdminUpdateEventRequest adminUpdateEventRequest
    ) {
        log.info("[ADMIN] Update event id={}, update={}", eventId, adminUpdateEventRequest);
        return adminEventService.updateEvent(eventId, adminUpdateEventRequest);
    }

    //Публикация события
    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEvent(
            @PathVariable long eventId
    ) {
        log.info("[ADMIN] Publish event id={}", eventId);
        return adminEventService.publishEvent(eventId);
    }

    //Отклонение события
    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEvent(
            @PathVariable long eventId
    ) {
        log.info("[ADMIN] Reject event id={}", eventId);
        return adminEventService.rejectEvent(eventId);
    }
}
