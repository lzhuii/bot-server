package bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 调用凭证
 *
 * @author hui
 * @since 2024-09-29
 */
public record Token(
        // 获取到的凭证
        @JsonProperty("access_token")
        String accessToken,
        // 凭证有效时间，单位：秒。目前是7200秒之内的值
        @JsonProperty("expires_in")
        Long expiresIn
) {}