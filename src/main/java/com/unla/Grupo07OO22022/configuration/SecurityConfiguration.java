package com.unla.Grupo07OO22022.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unla.Grupo07OO22022.services.implementation.CustomUserDetailsService;
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
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
	        .antMatchers("/user/new", "/user/update/**", "/user/delete/**", "/user-role/new", 
	        		"/user-role/update/**", "/user-role/delete/**",
	        		"/matter/new", "/matter/update/**", "/matter/delete/**",
	        		"/order-note/new-final", "/order-note/new-course", "/order-note/delete/final/**", "/order-note/delete/course/**",
	        		"/order-note/update-final/**", "/order-note/update-course/**").hasAnyAuthority("ADMINISTRADOR")
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().permitAll()
	        .and()
	        .logout().permitAll();
    }
}
