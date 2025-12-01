package bot.dto;

import bot.entity.ChannelEntity;
import org.springframework.beans.BeanUtils;

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
        String guildId,
        // 子频道名
        String name,
        // 子频道类型 ChannelType
        Integer type,
        // 子频道子类型 ChannelSubType
        Integer subType,
        // 排序值，具体请参考 有关 position 的说明
        Integer position,
        // 所属分组 id，仅对子频道有效，对 子频道分组（ChannelType=4） 无效
        String parentId,
        // 创建人 id
        String ownerId,
        // 子频道私密类型 PrivateType
        Integer privateType,
        // 子频道发言权限 SpeakPermission
        Integer speakPermission,
        // 用于标识应用子频道应用类型，仅应用子频道时会使用该字段，具体定义请参考 应用子频道的应用类型
        String applicationId,
        // 用户拥有的子频道权限 Permissions
        String permissions
) {
    public ChannelEntity toEntity() {
        ChannelEntity entity = new ChannelEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
