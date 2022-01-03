package fa.training.impsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSessionEvent;

public class SessionTimeoutHandler extends HttpSessionEventPublisher {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessionRegistry.removeSessionInformation(event.getSession().getId());
    }


}
