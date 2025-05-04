package api.sistemaVotaciones.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtService jwtService;

    public SecurityConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/voters").hasAnyRole("ADMIN", "VOTER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/voters/{id}").hasAnyRole("ADMIN", "VOTER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/voters/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/voters").hasAnyRole("ADMIN", "VOTER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/voters/{id}").hasAnyRole("ADMIN", "VOTER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/candidates").hasAnyRole("ADMIN", "CANDIDATE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/candidates/{id}").hasAnyRole("ADMIN", "CANDIDATE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/candidates/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/candidates").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/candidates/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/votes").hasRole("VOTER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/votes").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/votes/statistics").authenticated()
                );
        return http.build();
        //TODO: el login y el register funcionan bien, falta probar la autorizacion por roles
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
