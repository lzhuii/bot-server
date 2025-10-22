package bot.dto.request;

/**
 * webhook回调验证请求参数
 *
 * @author hui
 * @since 1.0.0
 */
public record VerifyRequest(
        // 需要计算签名的字符串
        String plainToken,
        // 计算签名使用时间戳
        String eventTs
) {}
