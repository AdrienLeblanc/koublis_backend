package com.koublis.api.auth.services;

import com.koublis.api.auth.domain.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static com.koublis.api.auth.domain.Role.*;

@Service("guardService")
public class GuardService {

    public boolean isAuthenticated(Authentication authentication) {
        return hasAnyRole(authentication, ROLE_USER, ROLE_MODERATOR, ROLE_ADMIN);
    }

    public boolean isModerator(Authentication authentication) {
        return hasAnyRole(authentication, ROLE_MODERATOR, ROLE_ADMIN);
    }
    
    public boolean isAdmin(Authentication authentication) {
        return hasAnyRole(authentication, ROLE_ADMIN);
    }

    private static boolean hasAnyRole(Authentication authentication, Role... roles) {
        return Optional.of(authentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getAuthorities)
                .stream()
                .flatMap(Collection::stream)
                .anyMatch(authority ->
                        Arrays.stream(roles)
                                .map(Role::name)
                                .map(SimpleGrantedAuthority::new)
                                .anyMatch(authority::equals)
                );
    }
}