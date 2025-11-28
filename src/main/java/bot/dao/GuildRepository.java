package bot.dao;

import bot.entity.GuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hui
 * @since 1.0.0
 */
public interface GuildRepository extends JpaRepository<GuildEntity, String> {
}
