package ru.practicum.explore.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.user.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
