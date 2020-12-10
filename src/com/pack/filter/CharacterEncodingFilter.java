package com.pack.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	private String encode;
	private boolean forceEncoding;

	public void init(FilterConfig config) throws ServletException {

		String force = config.getInitParameter("forceEncoding");
		if (force != null && !force.isEmpty()) {
			this.forceEncoding = Boolean.parseBoolean(force);
		}

		this.encode = config.getInitParameter("encoding");
		if (this.encode == null) {
			this.encode = "UTF-8";
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (this.encode != null
				&& (this.forceEncoding || request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(this.encode);
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}