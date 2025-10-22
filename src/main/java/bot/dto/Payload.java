package bot.dto;


/**
 * 通用数据结构
 *
 * @author hui
 * @since 1.0.0
 */
public record Payload<T>(
        // 事件id
        String id,
        // 指的是 opcode，参考连接维护
        Integer op,
        // 代表事件内容，不同事件类型的事件内容格式都不同，请注意识别。主要用在op为 0 Dispatch 的时候
        T d,
        // 下行消息都会有一个序列号，标识消息的唯一性，客户端需要再发送心跳的时候，携带客户端收到的最新的s
        Integer s,
        // 代表事件类型。主要用在op为 0 Dispatch 的时候
        String t
) {}

