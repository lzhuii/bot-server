package bot.service;

import bot.dao.ChannelRepository;
import bot.entity.ChannelEntity;
import org.springframework.stereotype.Service;

/**
 * @author hui
 * @since 1.0.0
 */
@Service
public class ChannelService {
    private final ChannelRepository repository;

    public ChannelService(ChannelRepository repository) {
        this.repository = repository;
    }

    public void save(ChannelEntity entity) {
        repository.save(entity);
    }
}
