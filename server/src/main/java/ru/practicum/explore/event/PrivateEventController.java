package ru.practicum.explore.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class PrivateEventController {
    //Получение событий, добавленных текущим пользователем
    @GetMapping("/{userId}/events")
    public List<EventShortDto> getEvents(
            @PathVariable long userId,
            @RequestParam int from,
            @RequestParam int size
    ) {
        log.info("Get events user id={}, from={}, size={}", userId, from, size);
        return null;
    }

    //Изменение события добавленного текущим пользователем
    @PatchMapping("/{userId}/events")
    public EventFullDto patchEvent(
            @PathVariable long userId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        log.info("Patch event user id={}, patch {}", userId, updateEventRequest);
        return null;
    }

    //Добавление нового события
    @PostMapping("/{userId}/events")
    public EventFullDto postEvent(
            @PathVariable long userId,
            @RequestBody NewEventDto newEventDto
    ) {
        log.info("Post event user id={}, event {}", userId, newEventDto);
        return null;
    }

    //Получение полной информации о событии добавленном текущим пользователем
    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto getEvent(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        log.info("Get event user id={}, event id={}", userId, eventId);
        return null;
    }

    //Отмена события добавленного текущим пользователем
    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto patchEvent(
            @PathVariable long userId,
            @PathVariable long eventId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        log.info("Patch event user id={}, event id={}, patch {}", userId, eventId, updateEventRequest);
        return null;
    }

    //Получение информации о запросах на участие в событии текущего пользователя
    @GetMapping("{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getEventRequests(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        log.info("Get user id={} event id={} requests", userId, eventId);
        return null;
    }

    //Подтверждение чужой заявки на участие в событии текущего пользователя
    @PatchMapping("{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        log.info("Confirm user id={}, event id={}, request id={}", userId, eventId, reqId);
        return null;
    }

    //Отклонение чужой заявки на участие в событии текущего пользователя
    @PatchMapping("{userId}/events/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        log.info("Reject user id={}, event id={}, request id={}", userId, eventId, reqId);
        return null;
    }
}
