package bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hui
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ChannelType {
    // 文字子频道
    TEXT(0),
    // 语音子频道
    VOICE(2),
    // 子频道分组
    GROUP(4),
    // 直播子频道
    LIVE(10005),
    // 应用子频道
    APP(10005),
    // 论坛子频道
    FORUM(10005);

    private final int code;

    /**
     * 根据值获取枚举
     *
     * @param code 枚举值
     * @return 枚举
     */
    public static ChannelType of(int code) {
        for (ChannelType value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknow ChannelType:" + code);
    }
}
