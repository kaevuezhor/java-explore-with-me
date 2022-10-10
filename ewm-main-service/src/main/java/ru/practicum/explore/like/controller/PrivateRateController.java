package ru.practicum.explore.like.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore.like.service.PrivateRateService;

import java.rmi.AccessException;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events/{eventId}")
@RequiredArgsConstructor
public class PrivateRateController {

    private final PrivateRateService privateRateService;

    @PatchMapping("/like")
    public void like(
            @PathVariable long userId,
            @PathVariable long eventId
    ) throws IllegalAccessException, AccessException {
        log.info("Like user id={}, event id={}", userId, eventId);
        privateRateService.like(userId, eventId);
    }

    @PatchMapping("/dislike")
    public void dislike(
            @PathVariable long userId,
            @PathVariable long eventId
    ) throws IllegalAccessException, AccessException {
        log.info("Dislike user id={}, event id={}", userId, eventId);
        privateRateService.dislike(userId, eventId);
    }
}
