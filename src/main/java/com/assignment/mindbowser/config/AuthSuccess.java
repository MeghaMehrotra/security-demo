package com.assignment.mindbowser.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * This is our custom authentication success handler
 * 
 * @author Megha
 *
 */
@Component
public class AuthSuccess extends SimpleUrlAuthenticationSuccessHandler {
	@Value("${server.url}")
	private String serverUrl;

	/**
	 * RedirectStrategy is use to redirect page
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private static Logger logger = LoggerFactory.getLogger(AuthSuccess.class);

	/**
	 * This callback method is called when authentication is success
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
		
		

		// redirectStrategy.sendRedirect(request, response,
		// determineTargetUrl(authentication));

	}

	/**
	 * This mehtod determine target url for user based on there role
	 * 
	 * @param authentication
	 * @return
	 */
	protected String determineTargetUrl(Authentication authentication) {

		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		return "/";
	}

	public RedirectStrategy getRedirectStrategy() {

		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {

		this.redirectStrategy = redirectStrategy;
	}
}