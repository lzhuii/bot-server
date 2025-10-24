package bot.filter;

import bot.api.TokenApi;
import bot.dto.request.TokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * QQ Token 过滤器
 *
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@Component
public class TokenFilter implements ExchangeFilterFunction {
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final TokenApi tokenApi;
    @Value("${bot.app-id}")
    private String appId;
    @Value("${bot.app-secret}")
    private String appSecret;

    public TokenFilter(ReactiveRedisTemplate<String, String> redisTemplate, TokenApi tokenApi) {
        this.redisTemplate = redisTemplate;
        this.tokenApi = tokenApi;
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        return redisTemplate.opsForValue()
                .get(appId)
                .switchIfEmpty(Mono.defer(this::getAccessToken))
                .doOnNext(accessToken -> log.info("使用AccessToken"))
                .flatMap(accessToken -> {
                    String authorization = String.format("QQBot %s", accessToken);
                    ClientRequest newRequest = ClientRequest.from(request)
                            .header("Authorization", authorization)
                            .build();
                    return next.exchange(newRequest);
                });
    }

    /**
     * 获取 AccessToken
     *
     * @return AccessToken
     */
    private Mono<String> getAccessToken() {
        TokenRequest request = new TokenRequest(appId, appSecret);
        return tokenApi.getToken(request)
                .doOnNext(token -> log.info("获取AccessToken"))
                .flatMap(token -> {
                    String accessToken = token.accessToken();
                    Long expiresIn = token.expiresIn();
                    Duration duration = Duration.ofSeconds(expiresIn);
                    return redisTemplate.opsForValue()
                            .set(appId, accessToken, duration)
                            .thenReturn(accessToken);
                });
    }
}