package ua.testing.repairagency.filter;

import ua.testing.repairagency.util.Constants;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, false);
        HttpSession session = req.getSession();

        Optional<String> userRole = Optional.ofNullable((String) session.getAttribute(Constants.ROLE_ATTRIBUTE));
        if (!userRole.isPresent()) {
            session.setAttribute(Constants.ROLE_ATTRIBUTE, Constants.UNKNOWN_ROLE);
        }




        chain.doFilter(request, response);
    }


}