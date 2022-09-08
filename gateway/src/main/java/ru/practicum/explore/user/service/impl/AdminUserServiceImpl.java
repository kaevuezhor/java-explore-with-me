package ru.practicum.explore.user.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.service.AdminUserService;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Override
    public List<UserDto> getUsers(List<Long> users, int from, int size) {
        return null;
    }

    @Override
    public UserDto postUser(UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(long userId) {

    }
}
