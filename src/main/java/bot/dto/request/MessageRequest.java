package bot.dto.request;

import bot.dto.Message;
import lombok.Builder;

@Builder
public record MessageRequest(
        // 消息类型：0 是文本，2 是 markdown，3 ark，4 embed，7 media 富媒体
        Integer msgType,
        // 文本内容
        String content,
        // Markdown对象
        Message.MessageMarkdown markdown,
        // Keyboard对象
        String keyboard,
        // Ark对象
        Message.MessageArk ark,
        // 富媒体对象
        String media,
        // 消息引用对象
        Message.MessageReference messageReference,
        // 前置收到的事件 ID，用于发送被动消息
        String eventId,
        // 前置收到的用户发送过来的消息 ID，用于发送被动（回复）消息
        String msgId,
        // 回复消息的序号，与 msg_id 联合使用
        Integer msgSeq
) {}
