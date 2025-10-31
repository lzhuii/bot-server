package bot.dto;

/**
 * 子频道对象
 *
 * @author hui
 * @since 1.0.0
 */
public record Channel(
        // 子频道 id
        String id,
        // 频道 id
        String guild_id,
        // 子频道名
        String name,
        // 子频道类型 ChannelType
        Integer type,
        // 子频道子类型 ChannelSubType
        Integer sub_type,
        // 排序值，具体请参考 有关 position 的说明
        Integer position,
        // 所属分组 id，仅对子频道有效，对 子频道分组（ChannelType=4） 无效
        String parent_id,
        // 创建人 id
        String owner_id,
        // 子频道私密类型 PrivateType
        Integer private_type,
        // 子频道发言权限 SpeakPermission
        Integer speak_permission,
        // 用于标识应用子频道应用类型，仅应用子频道时会使用该字段，具体定义请参考 应用子频道的应用类型
        String application_id,
        // 用户拥有的子频道权限 Permissions
        String permissions
) {}
