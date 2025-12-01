package bot.service;

import bot.repository.GuildRepository;
import bot.dto.Guild;
import bot.entity.GuildEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@Service
public class GuildService {
    private final GuildRepository repository;

    public GuildService(GuildRepository repository) {
        this.repository = repository;
    }

    public void save(Guild guild) {
        log.info("save guild: {}", guild.id());
        GuildEntity entity = guild.toEntity();
        repository.save(entity);
    }
}
