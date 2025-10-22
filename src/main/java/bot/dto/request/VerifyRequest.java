package bot.dto.request;

/**
 * Webhook 回调验证
 *
 * @author hui
 * @since 2025-02-08
 */
public record VerifyRequest(
        // 需要计算签名的字符串
        String plainToken,
        // 计算签名使用时间戳
        String eventTs
) {}
