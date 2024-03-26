package cn.todo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NationalityWebClientService {

    private WebClient webClient;

    public Mono<String> predictNationality(String baseUrl, String name) {
        this.webClient = WebClient.create(baseUrl);

        return webClient
                .get()
                .uri("?name={name}", name)
                .retrieve()
                .bodyToMono(String.class);
    }

}
