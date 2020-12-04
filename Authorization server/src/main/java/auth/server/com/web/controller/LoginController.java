package auth.server.com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;

@Controller
@RequestMapping(value = {"/oa"})
public class LoginController {

    @GetMapping
    public String getPage(HttpServletRequest request, HttpServletResponse response, Principal principal)
            throws IOException {
        if (principal != null) { //depends on your security config, maybe you want to check the security context instead if you allow anonym access
            String redirect_uri = request.getParameter("redirect_uri");
            String state = request.getParameter("state");
            String clientId = request.getParameter("client_id");
            String scope = request.getParameter("scope");
            //here you must get all the other attributes thats needed for the authorize url
            if (redirect_uri == null) {
                redirect_uri = "http://localhost:8083/um-webapp-auth-server/login";
            }
            return "redirect:http://localhost:8083/um-webapp-auth-server/oauth/authorize?response_type=code&state=" + state + "&client_id=" + clientId + "&scope=" + scope + "&redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8");
        }
        return "login";
    }
}