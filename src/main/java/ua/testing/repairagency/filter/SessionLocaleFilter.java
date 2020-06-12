package ua.testing.repairagency.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;


public class SessionLocaleFilter implements Filter {
    private Logger logger = LogManager.getLogger();
    private HashMap<String, Runnable> locales  = new HashMap<>();
    private HttpServletRequest httpServletRequest;
    private HttpSession session;



    public void init(FilterConfig config) throws ServletException {
        locales.put("en", () -> switchLocaleToEn(httpServletRequest, session));
        locales.put("ua", () -> switchLocaleToUa(httpServletRequest, session));

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        if(session.isNew()){
            session.setAttribute(Constants.CURRENT_LOCALE_ATTRIBUTE, Constants.EN_LOCALE);
            this.httpServletRequest = httpRequest;
            this.session = session;
        }

            Optional<String> currentLocaleKey = Optional.ofNullable(httpRequest.getParameter(Constants.LANG_PARAM));
            currentLocaleKey.ifPresent(this::assignUserLocale);

        chain.doFilter(req, resp);
    }

    private void switchLocaleToEn(HttpServletRequest httpRequest, HttpSession session){
        logger.info("Current locale is en");
        session.setAttribute(Constants.CURRENT_LOCALE_ATTRIBUTE, Constants.EN_LOCALE);
        httpRequest.setAttribute(Constants.CURRENT_LOCALE_ATTRIBUTE, Constants.EN_LANGUAGE);
    }

    private void switchLocaleToUa(HttpServletRequest httpRequest, HttpSession session){
        logger.info("Current locale is ua");
        session.setAttribute(Constants.CURRENT_LOCALE_ATTRIBUTE, Constants.UA_LOCALE);
        httpRequest.setAttribute(Constants.CURRENT_LOCALE_ATTRIBUTE, Constants.UA_LANGUAGE);
    }

    private void assignUserLocale(String localeKey){
        locales.get(localeKey).run();
    }


}
