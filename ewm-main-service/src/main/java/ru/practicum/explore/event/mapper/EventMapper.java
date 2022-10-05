package ru.practicum.explore.event.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explore.category.mapper.CategoryMapper;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.EventState;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.Location;
import ru.practicum.explore.like.model.Rate;
import ru.practicum.explore.user.mapper.UserMapper;
import ru.practicum.explore.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final CategoryMapper categoryMapper;

    private final UserMapper userMapper;

    public EventShortDto toEventShortDto(Event event) {
        return new EventShortDto(
                event.getAnnotation(),
                categoryMapper.toCategoryDto(event.getCategory()),
                event.getConfirmedRequests().size(),
                event.getEventDate(),
                event.getId(),
                userMapper.toUserShortDto(event.getInitiator()),
                event.isPaid(),
                event.getTitle(),
                event.getViews()
        );
    }

    public EventFullDto toEventFullDto(Event event) {
        List<Rate> rates = event.getRates();
        long likes = 0;
        long dislikes = 0;
        for (Rate rate : rates) {
            if (rate.isLiked()) {
                likes++;
            } else {
                dislikes++;
            }
        }
        return new EventFullDto(
                event.getAnnotation(),
                categoryMapper.toCategoryDto(event.getCategory()),
                event.getConfirmedRequests().size(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                event.getId(),
                userMapper.toUserShortDto(event.getInitiator()),
                event.getLocation(),
                event.isPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.isRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews(),
                likes,
                dislikes
        );
    }

    public Event toEventModel(NewEventDto newEventDto, Category category, User initiator, Location location) {
        return new Event(
                newEventDto.getAnnotation(),
                category,
                List.of(),
                LocalDateTime.now(),
                newEventDto.getDescription(),
                newEventDto.getEventDate(),
                0,
                initiator,
                location,
                newEventDto.isPaid(),
                newEventDto.getParticipantLimit(),
                null,
                newEventDto.isRequestModeration(),
                EventState.PENDING,
                newEventDto.getTitle(),
                0,
                List.of()
        );
    }
}
