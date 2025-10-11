package br.com.link_box_core_back_springboot.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class JWTProvider {

    public String generateToken(UUID id) {

        Dotenv dotenv = Dotenv.load();
        String secret = dotenv.get("JWT_SECRET");

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT
                .create()
                .withIssuer("linkbox")
                .withExpiresAt(generateTokenExpirationTime())
                .withSubject(id.toString())
                .sign(algorithm);
    }

    public Instant generateTokenExpirationTime() {
        return Instant.now().plus(Duration.ofHours(2));
    }

}
