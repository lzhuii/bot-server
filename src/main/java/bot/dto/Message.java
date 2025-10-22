package bot.dto;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * QQ 消息
 *
 * @author hui
 * @since 1.0.0
 */
public record Message(
        // 消息 id
        String id,
        // 子频道 id
        String channelId,
        // 频道 id
        String guildId,
        // 群聊的 openid
        String groupOpenid,
        // 消息内容
        String content,
        // 消息创建时间
        OffsetDateTime timestamp,
        // 消息编辑时间
        OffsetDateTime editedTimestamp,
        // 是否是@全员消息
        boolean mentionEveryone,
        // 消息创建者
        User author,
        // 附件
        List<MessageAttachment> attachments,
        // embed
        List<MessageEmbed> embeds,
        // 消息中@的人
        List<User> mentions,
        // 消息创建者的member信息
        Member member,
        // ark消息对象
        MessageArk ark,
        // 用于消息间的排序，seq 在同一子频道中按从先到后的顺序递增，不同的子频道之间消息无法排序。(目前只在消息事件中有值，2022年8月1日 后续废弃)
        int seq,
        // 子频道消息 seq，用于消息间的排序，seq 在同一子频道中按从先到后的顺序递增，不同的子频道之间消息无法排序
        String seqInChannel,
        // 引用消息对象
        MessageReference messageReference
) {
    public record MessageAttachment(String url) {}

    public record MessageEmbed(String title, String prompt, MessageEmbedThumbnail thumbnail, List<MessageEmbedField> fields) {}

    public record MessageEmbedField(String name) {}

    public record MessageEmbedThumbnail(String url) {}

    public record MessageArk(int templateId, List<MessageArkKv> kv) {}

    public record MessageArkKv(String key, String value, List<MessageArkObj> obj) {}

    public record MessageArkObj(List<MessageArkObjKv> objKv) {}

    public record MessageArkObjKv(String key, String value) {}

    public record MessageReference(String messageId, boolean ignoreGetMessageError) {}

    public record MessageMarkdown(int templateId, MessageMarkdownParams params, String content) {}

    public record MessageMarkdownParams(String key, List<String> values) {}

}