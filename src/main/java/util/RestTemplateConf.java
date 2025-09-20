package util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {"service", "dao", "mapper"})
public class RestTemplateConf {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
