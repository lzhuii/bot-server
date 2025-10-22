package bot.config;


import bot.api.BotApi;
import bot.api.TokenApi;
import bot.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * API配置类
 *
 * @author hui
 * @since 1.0.0
 */
@Configuration
public class ApiConfig {
    @Bean
    TokenApi tokenApi() {
        WebClient client = WebClient.builder()
                .baseUrl("https://bots.qq.com")
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(TokenApi.class);
    }

    @Bean
    BotApi botApi(TokenFilter tokenFilter) {
        WebClient client = WebClient.builder()
                .baseUrl("https://api.sgroup.qq.com")
                .filter(tokenFilter)
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(BotApi.class);
    }
}
