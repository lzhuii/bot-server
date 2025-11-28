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
    private final GuildRepository repository;

    public GuildService(GuildRepository repository) {
        this.repository = repository;
    }

    public void save(GuildEntity entity) {
        repository.save(entity);
    }
}
