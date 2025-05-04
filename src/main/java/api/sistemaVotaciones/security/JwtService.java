package api.sistemaVotaciones.security;

import api.sistemaVotaciones.roles.model.Role;
import api.sistemaVotaciones.users.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.security.jwt.expiration-time}")
    private Long expirationTime;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var authorities = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    public Authentication validateToken(String token) throws AuthenticationException{
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);

        var username = extractUsername(decodedJWT);
        var roles = getSpecificClaim(decodedJWT, "roles").asString();
        var grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
        return new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }
}
