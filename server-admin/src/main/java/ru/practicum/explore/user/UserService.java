package ru.practicum.explore.user;

import ru.practicum.explore.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers(List<Long> users, int from, int size);

    UserDto postUser(UserDto userDto);

    void deleteUser(long userId);
}
