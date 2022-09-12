package ru.practicum.explore.event.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.explore.event.dto.UserShortDto;
import ru.practicum.explore.event.model.User;

@Component
public class UserMapper {

    public UserShortDto toUserShortDto(User user) {
        return new UserShortDto(
                user.getId(),
                user.getName()
        );
    }
}
