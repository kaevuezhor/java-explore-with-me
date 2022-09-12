package ru.practicum.explore.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.mapper.CompilationMapper;
import ru.practicum.explore.compilation.model.Compilation;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationsServiceImpl implements CompilationsService {

    private final CompilationsRepository compilationsRepository;

    private final CompilationMapper compilationMapper;
    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, int from, int size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by(Sort.Direction.DESC, "id"));
        List<Compilation> compilations;
        if (pinned == null) {
            compilations = compilationsRepository.findAll(pageRequest).getContent();
        } else {
            compilations = compilationsRepository.findAllByPinned(pinned, pageRequest);
        }
        return compilations.stream()
                .map(compilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        Compilation foundCompilation = compilationsRepository.findById(compId).orElseThrow(() -> new EntityNotFoundException("Compilation not found"));
        return compilationMapper.toCompilationDto(foundCompilation);
    }
}
