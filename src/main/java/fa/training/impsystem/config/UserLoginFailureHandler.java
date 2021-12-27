package fa.training.impsystem.config;

import fa.training.impsystem.entities.Users;
import fa.training.impsystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (exception.getClass().isAssignableFrom(SessionAuthenticationException.class)) {
            request.getRequestDispatcher("/login?message=max_session").forward(request, response);
            return;
        }

        Users user = UserServiceImpl.checkUser;

        if (user != null) {
            if (user.isEnable() && user.isAccountNonLocked()) {
                if (user.getFailedAttempt() < UserServiceImpl.MAX_FAILED_ATTEMPTS - 1) {
                    userService.increaseFailedAttempts(user);
                } else {
                    userService.lock(user);
                }
            }
        }
        request.getRequestDispatcher("/fail").forward(request, response);

    }
}
