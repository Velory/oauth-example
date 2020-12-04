package com.client.oauth;

import com.client.oauth.spring.ClientMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{OauthApplication.class, ClientMvcConfig.class}, args);
    }

}
