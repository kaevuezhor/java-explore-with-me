package ru.practicum.explore.compilation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;
import ru.practicum.explore.compilation.mapper.CompilationMapper;
import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.compilation.repository.CompilationsRepository;
import ru.practicum.explore.compilation.service.AdminCompilationsService;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCompilationsServiceImpl implements AdminCompilationsService {

    private final CompilationsRepository compilationsRepository;

    private final EventRepository eventRepository;

    private final CompilationMapper compilationMapper;

    @Override
    public CompilationDto postCompilation(NewCompilationDto newCompilationDto) {
        List<Event> events = eventRepository.findAllById(newCompilationDto.getEvents());
        Compilation compilation = compilationMapper.toCompilationModel(newCompilationDto, events);
        return compilationMapper.toCompilationDto(compilationsRepository.save(compilation));
    }

    @Override
    public void deleteCompilation(long compId) {
        compilationsRepository.deleteById(compId);
    }

    @Override
    public void deleteEventFromCompilation(long compId, long eventId) {
        Compilation compilation = compilationsRepository.getReferenceById(compId);
        Event event = eventRepository.findById(eventId).orElseThrow();
        List<Event> events = compilation.getEvents();
        events.remove(event);
        compilationsRepository.save(compilation);
    }

    @Override
    public void addEventToCompilation(long compId, long eventId) {
        Compilation compilation = compilationsRepository.getReferenceById(compId);
        Event event = eventRepository.findById(eventId).orElseThrow();
        List<Event> events = compilation.getEvents();
        events.add(event);
        compilationsRepository.save(compilation);
    }

    @Override
    public void removeCompilationPin(long compId) {
        Compilation compilation = compilationsRepository.getReferenceById(compId);
        compilation.setPinned(false);
        compilationsRepository.save(compilation);
    }

    @Override
    public void addCompilationPin(long compId) {
        Compilation compilation = compilationsRepository.getReferenceById(compId);
        compilation.setPinned(true);
        compilationsRepository.save(compilation);
    }
}
