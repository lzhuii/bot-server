package bot.dao;

import bot.entity.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hui
 * @since 1.0.0
 */
public interface ChannelRepository extends JpaRepository<ChannelEntity, String> {
}
