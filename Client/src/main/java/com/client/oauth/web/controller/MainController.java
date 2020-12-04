package com.client.oauth.web.controller;

import com.client.oauth.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
class MainController {

    private final OAuth2RestTemplate restTemplate;

    public MainController(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        restTemplate.setAccessTokenProvider(new AuthorizationCodeAccessTokenProvider());
    }

    //

    @GetMapping
    public ModelAndView list() {
        final List<User> users = restTemplate.getForObject("http://localhost:8081/um-webapp-resource-server/api/users", List.class);
        return new ModelAndView("list", "users", users);
    }

    @PostMapping
    public String create(User user) {
        final MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("email", user.getEmail());
        param.add("password", user.getPassword());
        final User created = restTemplate.postForObject("http://localhost:8081/um-webapp-resource-server/api/users", param, User.class);
        System.out.println(created);
        return "redirect:/user";
    }

    @GetMapping(params = "form")
    public String createForm(@ModelAttribute final User user) {
        return "form";
    }

}