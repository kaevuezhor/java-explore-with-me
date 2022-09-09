package ru.practicum.explore.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers(List<Long> users, int from, int size) {
        PageRequest pageRequest = PageRequest.of(size - from, size);
        List<User> foundUsers = userRepository.findAll();
        return foundUsers.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto postUser(UserDto userDto) {
        return userMapper.toUserDto(
                userRepository.save(
                        userMapper.toUserModel(userDto)
                )
        );
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
