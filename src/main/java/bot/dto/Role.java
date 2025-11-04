package bot.dto;

/**
 * 频道身份组对象
 *
 * @author hui
 * @since 1.0.0
 */
public record Role(
        // 身份组ID
        String id,
        // 名称
        String name,
        // ARGB的HEX十六进制颜色值转换后的十进制数值
        Integer color,
        // 是否在成员列表中单独展示: 0-否, 1-是
        Integer hoist,
        // 人数
        Integer number,
        // 成员上限
        Integer member_limit
) {}
