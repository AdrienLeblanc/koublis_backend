package com.koublis.configuration.security.jwt;

import com.koublis.configuration.exceptions.Reason;
import com.koublis.configuration.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static org.springframework.util.StringUtils.hasText;

@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {

    private static final String ALGORITHM = "HmacSHA256";
    private final JwtPropertiesConfiguration jwtPropertiesConfiguration;

    @Bean
    public SecretKey secretKey() {
        val secretKeyString = jwtPropertiesConfiguration.getJwtSecret();
        if (!hasText(secretKeyString)) {
            throw new ResourceNotFoundException(Reason.SECRET_KEY_NOT_FOUND);
        }
        val decodedKey = Base64.getDecoder().decode(secretKeyString);
        return new SecretKeySpec(decodedKey, ALGORITHM);
    }

    public long getJwtExpiration() {
        return jwtPropertiesConfiguration.getJwtExpiration().toHours();
    }
}
