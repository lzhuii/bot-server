package bot.dto;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 成员对象
 *
 * @author hui
 * @since 1.0.0
 */
public record Member(
        // 频道id
        String guildId,
        // 用户的频道基础信息，只有成员相关接口中会填充此信息
        User user,
        // 用户的昵称
        String nick,
        // 用户在频道内的身份组ID, 默认值可参考DefaultRoles
        List<String> roles,
        // 用户加入频道的时间
        OffsetDateTime joinedAt
) {}