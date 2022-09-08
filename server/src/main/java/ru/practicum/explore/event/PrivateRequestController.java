package ru.practicum.explore.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class PrivateRequestController {
    //Получение информации о заявках текущего пользователя на участие в чужих событиях
    @GetMapping("{userId}/requests")
    public List<ParticipationRequestDto> getRequests(
            @PathVariable long userId
    ) {
        log.info("Get requests user i={}", userId);
        return null;
    }

    //Добавление запроса от текущего пользователя на участие в событии
    @PostMapping("{userId}/requests")
    public ParticipationRequestDto postRequest(
            @PathVariable long userId,
            @RequestParam long eventId
    ) {
        log.info("Post request user id={}, event id={}", userId, eventId);
        return null;
    }

    //Отмена своего запроса на участие в событии
    @PatchMapping("{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @PathVariable long userId,
            @PathVariable long requestId
    ) {
        log.info("Cancel user id={}, request id={}", userId, requestId);
        return null;
    }



    //
}
