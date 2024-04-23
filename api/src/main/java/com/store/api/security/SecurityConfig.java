package com.store.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.store.api.controller.LoginController;

import java.util.List;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private LoginController loginController;

    @Autowired
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // your existing configuration code here
    }


    @Bean
    @Autowired
    SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter)
            throws Exception {
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/inicio", "/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/usuarios", "/clientes").permitAll()
            .requestMatchers(HttpMethod.GET, "/clientes").hasAnyRole("MODERADOR", "ADMIN")
            .requestMatchers(HttpMethod.GET, "/clientes/cantidad", "/clientes/compras").hasRole("MODERADOR")
            .requestMatchers(HttpMethod.GET, "/productos").hasAnyRole("REGISTRADO", "MODERADOR", "ADMIN")
            .anyRequest().hasRole("ADMIN"));


            http.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);
            http.cors(cors -> corsConfigurationSource());
            http.csrf(csrf -> csrf
                    .csrfTokenRequestHandler(requestHandler)
                    .ignoringRequestMatchers(
                        new AntPathRequestMatcher("/inicio"),
                        new AntPathRequestMatcher("/login"),
                        new AntPathRequestMatcher("/usuarios", HttpMethod.POST.toString()),
                        new AntPathRequestMatcher("/clientes", HttpMethod.POST.toString())
                    )
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                    .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
            return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}