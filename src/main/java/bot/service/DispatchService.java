package bot.service;

import bot.api.BotApi;
import bot.dto.Message;
import bot.dto.Payload;
import bot.dto.request.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

/**
 * 消息分发服务
 *
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@Service
public class DispatchService {
    private final JsonMapper jsonMapper;
    private final BotApi botApi;

    public DispatchService(JsonMapper jsonMapper, BotApi botApi) {
        this.jsonMapper = jsonMapper;
        this.botApi = botApi;
    }

    public void dispatch(Payload<JsonNode> payload) {
        Message message = jsonMapper.readValue(payload.d().toString(), Message.class);
        MessageRequest request = MessageRequest.builder()
                .content(message.content())
                .msgId(message.id())
                .build();
        // 根据消息类型选择不同的消息发送方法
        switch (payload.t()) {
            case "C2C_MESSAGE_CREATE" -> botApi.sendToUser(message.author().id(), request).subscribe();
            case "GROUP_AT_MESSAGE_CREATE" -> botApi.sendToGroup(message.groupOpenid(), request).subscribe();
            case "AT_MESSAGE_CREATE" -> botApi.sendToChannel(message.channelId(), request).subscribe();
            case "DIRECT_MESSAGE_CREATE" -> botApi.sendToDirect(message.guildId(), request).subscribe();
            default -> Mono.empty().subscribe();
        }
    }
}
