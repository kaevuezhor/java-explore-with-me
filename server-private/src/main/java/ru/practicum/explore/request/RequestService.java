package ru.practicum.explore.request;

import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

public interface RequestService {
    List<ParticipationRequestDto> getRequests(long userId);

    ParticipationRequestDto postRequest(long userId, long eventId);

    ParticipationRequestDto cancelRequest(long userId, long eventId);
}
