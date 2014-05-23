package uts.wsd.controller;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String requestString = httpRequest.getMethod() + httpRequest.getServletPath();

        if (!authorised(httpRequest)) {
            // Send the following unauthorised requests to Login.
            switch (requestString) {
                case "GET/account"     :
                case "GET/newarticle"  :
                case "POST/newarticle" :
                    httpResponse.sendRedirect("login");
                    return;
            }
        } else {
            // Send the following authorised requests to Account.
            switch (requestString) {
                case "GET/login"     :
                case "POST/login"    :
                case "GET/register"  :
                case "POST/register" :
                    httpResponse.sendRedirect("account");
                    return;
            }
        }

        // Propagate to the RequestMediator.
        chain.doFilter(request, response);        
    }

    public void destroy() {
    }

    private boolean authorised(HttpServletRequest httpRequest) {
        return (httpRequest.getSession().getAttribute("user") != null);
    }

}