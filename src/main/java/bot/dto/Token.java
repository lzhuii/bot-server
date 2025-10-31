package bot.dto;

/**
 * 调用凭证
 *
 * @author hui
 * @since 1.0.0
 */
public record Token(
        // 获取到的凭证
        String accessToken,
        // 凭证有效时间，单位：秒。目前是7200秒之内的值
        Long expiresIn
) {}