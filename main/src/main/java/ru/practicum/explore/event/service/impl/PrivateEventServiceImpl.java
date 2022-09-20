package ru.practicum.explore.event.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
            throw new EntityNotFoundException("Not found");
        }
        Event savedEvent = eventRepository.getReferenceById(updateEventRequest.getEventId());
        if (updateEventRequest.isAnnotationNeedUpdate()) {
            savedEvent.setAnnotation(updateEventRequest.getAnnotation());
        }
        if (updateEventRequest.isCategoryNeedUpdate()) {
            Optional<Category> patchCategory = categoryRepository.findById(updateEventRequest.getCategory());
            savedEvent.setCategory(patchCategory.orElseThrow(() -> new EntityNotFoundException("dfdfdf")));
        }
        if (updateEventRequest.isDescriptionNeedUpdate()) {
            savedEvent.setDescription(updateEventRequest.getDescription());
        }
        if (updateEventRequest.isEventDateNeedUpdate()) {
            savedEvent.setEventDate(updateEventRequest.getEventDate());
        }
        if (updateEventRequest.isPaidFlagNeedUpdate()) {
            savedEvent.setPaid(updateEventRequest.getPaid());
        }
        if (updateEventRequest.isParticipantLimitNeedUpdate()) {
            savedEvent.setParticipantLimit(updateEventRequest.getParticipantLimit());
        }
        if (updateEventRequest.isTitleNeedUpdate()) {
            savedEvent.setTitle(updateEventRequest.getTitle());
        }
        Event updatedEvent = eventRepository.save(savedEvent);
        return eventMapper.toEventFullDto(updatedEvent);
    }

    @Override
    public EventFullDto postEvent(long userId, NewEventDto newEventDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("loh"));
        Category category = categoryRepository.findById(newEventDto.getCategory()).orElseThrow(() -> new EntityNotFoundException("loh"));
        Location location = locationRepository.save(newEventDto.getLocation());
        Event savedEvent = eventRepository.save(eventMapper.toEventModel(newEventDto, category, user, location));
        return eventMapper.toEventFullDto(savedEvent);
    }

    @Override
    public EventFullDto getEvent(long userId, long eventId) {
        Optional<Event> foundEvent = eventRepository.findByIdAndInitiatorId(eventId, userId);
        return eventMapper.toEventFullDto(foundEvent.orElseThrow(() -> new EntityNotFoundException("Не найдено:(((")));
    }

    @Override
    public EventFullDto patchEvent(long userId, long eventId, UpdateEventRequest updateEventRequest) {
        Event event = eventRepository.getReferenceById(eventId);
        if (!event.getState().equals(EventState.PENDING)) {
            throw new IllegalArgumentException("loh");
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
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) {
        ParticipationRequest request = requestRepository.findById(reqId).orElseThrow(() -> new RuntimeException("loh"));
        if (request.getEvent().getId() != eventId) {
            throw new RuntimeException("loh");
        }

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("loh"));
        if (event.getInitiator().getId() != userId) {
            throw new RuntimeException("loh");
        }
        if (event.getConfirmedRequests().size() == event.getParticipantLimit()) {
            throw new RuntimeException("loh");
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
    public ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) {
        ParticipationRequest request = requestRepository.findById(reqId).orElseThrow(() -> new RuntimeException("loh"));
        if (request.getEvent().getId() != eventId) {
            throw new RuntimeException("loh");
        }

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("loh"));
        if (event.getInitiator().getId() != userId) {
            throw new RuntimeException("loh");
        }

        ParticipationRequest rejectingRequest = requestRepository.getReferenceById(reqId);
        rejectingRequest.setStatus(RequestStatus.REJECTED);
        ParticipationRequest confirmedRequest = requestRepository.save(rejectingRequest);

        return requestMapper.toParticipationRequestDto(confirmedRequest);
    }



}
