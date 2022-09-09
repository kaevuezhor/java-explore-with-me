package ru.practicum.explore.user.service.impl;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.service.AdminUserService;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final WebClient webClient;

    private static final String API_PREFIX = "/users";

    @Autowired
    public AdminUserServiceImpl(@Value("${explore-server-admin.url}") String serverUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(serverUrl + API_PREFIX)
                .build();
    }
    @Override
    public List<UserDto> getUsers(List<Long> ids, int from, int size) {
        return webClient
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .queryParam("ids", ids)
                                .queryParam("from", from)
                                .queryParam("size", size)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserDto>>() {})
                .block();
    }

    @Override
    public UserDto postUser(UserDto userDto) {
        return webClient
                .post()
                .bodyValue(userDto)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public void deleteUser(long userId) {
        webClient.delete()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/{userId}")
                                .build(userId)
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
