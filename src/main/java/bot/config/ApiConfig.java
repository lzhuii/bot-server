package bot.config;


import bot.api.BotApi;
import bot.api.TokenApi;
import bot.filter.TokenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
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
    private final ObjectMapper objectMapper;

    public ApiConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private <T> T createClient(Class<T> clazz, String baseUrl, ExchangeFilterFunction... filters) {
        WebClient.Builder builder = WebClient.builder()
                .baseUrl(baseUrl)
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                });
        for (ExchangeFilterFunction filter : filters) {
            builder.filter(filter);
        }
        WebClient client = builder.build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(clazz);
    }

    @Bean
    TokenApi tokenApi() {
        return createClient(TokenApi.class, "https://bots.qq.com");
    }

    @Bean
    BotApi botApi(TokenFilter tokenFilter) {
        return createClient(BotApi.class, "https://api.sgroup.qq.com", tokenFilter);
    }
}
