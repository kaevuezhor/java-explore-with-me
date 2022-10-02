package ru.practicum.explore.event.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.category.repository.CategoryRepository;
import ru.practicum.explore.event.dto.*;
import ru.practicum.explore.event.mapper.EventMapper;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.Location;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.event.repository.LocationRepository;
import ru.practicum.explore.event.service.PrivateEventService;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.dto.RequestStatus;
import ru.practicum.explore.request.mapper.RequestMapper;
import ru.practicum.explore.request.model.ParticipationRequest;
import ru.practicum.explore.request.repository.RequestRepository;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateEventServiceImpl implements PrivateEventService {

    private final EventRepository eventRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final RequestRepository requestRepository;

    private final LocationRepository locationRepository;

    private final EventMapper eventMapper;

    private final RequestMapper requestMapper;

    @Override
    public List<EventShortDto> getEvents(long userId, int from, int size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by("eventDate"));
        List<Event> foundEvents = eventRepository.findAllByInitiatorId(userId, pageRequest);
        return foundEvents.stream()
                .map(eventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto patchEvent(long userId, UpdateEventRequest updateEventRequest) {
        Optional<Event> foundEvent = eventRepository.findById(updateEventRequest.getEventId());
        if (foundEvent.isEmpty()) {
            throw new EntityNotFoundException("Unable to find Event id " + updateEventRequest.getEventId());
        }
        if (foundEvent.get().getState().equals(EventState.PUBLISHED)) {
            throw new IllegalArgumentException("State must be on of [PENDING, CANCELED]");
        }
        if (foundEvent.get().getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new IllegalArgumentException("Event date must be after " + LocalDateTime.now().plusHours(2));
        }

        Event updatedEvent = eventRepository.save(patch(updateEventRequest));
        return eventMapper.toEventFullDto(updatedEvent);
    }

    @Override
    public EventFullDto postEvent(long userId, NewEventDto newEventDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find User id " + userId));
        Category category = categoryRepository.findById(newEventDto.getCategory())
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Category id " + newEventDto.getCategory()));
        Location location = locationRepository.save(newEventDto.getLocation());
        Event savedEvent = eventRepository.save(eventMapper.toEventModel(newEventDto, category, user, location));
        return eventMapper.toEventFullDto(savedEvent);
    }

    @Override
    public EventFullDto getEvent(long userId, long eventId) {
        Optional<Event> foundEvent = eventRepository.findByIdAndInitiatorId(eventId, userId);
        return eventMapper.toEventFullDto(
                foundEvent
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Event id " + eventId))
        );
    }

    @Override
    public EventFullDto patchEvent(long userId, long eventId) throws AccessException {
        Event event = eventRepository.getReferenceById(eventId);

        if (!event.getState().equals(EventState.PENDING)) {
            throw new IllegalArgumentException("State must be PENDING");
        }
        if (event.getInitiator().getId() != userId) {
            throw new AccessException("User id " + userId + " not initiator");
        }

        event.setState(EventState.CANCELED);
        return eventMapper.toEventFullDto(eventRepository.save(event));
    }

    @Override
    public List<ParticipationRequestDto> getEventRequests(long userId, long eventId) {
        List<ParticipationRequest> foundRequests = requestRepository.findAllByEventId(eventId);
        return foundRequests.stream()
                .map(requestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) throws AccessException {
        ParticipationRequest request = requestRepository.findById(reqId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Request id " + reqId));
        if (request.getEvent().getId() != eventId) {
            throw new EntityNotFoundException("Unable to find Request id " + reqId);
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Event id " + eventId));
        if (event.getInitiator().getId() != userId) {
            throw new AccessException("User id " + userId + " not initiator");
        }
        if (event.getConfirmedRequests().size() == event.getParticipantLimit()) {
            throw new AccessException("Participant limit is full");
        }

        ParticipationRequest confirmingRequest = requestRepository.getReferenceById(reqId);
        confirmingRequest.setStatus(RequestStatus.CONFIRMED);
        ParticipationRequest confirmedRequest = requestRepository.save(confirmingRequest);

        Event updatedEvent = eventRepository.getReferenceById(eventId);
        List<ParticipationRequest> confirmedRequests = updatedEvent.getConfirmedRequests();
        confirmedRequests.add(confirmedRequest);
        updatedEvent.setConfirmedRequests(confirmedRequests);
        eventRepository.save(updatedEvent);

        return requestMapper.toParticipationRequestDto(confirmedRequest);
    }

    @Override
    public ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) throws AccessException {
        ParticipationRequest request = requestRepository.findById(reqId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Request id " + reqId));
        if (request.getEvent().getId() != eventId) {
            throw new EntityNotFoundException("Unable to find Request id " + reqId);
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Event id " + eventId));
        if (event.getInitiator().getId() != userId) {
            throw new AccessException("User id " + userId + " not initiator");
        }

        ParticipationRequest rejectingRequest = requestRepository.getReferenceById(reqId);
        rejectingRequest.setStatus(RequestStatus.REJECTED);
        ParticipationRequest confirmedRequest = requestRepository.save(rejectingRequest);

        return requestMapper.toParticipationRequestDto(confirmedRequest);
    }

    private Event patch(UpdateEventRequest updateEventRequest) {
        Event savedEvent = eventRepository.getReferenceById(updateEventRequest.getEventId());
        if (updateEventRequest.getAnnotation() != null) {
            savedEvent.setAnnotation(updateEventRequest.getAnnotation());
        }
        if (updateEventRequest.getCategory() != null) {
            Optional<Category> patchCategory = categoryRepository.findById(updateEventRequest.getCategory());
            savedEvent.setCategory(
                    patchCategory
                    .orElseThrow(() -> new EntityNotFoundException("Unable to find Category id " + updateEventRequest.getCategory()))
            );
        }
        if (updateEventRequest.getDescription() != null) {
            savedEvent.setDescription(updateEventRequest.getDescription());
        }
        if (updateEventRequest.getEventDate() != null) {
            savedEvent.setEventDate(updateEventRequest.getEventDate());
        }
        if (updateEventRequest.getPaid() != null) {
            savedEvent.setPaid(updateEventRequest.getPaid());
        }
        if (updateEventRequest.getParticipantLimit() != null) {
            savedEvent.setParticipantLimit(updateEventRequest.getParticipantLimit());
        }
        if (updateEventRequest.getTitle() != null) {
            savedEvent.setTitle(updateEventRequest.getTitle());
        }
        if (savedEvent.getState().equals(EventState.CANCELED)) {
            savedEvent.setState(EventState.PENDING);
        }
        return savedEvent;
    }

}
