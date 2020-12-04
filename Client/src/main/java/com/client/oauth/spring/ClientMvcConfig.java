package com.client.oauth.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Arrays;

@Configuration
public class ClientMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("loginPage");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    // thymeleaf

    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    // end thymeleaf

    @Configuration
    @EnableOAuth2Client
    protected static class OAuthClientConfig {
        @Bean
        public OAuth2ProtectedResourceDetails resourceDetails() {
            final AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
            details.setClientId("lssClient");
            details.setClientSecret("lssSecret");
            details.setAccessTokenUri("http://localhost:8083/um-webapp-auth-server/oauth/token");
            details.setUserAuthorizationUri("http://localhost:8083/um-webapp-auth-server/oauth/authorize");
            details.setScope(Arrays.asList("read", "write"));
            details.setGrantType("authorization_code");
            details.setUseCurrentUri(true);
            return details;
        }

        @Bean
        public OAuth2RestTemplate redditRestTemplate(@Qualifier("oauth2ClientContext") final OAuth2ClientContext clientContext) {
            return new OAuth2RestTemplate(resourceDetails(), clientContext);
        }
    }
}

