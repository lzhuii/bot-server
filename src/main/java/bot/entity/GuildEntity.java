package bot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author hui
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "guild")
@EntityListeners(AuditingEntityListener.class)
public class GuildEntity {
    /**
     * 频道ID
     */
    @Id
    private String id;
    /**
     * 频道名称
     */
    private String name;
    /**
     * 频道头像地址
     */
    private String icon;
    /**
     * 创建人用户ID
     */
    private String ownerId;
    /**
     * 当前人是否是创建人
     */
    private Boolean owner;
    /**
     * 成员数
     */
    private Integer memberCount;
    /**
     * 最大成员数
     */
    private Integer maxMembers;
    /**
     * 描述
     */
    private String description;
    /**
     * 加入时间
     */
    private LocalDateTime joinedAt;
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
