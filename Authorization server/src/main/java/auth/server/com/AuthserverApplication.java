package auth.server.com;

import auth.server.com.spring.AuthSecurityConfig;
import auth.server.com.spring.AuthorizationServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AuthserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{AuthserverApplication.class, AuthorizationServerConfig.class, AuthSecurityConfig.class}, args);
    }

}
