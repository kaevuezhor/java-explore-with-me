package ru.practicum.explore.like.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.like.service.PrivateRateService;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events/{eventId}")
@RequiredArgsConstructor
public class PrivateLikeController {

    private final PrivateRateService privateRateService;

    @PatchMapping("/like")
    public void like(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        log.info("Like user id={}, event id={}", userId, eventId);
        privateRateService.like(userId, eventId);
    }

    @PatchMapping("/dislike")
    public void dislike(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        log.info("Dislike user id={}, event id={}", userId, eventId);
        privateRateService.dislike(userId, eventId);
    }
}
