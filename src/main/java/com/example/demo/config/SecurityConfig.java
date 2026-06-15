package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключаем CSRF для H2 Console (учебный проект)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                // Разрешаем фреймы для H2 Console
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                // Настройка прав доступа
                .authorizeHttpRequests(auth -> auth
                        // Статические ресурсы (CSS) - доступны всем
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // H2 Console - доступна всем (для учебы)
                        .requestMatchers("/h2-console/**").permitAll()
                        // Публичные GET-страницы
                        .requestMatchers(HttpMethod.GET,
                                "/", "/about", "/contacts",
                                "/tickets/new", "/login",
                                "/tickets/*/success"
                        ).permitAll()
                        // Публичный POST (создание заявки)
                        .requestMatchers(HttpMethod.POST, "/tickets").permitAll()
                        // Админ-зона доступна только ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Все остальные запросы требуют аутентификации
                        .anyRequest().authenticated()
                )
                // Настройка формы логина
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/admin/tickets", true)
                        .permitAll()
                )
                // Настройка выхода из системы
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Администратор
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("USER", "ADMIN")
                .build();

        // Обычный пользователь
        UserDetails user = User.withUsername("user")
                .password("{noop}user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
