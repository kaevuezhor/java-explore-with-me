package ru.practicum.explore.user;

import org.springframework.stereotype.Component;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.dto.UserShortDto;
import ru.practicum.explore.user.model.User;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        return new UserDto(user.getEmail(), user.getId(), user.getName());
    }

    public UserShortDto toUserShortDto(User user) {
        return new UserShortDto(user.getId(), user.getName());
    }

    public User toUserModel(UserDto userDto) {
        return new User(userDto.getId(), userDto.getEmail(), userDto.getName());
    }
}
