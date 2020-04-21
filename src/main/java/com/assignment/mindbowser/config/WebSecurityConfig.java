package com.assignment.mindbowser.config;

import java.util.concurrent.Executor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthFailure authFailure;

	@Autowired
	AuthSuccess authSuccess;

	@Autowired
	RestAuthenticationEntryPoint myEntryPoint;

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login/**", "/signup/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and().formLogin()
				.usernameParameter("username").passwordParameter("password").successHandler(authSuccess)
				.failureHandler(authFailure).authenticationDetailsSource(authenticationDetailsSource()).and().logout()
				.and().exceptionHandling().authenticationEntryPoint(myEntryPoint).and().csrf().disable();
	}

	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource() {

		return new AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>() {

			@Override
			public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
				if (null != request.getParameter("username")) {
					return new WebAuthenticationDetails(request);
				} else {
					return null;
				}
			}
		};
	}

	/**
	 * This callback method is used to check Authenticity of user
	 * 
	 * @param auth
	 * @throws Exception
	 */

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ram").password("ram123").roles("ADMIN");

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
	
	

	@Bean
	public ExUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {

		ExUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new ExUsernamePasswordAuthenticationFilter();
		customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return customUsernamePasswordAuthenticationFilter;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/**");
			}
		};
	}

	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	};

}
