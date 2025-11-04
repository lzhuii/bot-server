package bot.dto;

/**
 * @author hui
 * @since 1.0.0
 */
public record ApiPermission(
        String path,
        String method,
        String desc,
        Integer authStatus
) {}
