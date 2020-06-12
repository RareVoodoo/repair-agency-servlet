package ua.testing.repairagency.service.login;

import ua.testing.repairagency.model.User;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface AuthorityAuthentication {
    String authenticate(HttpServletRequest request, User user);
}
