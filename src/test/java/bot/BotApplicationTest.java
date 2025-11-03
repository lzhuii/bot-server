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
    public static final String GUILD_ID = System.getenv("GUILD_ID");
    public static final String CHANNEL_ID = System.getenv("CHANNEL_ID");
    public static final String USER_ID = System.getenv("USER_ID");

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

    @Test
    void getGuild() {
        StepVerifier.create(botApi.getGuild(GUILD_ID)).expectNextMatches(this::log).verifyComplete();
    }

    @Test
    void getChannels() {
        StepVerifier.create(botApi.getChannels(GUILD_ID)).expectNextMatches(this::log).verifyComplete();
    }

    @Test
    void getChannel() {
        StepVerifier.create(botApi.getChannel(CHANNEL_ID)).expectNextMatches(this::log).verifyComplete();
    }

    @Test
    void getMembers() {
        StepVerifier.create(botApi.getMembers(GUILD_ID, "0", 100)).expectNextMatches(this::log).verifyComplete();
    }

    @Test
    void getMember() {
        StepVerifier.create(botApi.getMember(GUILD_ID, USER_ID)).expectNextMatches(this::log).verifyComplete();
    }
}