package wdefassio.io.keycloaksercurity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hacker")
public class Hacker {
    @GetMapping
    public String isHacker(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaimAsString("name") + " you or a hacker!";
    }
}
