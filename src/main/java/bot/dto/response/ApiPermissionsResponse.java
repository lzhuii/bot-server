package bot.dto.response;

import bot.dto.ApiPermission;

import java.util.List;

/**
 * @author hui
 * @since 1.0.0
 */
public record ApiPermissionsResponse(
        List<ApiPermission> apis
) {}
