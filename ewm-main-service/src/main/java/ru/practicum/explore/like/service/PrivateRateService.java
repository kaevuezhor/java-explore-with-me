package ru.practicum.explore.like.service;

import java.rmi.AccessException;

public interface PrivateRateService {

    void like(long userId, long eventId) throws IllegalAccessException, AccessException;

    void dislike(long userId, long eventId) throws IllegalAccessException, AccessException;
}
