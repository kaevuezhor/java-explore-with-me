package ru.practicum.explore.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    //Получение информации о заявках текущего пользователя на участие в чужих событиях
    @GetMapping
    public List<ParticipationRequestDto> getRequests(
            @RequestHeader("X-User-Id") long userId
    ) {
        log.info("Get requests user i={}", userId);
        return requestService.getRequests(userId);
    }

    //Добавление запроса от текущего пользователя на участие в событии
    @PostMapping
    public ParticipationRequestDto postRequest(
            @RequestHeader("X-User-Id") long userId,
            @RequestParam long eventId
    ) {
        log.info("Post request user id={}, event id={}", userId, eventId);
        return requestService.postRequest(userId, eventId);
    }

    //Отмена своего запроса на участие в событии
    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @RequestHeader("X-User-Id") long userId,
            @PathVariable long requestId
    ) {
        log.info("Cancel user id={}, request id={}", userId, requestId);
        return requestService.cancelRequest(userId, requestId);
    }
}
