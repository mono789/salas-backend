package co.edu.udea.SalasInfo.Security;

import co.edu.udea.SalasInfo.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration{
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

     String[] PUBLIC_ENDPOINTS = {
             "/application/rooms",
             "/auth/register",
             "/auth/login",
            "/implement/rooms",
            "/reservation/find-all",
            "/reservation/free-all",
            "/reservation/save",
            "/reservation/find-by-room/**",
            "/room/find-all",
            "/room/find-by-id/**",
            "/room/find-by-application/**",
            "/room/find-by-implement/**"
    };
    String[] ADMIN_ENDPOINTS = {
            "/auth/**",
            "/reservation/**",
            "/application/**",
            "/implement/**",
            "/**"

    };
    String[] TEACHER_ENDPOINTS = {
            "/salasinfo/"
    };
    String[] MONITOR_ENDPOINTS = {
            "/salasinfo/"
    };

    //separo endpoints publicos y protegidos
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf-> csrf.disable())                 //desabilito el token
                .authorizeHttpRequests(authRequest ->   //gestionar rutas a la configuracion
                        authRequest
                                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()//Todos los que hagan mach con public deben ser publicos

                                .requestMatchers(TEACHER_ENDPOINTS).hasRole("PROFESSOR")
                                .requestMatchers(MONITOR_ENDPOINTS).hasRole("MONITOR")
                                .requestMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN")
                                .anyRequest().authenticated()//los demas endpoints requieren autenticacion
                )
                //.formLogin(withDefaults())//autenticacion de spring
                .sessionManagement(sessionManager->sessionManager        //inabilito sesiones
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))              //politica de creacion de sesiones--> no las utilice
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)//el filtro de jwt que configuramos previamente
                .build();
    }

}
