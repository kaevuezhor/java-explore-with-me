package ru.practicum.explore.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.user.service.AdminUserService;
import ru.practicum.explore.user.dto.UserDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    //Поиск пользователей
    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("[ADMIN] Get users users={}, from={}, size={}", users, from, size);
        return adminUserService.getUsers(users, from, size);
    }

    //Добавление нового пользователя
    @PostMapping
    public UserDto postUser(
            @RequestBody UserDto userDto
    ) {
        log.info("[ADMIN] Post user user={}", userDto);
        return adminUserService.postUser(userDto);
    }

    //Удаление пользователя
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable long userId
    ) {
        log.info("[ADMIN] Delete user id={}", userId);
        adminUserService.deleteUser(userId);
    }
}
