package bot;

import bot.api.BotApi;
import bot.entity.GuildEntity;
import bot.service.GuildService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@Component
public class StartupRunner implements CommandLineRunner {
    private final BotApi botApi;
    private final GuildService guildService;

    public StartupRunner(BotApi botApi, GuildService guildService) {
        this.botApi = botApi;
        this.guildService = guildService;
    }

    private void initGuilds() {
        botApi.getGuilds().subscribe(guilds -> guilds.forEach(guild -> {
            log.info("guild: {}", guild.id());
            GuildEntity entity = GuildEntity.builder()
                    .id(guild.id())
                    .name(guild.name())
                    .icon(guild.icon())
                    .ownerId(guild.ownerId())
                    .owner(guild.owner())
                    .memberCount(guild.memberCount())
                    .maxMembers(guild.maxMembers())
                    .description(guild.description())
                    .joinedAt(guild.joinedAt().toLocalDateTime())
                    .build();
            guildService.save(entity);
        }));
    }

    @Override
    public void run(String... args) {
        initGuilds();
    }
}
