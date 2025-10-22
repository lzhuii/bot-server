package bot.dto;

/**
 * 用户
 *
 * @author hui
 * @since 2024-09-29
 */
public record User(
        // 用户id
        String id,
        // 用户名
        String username,
        // 用户头像地址
        String avatar,
        // 是否是机器人
        Boolean bot,
        // 特殊关联应用的 openid，需要特殊申请并配置后才会返回
        String unionOpenid,
        // 机器人关联的互联应用的用户信息，与union_openid关联的应用是同一个
        String unionUserAccount
) {}