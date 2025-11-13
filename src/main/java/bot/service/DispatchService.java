package bot.service;

import bot.api.BotApi;
import bot.dto.Message;
import bot.dto.Payload;
import bot.dto.request.MessageRequest;
import bot.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 消息分发服务
 *
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@Service
public class DispatchService {
    private final JsonUtil jsonUtil;
    private final BotApi botApi;
    private final ChatModel chatModel;

    public DispatchService(
            JsonUtil jsonUtil,
            BotApi botApi,
            ChatModel chatModel
    ) {
        this.jsonUtil = jsonUtil;
        this.botApi = botApi;
        this.chatModel = chatModel;
    }

    public void dispatch(Payload<JsonNode> payload) {
        Message message = jsonUtil.readValue(payload.d().toString(), Message.class);
        chatModel.stream(message.content())
                .collect(StringBuilder::new, StringBuilder::append)
                .flatMap(response -> {
                    MessageRequest request = MessageRequest.builder()
                            .content(response.toString())
                            .msgId(message.id())
                            .build();
                    // 根据消息类型选择不同的消息发送方法
                    return switch (payload.t()) {
                        case "C2C_MESSAGE_CREATE" -> botApi.sendToUser(message.author().id(), request);
                        case "GROUP_AT_MESSAGE_CREATE" -> botApi.sendToGroup(message.groupOpenid(), request);
                        case "AT_MESSAGE_CREATE" -> botApi.sendToChannel(message.channelId(), request);
                        case "DIRECT_MESSAGE_CREATE" -> botApi.sendToDirect(message.guildId(), request);
                        default -> Mono.empty();
                    };
                }).subscribe();
    }
}
