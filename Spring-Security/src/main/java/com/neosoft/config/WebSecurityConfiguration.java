package com.neosoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/api/**").authenticated()
			.and()
			.oauth2Login(oauth2login -> 
					oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
			.oauth2Client(Customizer.withDefaults());
		
//		http.authorizeHttpRequests()
//		.antMatchers("/")
//		.permitAll()
//		.antMatchers("/home")
//		.hasAuthority("USER")
//		.antMatchers("/admin")
//		.hasAuthority("ADMIN")
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
	}
}
