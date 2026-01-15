package com.example.parser;

import com.example.parser.dto.AmsrosResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FedresursClient {

    private final WebClient webClient;

    public FedresursClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://bankrot.fedresurs.ru")
                .defaultHeader(HttpHeaders.USER_AGENT,
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
                .defaultHeader(HttpHeaders.ACCEPT,
                        "application/json, text/plain, */*")
                .defaultHeader(HttpHeaders.ACCEPT_LANGUAGE,
                        "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .defaultHeader(HttpHeaders.REFERER,
                        "https://bankrot.fedresurs.ru/arbitrmanagers")

                // 🔥 КРИТИЧНО
                .defaultHeader(HttpHeaders.COOKIE,
                        "_ym_uid=1768406400718164408; _ym_d=1768406400; " +
                                "qrator_ssid2=v2.0.1768488717.570.2efbc4ae0cdOXakG|pkcXKuXy5KeI3ccO|sw1FGkHaG1ARnLROoCjsLXyGQUhf2BPJcySnP2DrETISkx5FnJDQxbSQVHfXxsdM99POf7yNZkmBVYOnfyY3oA==-95QS+VipmxqybGjmLmkCSD9zURc=; " +
                                "_ym_isad=2; _ym_visorc=b")
                .build();
    }

    public AmsrosResponse loadPage(int limit, int offset) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/backend/amsros")
                        .queryParam("limit", limit)
                        .queryParam("offset", offset)
                        .build())
                .retrieve()
                .bodyToMono(AmsrosResponse.class)
                .block();
    }
}
