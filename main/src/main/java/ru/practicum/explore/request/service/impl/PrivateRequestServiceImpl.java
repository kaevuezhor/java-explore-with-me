package ru.practicum.explore.request.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.dto.RequestStatus;
import ru.practicum.explore.request.mapper.RequestMapper;
import ru.practicum.explore.request.model.ParticipationRequest;
import ru.practicum.explore.request.repository.RequestRepository;
import ru.practicum.explore.request.service.PrivateRequestService;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateRequestServiceImpl implements PrivateRequestService {

    private final RequestRepository requestRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final RequestMapper requestMapper;

    @Override
    public List<ParticipationRequestDto> getRequests(long userId) {
        List<ParticipationRequest> foundRequests = requestRepository.findAllByRequesterId(userId);
        return foundRequests.stream()
                .map(requestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto postRequest(long userId, long eventId) {
        User requester = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("loh"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("loh"));
        if (event.getConfirmedRequests().size() == event.getParticipantLimit() && event.getParticipantLimit() != 0) {
            throw new RuntimeException("loh");
        }

        RequestStatus status;
        if (event.isRequestModeration()) {
            status = RequestStatus.PENDING;
        } else {
            status = RequestStatus.CONFIRMED;
        }

        ParticipationRequest request = new ParticipationRequest(
                LocalDateTime.now(),
                event,
                0,
                requester,
                status
        );

        return requestMapper.toParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    public ParticipationRequestDto cancelRequest(long userId, long eventId) {
        ParticipationRequest request = requestRepository.findByRequesterIdAndEventId(userId, eventId).orElseThrow(() -> new RuntimeException());
        request.setStatus(RequestStatus.REJECTED);
        ParticipationRequest cancelledRequest = requestRepository.save(request);
        return requestMapper.toParticipationRequestDto(cancelledRequest);
    }
}
