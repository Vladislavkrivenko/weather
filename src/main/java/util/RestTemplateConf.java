package util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class RestTemplateConf {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
