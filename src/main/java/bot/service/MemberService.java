package bot.service;

import bot.dto.Member;
import bot.entity.MemberEntity;
import bot.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public void save(Member member) {
        log.info("save member: {}", member.user().id());
        MemberEntity entity = member.toEntity();
        repository.save(entity);
    }
}
