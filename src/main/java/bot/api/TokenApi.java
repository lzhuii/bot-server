package bot.api;

import bot.dto.Token;
import bot.dto.request.TokenRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

/**
 * QQ Token API接口
 *
 * @author hui
 * @since 2024-09-29
 */
public interface TokenApi {
    @PostExchange("/app/getAppAccessToken")
    Mono<Token> getToken(@RequestBody TokenRequest request);
}
