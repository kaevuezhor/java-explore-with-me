package ru.practicum.explore.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventRequest;
import ru.practicum.explore.event.service.PrivateEventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Validated
public class PrivateEventController {

    private final PrivateEventService privateEventService;

    //Получение событий, добавленных текущим пользователем
    @GetMapping
    public List<EventShortDto> getEvents(
            @PathVariable long userId,
            @PositiveOrZero @RequestParam int from,
            @Positive @RequestParam int size
    ) {
        log.info("Get events user id={}, from={}, size={}", userId, from, size);
        return privateEventService.getEvents(userId, from, size);
    }

    //Изменение события добавленного текущим пользователем
    @PatchMapping
    public EventFullDto patchEvent(
            @PathVariable long userId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        log.info("Patch event user id={}, patch {}", userId, updateEventRequest);
        return privateEventService.patchEvent(userId, updateEventRequest);
    }

    //Добавление нового события
    @PostMapping
    public EventFullDto postEvent(
            @PathVariable long userId,
            @RequestBody @Valid NewEventDto newEventDto
    ) {
        log.info("Post event user id={}, event {}", userId, newEventDto);
        if (newEventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new IllegalArgumentException(newEventDto.getEventDate().toString());
        }
        return privateEventService.postEvent(userId, newEventDto);
    }

    //Получение полной информации о событии добавленном текущим пользователем
    @GetMapping("/{eventId}")
    public EventFullDto getEvent(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        log.info("Get event user id={}, event id={}", userId, eventId);
        return privateEventService.getEvent(userId, eventId);
    }

    //Отмена события добавленного текущим пользователем
    @PatchMapping("/{eventId}")
    public EventFullDto patchEvent(
            @PathVariable long userId,
            @PathVariable long eventId,
            @RequestBody UpdateEventRequest updateEventRequest
    ) {
        log.info("Patch event user id={}, event id={}, patch {}", userId, eventId, updateEventRequest);
        return privateEventService.patchEvent(userId, eventId, updateEventRequest);
    }

    //Получение информации о запросах на участие в событии текущего пользователя
    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getEventRequests(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        log.info("Get user id={} event id={} requests", userId, eventId);
        return privateEventService.getEventRequests(userId, eventId);
    }

    //Подтверждение чужой заявки на участие в событии текущего пользователя
    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        log.info("Confirm user id={}, event id={}, request id={}", userId, eventId, reqId);
        return privateEventService.confirmRequest(userId, eventId, reqId);
    }

    //Отклонение чужой заявки на участие в событии текущего пользователя
    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequest(
            @PathVariable long userId,
            @PathVariable long eventId,
            @PathVariable long reqId
    ) {
        log.info("Reject user id={}, event id={}, request id={}", userId, eventId, reqId);
        return privateEventService.rejectRequest(userId, eventId, reqId);
    }
}
