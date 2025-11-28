package bot;

import bot.api.BotApi;
import bot.entity.ChannelEntity;
import bot.entity.GuildEntity;
import bot.service.ChannelService;
import bot.service.GuildService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    private final ChannelService channelService;

    public StartupRunner(BotApi botApi, GuildService guildService, ChannelService channelService) {
        this.botApi = botApi;
        this.guildService = guildService;
        this.channelService = channelService;
    }

    private void initGuilds() {
        botApi.getGuilds().subscribe(guilds -> guilds.forEach(guild -> {
            log.info("save guild: {}", guild.id());
            GuildEntity entity = new GuildEntity();
            BeanUtils.copyProperties(guild, entity);
            entity.setJoinedAt(guild.joinedAt().toLocalDateTime());
            guildService.save(entity);
            initChannels(guild.id());
        }));
    }

    private void initChannels(String guildId) {
        botApi.getChannels(guildId).subscribe(channels -> channels.forEach(channel -> {
            log.info("save channel: {}", channel.id());
            ChannelEntity entity = new ChannelEntity();
            BeanUtils.copyProperties(channel, entity);
            channelService.save(entity);
        }));
    }

    @Override
    public void run(String... args) {
        initGuilds();
    }
}
