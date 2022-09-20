package com.example.wroom.configuration;

import com.example.wroom.services.implementations.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.example.wroom.utils.Constants.Security.*;

/**
 * Configuration for security
 *
 * @author Kristiina Lindre
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String role = "ROLE_";
        String admin = AUTHORITY_ADMIN.replace(role, "");
        String owner = AUTHORITY_EMPLOYEE_OWNER.replace(role, "");
        String manager = AUTHORITY_EMPLOYEE_MANAGER.replace(role, "");
        String employee = AUTHORITY_EMPLOYEE_SALES_PERSON.replace(role, "");
        String customer = AUTHORITY_CUSTOMER.replace(role, "");


        http.authorizeRequests()
                .antMatchers("/", "/signup")
                .permitAll()
                .antMatchers("/branch/**")
                .hasAnyRole(manager, employee, admin, owner, customer)
                .antMatchers("/car/**")
                .hasAnyRole(manager, employee, admin, owner, customer)
                .antMatchers("/booking/**")
                .hasAnyRole(manager, employee, admin, owner, customer)
                .antMatchers("/user/**")
                .hasAnyRole(manager, admin, owner)
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .logout().permitAll(false).logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }
}
