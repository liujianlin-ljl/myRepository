package com.ljl.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * ×Ö·û±àÂë¹ýÂËÆ÷
 * @author ljl
 *
 */
public class CharacterEncodingFilter implements Filter {

	private String encoding=null;
	private FilterConfig fConfig=null;
	public void destroy() {
		encoding=null;
		fConfig=null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(encoding!=null) {
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset="+encoding);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
		encoding=this.fConfig.getInitParameter("encoding");
	}

}
