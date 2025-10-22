package bot.dto;

public record Verify(
        // 需要计算签名的字符串
        String plainToken,
        // 签名
        String signature
) {}
