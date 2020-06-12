package ua.testing.repairagency.filter;

import ua.testing.repairagency.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EncodingRequestFilter implements Filter {
    private String encoding;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        resp.setContentType(Constants.CONTENT_TYPE_DEFAULT);
        req.setCharacterEncoding(Constants.CHAR_ENCODING_DEFAULT);
        resp.setCharacterEncoding(Constants.CHAR_ENCODING_DEFAULT);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
