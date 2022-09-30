package ru.practicum.explore.request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.service.PrivateRequestService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
public class PrivateRequestController {

    private final PrivateRequestService privateRequestService;

    //Получение информации о заявках текущего пользователя на участие в чужих событиях
    @GetMapping
    public List<ParticipationRequestDto> getRequests(
            @PathVariable long userId
    ) {
        log.info("Get requests user i={}", userId);
        return privateRequestService.getRequests(userId);
    }

    //Добавление запроса от текущего пользователя на участие в событии
    @PostMapping
    public ParticipationRequestDto postRequest(
            @PathVariable long userId,
            @RequestParam long eventId
    ) {
        log.info("Post request user id={}, event id={}", userId, eventId);
        return privateRequestService.postRequest(userId, eventId);
    }

    //Отмена своего запроса на участие в событии
    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @PathVariable long userId,
            @PathVariable long requestId
    ) {
        log.info("Cancel user id={}, request id={}", userId, requestId);
        return privateRequestService.cancelRequest(userId, requestId);
    }
}
