package ru.practicum.explore.event.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.EventSort;
import ru.practicum.explore.event.mapper.EventMapper;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.event.service.PublicEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    @Override
    public List<EventShortDto> getEvents(
            String text,
            List<Long> categories,
            Boolean paid,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            boolean onlyAvailable,
            EventSort sort,
            int from,
            int size
    ) {
        String sortParam = "views";
        if (sort.equals(EventSort.EVENT_DATE)) {
            sortParam = "eventDate";
        }
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by(sortParam));
        if (text == null) {
            text = "";
        }
        if (rangeStart ==  null) {
            rangeStart = LocalDateTime.now();
        }
        if (rangeEnd == null) {
            rangeEnd = LocalDateTime.now().plusYears(100);
        }
        if (categories == null) {
            categories = List.of();
        }
        List<Event> events;
        if (categories.isEmpty() && paid == null && onlyAvailable) {
            events = eventRepository.findAllAvailable(text, rangeStart, rangeEnd, pageRequest);
        } else if (categories.isEmpty() && paid == null) {
            events = eventRepository.findAll(text, rangeStart, rangeEnd, pageRequest);
        } else if (categories.isEmpty() && onlyAvailable) {
            events = eventRepository.findAllAvailable(text, paid, rangeStart, rangeEnd, pageRequest);
        } else if (categories.isEmpty()) {
            events = eventRepository.findAll(text, paid, rangeStart, rangeEnd, pageRequest);
        } else if (paid == null && onlyAvailable) {
            events = eventRepository.findAllAvailable(text, categories, rangeStart, rangeEnd, pageRequest);
        } else if (paid == null) {
            events = eventRepository.findAll(text, categories, rangeStart, rangeEnd, pageRequest);
        } else if (onlyAvailable) {
            events = eventRepository.findAllAvailable(text, categories, paid, rangeStart, rangeEnd, pageRequest);
        } else {
            events = eventRepository.findAll(text, categories, paid, rangeStart, rangeEnd, pageRequest);
        }
        return events.stream().map(eventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto getEvent(long id) {
        Event foundEvent = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event " + id + " not found"));
        return eventMapper.toEventFullDto(foundEvent);
    }

}
