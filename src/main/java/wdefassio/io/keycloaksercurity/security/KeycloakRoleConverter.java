package wdefassio.io.keycloaksercurity.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");

        if (Objects.isNull(realmAccess) || realmAccess.isEmpty()) {
            log.info("atentication is fail");
            return new ArrayList<>();
        }
        log.info("atentication is up");
        return ((List<String>) realmAccess.get("roles")).stream().map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
