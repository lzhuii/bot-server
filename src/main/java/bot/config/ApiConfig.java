package bot.config;


import bot.api.BotApi;
import bot.api.TokenApi;
import bot.filter.TokenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
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
    TokenApi tokenApi(ObjectMapper objectMapper) {
        WebClient client = WebClient.builder()
                .baseUrl("https://bots.qq.com")
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                })
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(TokenApi.class);
    }

    @Bean
    BotApi botApi(ObjectMapper objectMapper, TokenFilter tokenFilter) {
        WebClient client = WebClient.builder()
                .baseUrl("https://api.sgroup.qq.com")
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                })
                .filter(tokenFilter)
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(BotApi.class);
    }
}
