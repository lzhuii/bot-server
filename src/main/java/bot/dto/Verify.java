package bot.dto;

/**
 * webhook回调验证
 *
 * @author hui
 * @since 1.0.0
 */
public record Verify(
        // 需要计算签名的字符串
        String plainToken,
        // 签名
        String signature
) {}
