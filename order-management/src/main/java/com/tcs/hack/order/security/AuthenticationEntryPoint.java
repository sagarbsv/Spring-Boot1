
package com.tcs.hack.order.security;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)

			throws IOException, ServletException {

		response.addHeader("LoginUser", "Basic " + getRealmName());

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		PrintWriter writer = response.getWriter();

		writer.println("HTTP Status 401 - " + authEx.getMessage());

	}

	@Override

	public void afterPropertiesSet() throws Exception {

		setRealmName("com.hackerrank.sample");

		super.afterPropertiesSet();

	}

}
