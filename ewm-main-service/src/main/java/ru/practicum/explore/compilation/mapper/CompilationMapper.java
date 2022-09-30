package ru.practicum.explore.compilation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;
import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.event.mapper.EventMapper;
import ru.practicum.explore.event.model.Event;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompilationMapper {

    private final EventMapper eventMapper;

    public CompilationDto toCompilationDto(Compilation compilation) {
        return new CompilationDto(
                compilation.getEvents().stream()
                        .map(eventMapper::toEventShortDto)
                        .collect(Collectors.toList()),
                compilation.getId(),
                compilation.isPinned(),
                compilation.getTitle()
        );
    }

    public Compilation toCompilationModel(NewCompilationDto newCompilationDto, List<Event> events) {
        return new Compilation(
                0,
                events,
                newCompilationDto.isPinned(),
                newCompilationDto.getTitle()
        );
    }
}
