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
    /**
     * 用户id
     */
    @Id
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头像地址
     */
    private String avatar;
    /**
     * 是否是机器人
     */
    private Boolean bot;
    /**
     * 特殊关联应用的 openid，需要特殊申请并配置后才会返回
     */
    private String unionOpenid;
    /**
     * 机器人关联的互联应用的用户信息，与union_openid关联的应用是同一个
     */
    private String unionUserAccount;
    /**
     * 用户的昵称
     */
    private String nick;
    /**
     * 用户在频道内的身份组ID, 默认值可参考DefaultRoles
     */
    private List<String> roles;
    /**
     * 用户加入频道的时间
     */
    private LocalDateTime joinedAt;
    /**
     * 频道id
     */
    private String guildId;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
