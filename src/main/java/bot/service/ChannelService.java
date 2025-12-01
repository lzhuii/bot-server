package bot.service;

import bot.dao.ChannelRepository;
import bot.dto.Channel;
import bot.entity.ChannelEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@Service
public class ChannelService {
    private final ChannelRepository repository;

    public ChannelService(ChannelRepository repository) {
        this.repository = repository;
    }

    public void save(Channel channel) {
        log.info("save channel: {}", channel.id());
        ChannelEntity entity = channel.toEntity();
        repository.save(entity);
    }
}
