package bot.dto.request;

/**
 * 获取调用凭证请求参数
 *
 * @author hui
 * @since 1.0.0
 */
public record TokenRequest(
        // 机器人ID
        String appId,
        // 机器人密钥
        String clientSecret
) {}