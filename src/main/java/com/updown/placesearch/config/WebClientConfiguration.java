package com.updown.placesearch.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {

    // 카카오 API 호출에 필요한 설정
    public static final int CONNECT_TIMEOUT_MILLI_SECOND = 5000;
    public static final int READ_TIMEOUT_SECOND = 3;
    @Value("${kakao.api.url}")
    private String kakaoApiUrl;
    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    // 네이버 API 호출에 필요한 설정
    public static final String X_NAVER_CLIENT_ID_HEADER = "X-Naver-Client-Id";
    public static final String X_NAVER_CLIENT_SECRET_HEADER = "X-Naver-Client-Secret";

    @Value("${naver.api.url}")
    private String naverApiUrl;

    @Value("${naver.api.client-id}")
    private String naverClientId;

    @Value("${naver.api.client-secret}")
    private String naverClientSecret;

    @Bean
    public WebClient kakaoApiWebClient() {
        return WebClient.builder()
                .baseUrl(kakaoApiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, kakaoApiKey)
                .build();
    }

    @Bean
    public WebClient naverApiWebClient() {
        return WebClient.builder()
                .baseUrl(naverApiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(X_NAVER_CLIENT_ID_HEADER, naverClientId)
                .defaultHeader(X_NAVER_CLIENT_SECRET_HEADER, naverClientSecret)
                .build();
    }

}
