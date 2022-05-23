package com.unla.Grupo07OO22022.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unla.Grupo07OO22022.services.implementation.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
	        .antMatchers("/user/new", "/user/update/**", "/user/delete/**", "/user-role/new", "/user-role/update/**", "/user-role/delete/**").hasAnyAuthority("ADMINISTRADOR")
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().permitAll()
	        .and()
	        .logout().permitAll();
    }
    
}
