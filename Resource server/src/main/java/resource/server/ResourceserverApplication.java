package resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import resource.server.spring.ResourceServerConfig;

@SpringBootApplication
public class ResourceserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{ResourceserverApplication.class, ResourceServerConfig.class}, args);
    }

}
