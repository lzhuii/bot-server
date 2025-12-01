package bot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hui
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity {
    @Id
    private String id;
    private String username;
    private String avatar;
    private Boolean bot;
    private String unionOpenid;
    private String unionUserAccount;
    private String nick;
    private List<String> roles;
    private LocalDateTime joinedAt;
    private String guildId;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
