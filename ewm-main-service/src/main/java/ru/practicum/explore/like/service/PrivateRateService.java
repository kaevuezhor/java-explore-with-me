package ru.practicum.explore.like.service;

public interface PrivateRateService {

    void like(long userId, long eventId);

    void dislike(long userId, long eventId);
}
