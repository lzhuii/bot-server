package bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hui
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum RoleIds implements BaseEnum {
    ;

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
    public static RoleIds of(int code) {
        return BaseEnum.of(RoleIds.class, code);
    }
}
