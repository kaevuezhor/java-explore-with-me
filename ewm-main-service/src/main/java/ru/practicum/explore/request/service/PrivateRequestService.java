package ru.practicum.explore.request.service;

import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

public interface PrivateRequestService {

    List<ParticipationRequestDto> getRequests(long userId);

    ParticipationRequestDto postRequest(long userId, long eventId);

    ParticipationRequestDto cancelRequest(long userId, long requestId);
}
