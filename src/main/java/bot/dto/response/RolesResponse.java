package bot.dto.response;

import bot.dto.Role;

import java.util.List;

/**
 * @author hui
 * @since 1.0.0
 */
public record RolesResponse(
        String guildId,
        List<Role> roles,
        String roleNumLimit
) {}
