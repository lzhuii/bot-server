package bot.dto;

import java.time.OffsetDateTime;

/**
 * 频道
 *
 * @author hui
 * @since 1.0.0
 */
public record Guild(
        // 频道ID
        String id,
        // 频道名称
        String name,
        // 频道头像地址
        String icon,
        // 创建人用户ID
        String ownerId,
        // 当前人是否是创建人
        Boolean owner,
        // 成员数
        Integer memberCount,
        // 最大成员数
        Integer maxMembers,
        // 描述
        String description,
        // 加入时间
        OffsetDateTime joinedAt
) {}
