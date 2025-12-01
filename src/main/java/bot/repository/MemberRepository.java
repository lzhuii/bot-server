package bot.repository;

import bot.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hui
 * @since 1.0.0
 */
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
}
