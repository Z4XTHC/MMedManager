// package com.mangosoft.MMedManager.config;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
// public class WebSecurityConfig {

// @Autowired
// DataSource dataSource;

// @Autowired
// private BCryptPasswordEncoder passwordEncoder;

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .csrf(csrf -> csrf
// .ignoringRequestMatchers("/api/pago/**") // UTILIZA
// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //
// COOKIES
// )
// .authorizeHttpRequests((requests) -> requests
// .requestMatchers("/", "/home/**", "/css/**", "/js/**", "/img/**",
// "/layout/**",
// "/orden/consultar/**",
// "/api/pago/**")
// .permitAll()
// .requestMatchers("/usuario/**").hasAuthority("ADMIN")
// .requestMatchers("/orden/**").hasAnyAuthority("VENDEDOR", "ADMIN")
// .anyRequest().authenticated())
// .formLogin((form) -> form
// .loginPage("/login")
// .usernameParameter("usuario")
// .passwordParameter("clave")
// .permitAll()
// .successHandler((request, response, authentication) -> {
// response.sendRedirect("/home?sesionIniciada=INICIADO");
// }))
// .logout((logout) -> logout.permitAll()
// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
// .logoutSuccessUrl("/home?sesionFinalizada=FINALIZADO")
// .permitAll());

// return http.build();
// }

// @Bean
// public AuthenticationManager customAuthenticationManager(HttpSecurity http)
// throws Exception {
// return http.getSharedObject(AuthenticationManagerBuilder.class).build();
// }

// @Autowired
// public void configurerGlobal(AuthenticationManagerBuilder build) throws
// Exception {
// build.jdbcAuthentication().dataSource(dataSource)
// .passwordEncoder(passwordEncoder)
// .usersByUsernameQuery("SELECT usuario, clave, activo from usuarios where
// usuario = ?")
// .authoritiesByUsernameQuery(
// "SELECT u.usuario, r.nombre from roles r inner join usuarios u on u.id_rol =
// r.id where u.usuario = ?");
// }
// }