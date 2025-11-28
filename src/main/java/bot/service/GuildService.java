package bot.service;

import bot.dao.GuildRepository;
import bot.entity.GuildEntity;
import org.springframework.stereotype.Service;

/**
 * @author hui
 * @since 1.0.0
 */
@Service
public class GuildService {
    private final GuildRepository guildRepository;

    public GuildService(GuildRepository guildRepository) {
        this.guildRepository = guildRepository;
    }

    public void save(GuildEntity entity) {
        guildRepository.save(entity);
    }
}
