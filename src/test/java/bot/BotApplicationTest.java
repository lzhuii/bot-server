package bot;

import bot.api.BotApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

/**
 * @author hui
 * @since 2025-10-27
 */
@Slf4j
@SpringBootTest
class BotApplicationTest {
    @Resource
    BotApi botApi;
    @Resource
    ObjectMapper objectMapper;

    @SneakyThrows
    <T> boolean log(T object) {
        log.info("{}", objectMapper.writeValueAsString(object));
        return true;
    }

    @Test
    void getUserInfo() {
        StepVerifier.create(botApi.getUserInfo()).expectNextMatches(this::log).verifyComplete();
    }

    @Test
    void getGuilds() {
        StepVerifier.create(botApi.getGuilds()).expectNextMatches(this::log).verifyComplete();
    }
}