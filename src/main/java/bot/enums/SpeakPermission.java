package bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 子频道发言权限
 *
 * @author hui
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum SpeakPermission implements BaseEnum {
    /**
     * 无效类型
     */
    INVALID(0),
    /**
     * 所有人
     */
    ALL(1),
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
    public static SpeakPermission of(int code) {
        return BaseEnum.of(SpeakPermission.class, code);
    }
}
