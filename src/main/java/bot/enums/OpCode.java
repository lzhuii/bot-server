package bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * opcode 枚举
 *
 * @author hui
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum OpCode {
    // 服务端进行消息推送
    DISPATCH(0),
    // 客户端或服务端发送心跳
    HEARTBEAT(1),
    // 客户端发送鉴权
    IDENTIFY(2),
    // 客户端恢复连接
    RESUME(6),
    // 服务端通知客户端重新连接
    RECONNECT(7),
    // 当 identify 或 resume 的时候，如果参数有错，服务端会返回该消息
    INVALID_SESSION(9),
    // 当客户端与网关建立 ws 连接之后，网关下发的第一条消息
    HELLO(10),
    // 当发送心跳成功之后，就会收到该消息
    HEARTBEAT_ACK(11),
    // 仅用于 http 回调模式的回包，代表机器人收到了平台推送的数据
    HTTP_CALLBACK_ACK(12),
    // 开放平台对机器人服务端进行验证
    VERIFY(13);

    private final int code;

    /**
     * 根据值获取枚举
     *
     * @param code 枚举值
     * @return 枚举
     */
    public static OpCode of(int code) {
        for (OpCode value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown opcode " + code);
    }
}
