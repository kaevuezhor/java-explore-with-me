package ru.practicum.explore.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventRequest;
import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    //Получение событий, добавленных текущим пользователем
    @GetMapping
    public List<EventShortDto> getEvents(
            @RequestHeader("X-User-Id") long userId,
            @RequestParam int from,
            @RequestParam int size
    ) {
        log.info("Get events user id={}, from={}, size={}", userId, from, size);
        return eventService.getEvents(userId, from, size);
    }

    //Изменение события добавленного текущим пользователем
    @PatchMapping
    public EventFullDto patchEvent(
            @RequestHeader("X-User-Id") long userId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        log.info("Patch event user id={}, patch {}", userId, updateEventRequest);
        return eventService.patchEvent(userId, updateEventRequest);
    }

    //Добавление нового события
    @PostMapping
    public EventFullDto postEvent(
            @RequestHeader("X-User-Id") long userId,
            @RequestBody NewEventDto newEventDto
    ) {
        log.info("Post event user id={}, event {}", userId, newEventDto);
        return eventService.postEvent(userId, newEventDto);
    }

    //Получение полной информации о событии добавленном текущим пользователем
    @GetMapping("/{eventId}")
    public EventFullDto getEvent(
            @RequestHeader("X-User-Id") long userId,
            @PathVariable long eventId
    ) {
        log.info("Get event user id={}, event id={}", userId, eventId);
        return eventService.getEvent(userId, eventId);
    }

    //Отмена события добавленного текущим пользователем
    @PatchMapping("/{eventId}")
    public EventFullDto patchEvent(
            @RequestHeader("X-User-Id") long userId,
            @PathVariable long eventId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        log.info("Patch event user id={}, event id={}, patch {}", userId, eventId, updateEventRequest);
        return eventService.patchEvent(userId, eventId, updateEventRequest);
    }

    //Получение информации о запросах на участие в событии текущего пользователя
    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getEventRequests(
            @RequestHeader("X-User-Id") long userId,
            @PathVariable long eventId
    ) {
        log.info("Get user id={} event id={} requests", userId, eventId);
        return eventService.getEventRequests(userId, eventId);
    }

    //Подтверждение чужой заявки на участие в событии текущего пользователя
    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(
            @RequestHeader("X-User-Id") long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        log.info("Confirm user id={}, event id={}, request id={}", userId, eventId, reqId);
        return eventService.confirmRequest(userId, eventId, reqId);
    }

    //Отклонение чужой заявки на участие в событии текущего пользователя
    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequest(
            @RequestHeader("X-User-Id") long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        log.info("Reject user id={}, event id={}, request id={}", userId, eventId, reqId);
        return eventService.rejectRequest(userId, eventId, reqId);
    }
}
