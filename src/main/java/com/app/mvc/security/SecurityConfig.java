package com.app.mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig /*extends WebMvcConfigurationSupport */{

/*    private static final String RESOURCES_HANDLER = "/resources/";
    private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
    }*/
    @Bean
    public SecurityFilterChain customSecurity(HttpSecurity http) throws Exception {
//     /employees/showFormForUpdate
        http.authorizeHttpRequests((req) ->
            req
//                .requestMatchers("/**").permitAll()
                .requestMatchers("/employees/list**").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/employees/showFormForAdd").permitAll()
                .requestMatchers("/employees/save").permitAll()
                .requestMatchers("/employees/delete").permitAll()
                .requestMatchers("/employees/showFormForUpdate**").hasRole("ADMIN")
                .requestMatchers("/h2").authenticated()
                .requestMatchers("/h2/**").authenticated()
//                    .requestMatchers("/static/favicon.ico").permitAll()
        );
//                        .anyRequest("/employees/employees/showFormForUpdate**").authenticated());
//                    .requestMatchers(HttpMethod.POST,"/signup").hasRole("USER")
        http.csrf(csrf -> csrf.disable());
//        http.formLogin(Customizer.withDefaults());
//        http.formLogin(loginConfigurer -> loginConfigurer.defaultSuccessUrl("/employees/employees/list").permitAll());
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());

//        http.headers().frameOptions().disable();
        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions().disable());
        return http.build();
    }

//    @Bean
//    public JdbcUserDetailsManager securityManager(DataSource source){
//        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(source);
//        jdbc.setUsersByUsernameQuery("select member_name,pw,active from members where member_name=?");
//        jdbc.setAuthoritiesByUsernameQuery("SELECT member_name,roles FROM permissions where member_name=?");
//        return jdbc;
//    }

    @Bean
    public InMemoryUserDetailsManager users() {
        UserDetails pranav = User.builder().username("pranav").password("{noop}tiger").roles("USER").build();
        UserDetails mayur = User.builder().username("admin").password("{noop}tiger").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(pranav, mayur);
    }
}

