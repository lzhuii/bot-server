package bot.dto;

/**
 * <a href="https://bot.q.qq.com/wiki/develop/api-v2/server-inter/channel/manage/user/model.html#user">用户对象(User)</a>
 *
 * @author hui
 * @since 1.0.0
 */
public record User(
        String id,
        String username,
        String avatar,
        Boolean bot,
        String unionOpenid,
        String unionUserAccount
) {}