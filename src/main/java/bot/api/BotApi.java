package bot.api;

import bot.dto.request.MessageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

/**
 * QQ 机器人 API 接口
 *
 * @author hui
 * @since 1.0.0
 */
public interface BotApi {
    @PostExchange("/v2/users/{userId}/messages")
    Mono<String> sendToUser(@PathVariable String userId, @RequestBody MessageRequest request);

    @PostExchange("/channels/{channelId}/messages")
    Mono<String> sendToChannel(@PathVariable String channelId, @RequestBody MessageRequest request);

    @PostExchange("/v2/groups/{groupOpenid}/messages")
    Mono<String> sendToGroup(@PathVariable String groupOpenid, @RequestBody MessageRequest request);

    @PostExchange("/dms/{guildId}/messages")
    Mono<String> sendToDirect(@PathVariable String guildId, @RequestBody MessageRequest request);
}
