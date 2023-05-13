package wdefassio.io.keycloaksercurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import wdefassio.io.keycloaksercurity.model.User;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    Environment environment;

    @GetMapping("/status/check")
    public String status() {
        return "ok in port " + environment.getProperty("local.server.port");
    }

    @PreAuthorize("hasAnyRole('suporte') or #id == #jwt.subject")
    @DeleteMapping()
    public String deleteUser(@PathParam("id") String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user id : " + id + " and JWT subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.id == #jwt.subject")
    @GetMapping("/get")
    public User getUser(@PathParam("id") String id, @AuthenticationPrincipal Jwt jwt) {
        User user = new User(jwt.getClaimAsString("given_name"), jwt.getClaimAsString("family_name"),  jwt.getSubject());
        return user;
    }

    @Secured("ROLE_treinador")
    @GetMapping("/message")
    public String getMessage() {
        return "you got a message";
    }




}
