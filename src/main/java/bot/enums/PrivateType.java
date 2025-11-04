package bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 子频道私密类型枚举
 *
 * @author hui
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum PrivateType implements BaseEnum {
    /**
     * 公开频道
     */
    PUBLIC(0),
    /**
     * 群主管理员可见
     */
    OWNER_ADMIN(1),
    /**
     * 群主管理员+指定成员
     */
    OWNER_ADMIN_SPECIFY(2);

    /**
     * 枚举值
     */
    private final int code;

    /**
     * 根据值获取枚举
     *
     * @param code 枚举值
     * @return 枚举
     */
    public static PrivateType of(int code) {
        return BaseEnum.of(PrivateType.class, code);
    }
}
