package com.kodascript.oauth2sosial;

import com.kodascript.oauth2sosial.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Oauth2sosialApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2sosialApplication.class, args);
    }

}
