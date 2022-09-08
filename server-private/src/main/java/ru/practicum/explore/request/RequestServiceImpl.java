package ru.practicum.explore.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

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
