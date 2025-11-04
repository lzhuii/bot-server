package bot.enums;

/**
 * 基础枚举接口
 *
 * @author hui
 * @since 1.0.0
 */
public interface BaseEnum {
    /**
     * 枚举值
     *
     * @return 枚举值
     */
    int getCode();

    /**
     * 根据值获取枚举
     *
     * @param enumClass 枚举类
     * @param code      枚举值
     * @param <T>       枚举类型
     * @return 枚举
     */
    static <T extends Enum<T> & BaseEnum> T of(Class<T> enumClass, int code) {
        for (T value : enumClass.getEnumConstants()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
