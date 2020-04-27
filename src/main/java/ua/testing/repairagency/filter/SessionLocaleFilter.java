package ua.testing.repairagency.filter;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;


public class SessionLocaleFilter implements Filter {
    Logger logger = Logger.getLogger(String.valueOf(SessionLocaleFilter.class));

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;


        if (httpRequest.getParameter("lang") != null) {
            if (httpRequest.getParameter("lang").equals("en")) {
                logger.info("Current locale is en");
                httpRequest.setAttribute("language", "en");
            } else if (httpRequest.getParameter("lang").equals("ua")) {
                logger.info("Current locale is ua");
                httpRequest.setAttribute("language", "ua");
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
