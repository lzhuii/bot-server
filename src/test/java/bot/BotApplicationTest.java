package bot;

import bot.api.BotApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
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

    @Test
    void me() {
        StepVerifier.create(botApi.me()).expectNextMatches(user -> {
            try {
                log.info("{}", objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).verifyComplete();
    }
}