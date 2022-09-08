package ru.practicum.explore.compilation.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;
import ru.practicum.explore.compilation.service.AdminCompilationsService;

@Service
public class AdminCompilationsServiceImpl implements AdminCompilationsService {
    @Override
    public CompilationDto postCompilation(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void deleteCompilation(long compId) {

    }

    @Override
    public void deleteEventFromCompilation(long compId, long eventId) {

    }

    @Override
    public void addEventToCompilation(long compId, long eventId) {

    }

    @Override
    public void removeCompilationPin(long compId) {

    }

    @Override
    public void addCompilationPin(long compId) {

    }
}
