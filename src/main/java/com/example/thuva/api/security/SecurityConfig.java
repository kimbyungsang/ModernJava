package com.example.thuva.api.security;

import com.example.thuva.api.entity.RoleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.example.thuva.api.security.Contants.*;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


public class SecurityConfig {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserDetailsService userService;
    private final PasswordEncoder bCryptPasswordEncoder;

    private final ObjectMapper mapper;

    @Value("${app.security.jwt.keystore-location}")
    private String keyStorePath;

    @Value("${app.security.jwt.keystore-password}")
    private String keyStorePassword;

    @Value("${app.security.jwt.key-alias}")
    private String keyAlias;

    @Value("${app.security.jwt.private-key-passphrase}")
    private String privateKeyPassphrase;

    public SecurityConfig(
            UserDetailsService userService,
            @Lazy PasswordEncoder bCryptPasswordEncoder,
            @Lazy ObjectMapper mapper) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapper = mapper;
    }

    // key store bean

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().ignoringRequestMatchers(API_URL_PREFIX).ignoringRequestMatchers(toH2Console())
                .and()
                .headers().frameOptions().sameOrigin() // for H2 console
                .and()
                .cors()
                .and()
                .authorizeHttpRequests(req -> req.requestMatchers(toH2Console()).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(TOKEN_URL, HttpMethod.POST.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(TOKEN_URL, HttpMethod.DELETE.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(SIGNUP_URL, HttpMethod.POST.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(REFRESH_URL, HttpMethod.POST.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(PRODUCTS_URL, HttpMethod.GET.name())).permitAll()
                        .requestMatchers("/api/v1/addresses/**").hasAuthority(RoleEnum.ADMIN.getAuthority())
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter())))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authorityConverter = new JwtGrantedAuthoritiesConverter();
        authorityConverter.setAuthorityPrefix(AUTHORITY_PREFIX);
        authorityConverter.setAuthoritiesClaimName(ROLE_CLAIM);
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authorityConverter);
        return converter;
    }


}
