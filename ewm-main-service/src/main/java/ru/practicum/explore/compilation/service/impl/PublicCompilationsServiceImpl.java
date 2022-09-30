package ru.practicum.explore.compilation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.mapper.CompilationMapper;
import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.compilation.repository.CompilationsRepository;
import ru.practicum.explore.compilation.service.PublicCompilationsService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicCompilationsServiceImpl implements PublicCompilationsService {

    private final CompilationsRepository publicCompilationsRepository;

    private final CompilationMapper compilationMapper;
    
    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, int from, int size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "id"));
        List<Compilation> compilations;
        if (pinned == null) {
            compilations = publicCompilationsRepository.findAll(pageRequest).getContent();
        } else {
            compilations = publicCompilationsRepository.findAllByPinned(pinned, pageRequest);
        }
        return compilations.stream()
                .map(compilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        Compilation foundCompilation = publicCompilationsRepository.findById(compId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Compilation id " + compId));
        return compilationMapper.toCompilationDto(foundCompilation);
    }
}
