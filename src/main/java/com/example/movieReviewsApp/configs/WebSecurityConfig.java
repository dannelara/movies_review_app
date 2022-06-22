package com.example.movieReviewsApp.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


/**
 * Websecurity class that checks implements springs own security mechanism when requests are made to the api.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired RequestFilter requestFilterer;
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {

        // Make CSRF disabled and cors enabled.
        http = http.cors().and().csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http
			.authorizeRequests()
				.antMatchers("/api/v1/movies/**").permitAll();
	}

}
