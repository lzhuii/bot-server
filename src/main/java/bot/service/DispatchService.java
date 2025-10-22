package bot.service;

import bot.api.BotApi;
import bot.dto.Message;
import bot.dto.Payload;
import bot.dto.request.MessageRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DispatchService {
    private final ObjectMapper objectMapper;
    private final BotApi botApi;

    public DispatchService(ObjectMapper objectMapper, BotApi botApi) {
        this.objectMapper = objectMapper;
        this.botApi = botApi;
    }

    @SneakyThrows
    public void dispatch(Payload<JsonNode> payload) {
        log.info("收到用户消息");
        Message message = objectMapper.readValue(payload.d().toString(), Message.class);
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
        }
    }
}
