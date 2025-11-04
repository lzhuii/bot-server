package bot.api;

import bot.dto.Channel;
import bot.dto.Guild;
import bot.dto.Member;
import bot.dto.User;
import bot.dto.request.MessageRequest;
import bot.dto.response.ApiPermissionsResponse;
import bot.dto.response.RolesResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * QQ 机器人 API 接口
 *
 * @author hui
 * @since 1.0.0
 */
public interface BotApi {
    /**
     * 获取用户详情
     *
     * @return User
     */
    @GetExchange("/users/@me")
    Mono<User> getUserInfo();

    /**
     * 获取用户频道列表
     *
     * @return Guild列表
     */
    @GetExchange("/users/@me/guilds")
    Mono<List<Guild>> getGuilds();

    /**
     * 获取频道详情
     *
     * @param guildId 频道ID
     * @return Guild
     */
    @GetExchange("/guilds/{guildId}")
    Mono<Guild> getGuild(@PathVariable String guildId);

    /**
     * 获取子频道列表
     *
     * @param guildId 频道ID
     * @return Channel列表
     */
    @GetExchange("/guilds/{guildId}/channels")
    Mono<List<Channel>> getChannels(@PathVariable String guildId);

    /**
     * 获取子频道详情
     *
     * @param channelId 子频道ID
     * @return Channel
     */
    @GetExchange("/channels/{channelId}")
    Mono<Channel> getChannel(@PathVariable String channelId);

    /**
     * 获取频道成员列表
     *
     * @param guildId 频道ID
     * @param after   第一个ID
     * @param limit   条数
     * @return Member列表
     */
    @GetExchange("/guilds/{guildId}/members")
    Mono<List<Member>> getMembers(@PathVariable String guildId, @RequestParam String after, @RequestParam Integer limit);

    /**
     * 获取频道成员详情
     *
     * @param guildId 频道ID
     * @param userId  用户ID
     * @return Member
     */
    @GetExchange("/guilds/{guildId}/members/{userId}")
    Mono<Member> getMember(@PathVariable String guildId, @PathVariable String userId);

    /**
     * 获取频道身份组列表
     *
     * @param guildId 频道ID
     * @return RolesResponse
     */
    @GetExchange("/guilds/{guildId}/roles")
    Mono<RolesResponse> getRoles(@PathVariable String guildId);

    /**
     * 获取机器人在频道可用权限列表
     *
     * @param guildId 频道ID
     * @return Role
     */
    @GetExchange("/guilds/{guildId}/api_permission")
    Mono<ApiPermissionsResponse> getApiPermissions(@PathVariable String guildId);

    @PostExchange("/v2/users/{userId}/messages")
    Mono<String> sendToUser(@PathVariable String userId, @RequestBody MessageRequest request);

    @PostExchange("/channels/{channelId}/messages")
    Mono<String> sendToChannel(@PathVariable String channelId, @RequestBody MessageRequest request);

    @PostExchange("/v2/groups/{groupOpenid}/messages")
    Mono<String> sendToGroup(@PathVariable String groupOpenid, @RequestBody MessageRequest request);

    @PostExchange("/dms/{guildId}/messages")
    Mono<String> sendToDirect(@PathVariable String guildId, @RequestBody MessageRequest request);
}
