package bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 启动类
 *
 * @author hui
 * @since 1.0.0
 */
@EnableJpaAuditing
@SpringBootApplication
public class BotApplication {
    static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }
}