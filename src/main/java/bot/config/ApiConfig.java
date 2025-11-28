package bot.config;


import bot.api.BotApi;
import bot.api.TokenApi;
import bot.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.JacksonJsonDecoder;
import org.springframework.http.codec.json.JacksonJsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tools.jackson.databind.json.JsonMapper;

/**
 * API配置类
 *
 * @author hui
 * @since 1.0.0
 */
@Configuration
public class ApiConfig {
    private final JsonMapper jsonMapper;

    public ApiConfig(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    private <T> T createClient(Class<T> clazz, String baseUrl, ExchangeFilterFunction... filters) {
        WebClient.Builder builder = WebClient.builder()
                .baseUrl(baseUrl)
                .codecs(configurer -> {
                    configurer.defaultCodecs().jacksonJsonDecoder(new JacksonJsonDecoder(jsonMapper));
                    configurer.defaultCodecs().jacksonCborEncoder(new JacksonJsonEncoder(jsonMapper));
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
