package ru.practicum.explore.request.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.service.PrivateRequestService;

import java.util.List;

@Service
public class PrivateRequestServiceImpl implements PrivateRequestService {
    @Override
    public List<ParticipationRequestDto> getRequests(long userId) {
        return null;
    }

    @Override
    public ParticipationRequestDto postRequest(long userId, long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto cancelRequest(long userId, long eventId) {
        return null;
    }
}
