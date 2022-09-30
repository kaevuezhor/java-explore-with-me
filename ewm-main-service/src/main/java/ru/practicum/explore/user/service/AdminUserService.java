package ru.practicum.explore.user.service;

import ru.practicum.explore.user.dto.UserDto;

import java.util.List;

public interface AdminUserService {

    List<UserDto> getUsers(List<Long> users, int from, int size);

    UserDto postUser(UserDto userDto);

    void deleteUser(long userId);
}
