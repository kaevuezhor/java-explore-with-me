package ru.practicum.explore.compilation.service;

import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;

public interface AdminCompilationsService {

    CompilationDto postCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(long compId);

    void deleteEventFromCompilation(long compId, long eventId);

    void addEventToCompilation(long compId, long eventId);

    void removeCompilationPin(long compId);

    void addCompilationPin(long compId);
}
