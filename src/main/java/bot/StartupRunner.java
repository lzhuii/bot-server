package bot;

import bot.api.BotApi;
import bot.service.ChannelService;
import bot.service.GuildService;
import bot.service.MemberService;
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
    private final ChannelService channelService;
    private final MemberService memberService;

    public StartupRunner(BotApi botApi, GuildService guildService, ChannelService channelService, MemberService memberService) {
        this.botApi = botApi;
        this.guildService = guildService;
        this.channelService = channelService;
        this.memberService = memberService;
    }

    private void initGuilds() {
        botApi.getGuilds().subscribe(guilds -> guilds.forEach(guild -> {
            guildService.save(guild);
            initChannels(guild.id());
            initMembers(guild.id());
        }));
    }

    private void initChannels(String guildId) {
        botApi.getChannels(guildId).subscribe(channels -> channels.forEach(channelService::save));
    }

    private void initMembers(String guildId) {
        botApi.getMembers(guildId, "0", 100).subscribe(members -> members.forEach(memberService::save));
    }

    @Override
    public void run(String... args) {
        initGuilds();
    }
}
