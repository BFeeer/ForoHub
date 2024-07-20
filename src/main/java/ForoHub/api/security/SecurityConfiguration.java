package ForoHub.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean // configuración de la cadena de filtros de seguridad
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // protección contra cross-site request forgery (desactivado)
                .authorizeHttpRequests(auth ->{ // configuración de acceso a urls
                    auth.requestMatchers("/autenticacion").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/usuarios").permitAll();
                    auth.requestMatchers("/swagger-ui.html","/v3/api-docs/**","/swagger-ui/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session ->  // configurar el comportamiento de la sesión
                        // política de creación de sesión
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(Customizer.withDefaults()) // establecer autenticación básica
                .build(); // construir el objeto
    }

    @Bean // definición de un encriptador de contraseñas
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean // definición de un administrador de autenticación
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
