package edu.uy.um.wtf.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;


@Service
public class SessionService {

    private final HttpSession httpSession;

    public SessionService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void addAttribute(String key, Object value) {
        httpSession.setAttribute(key, value);
    }

    public Object getAttribute(String key) {
        return httpSession.getAttribute(key);
    }

    public void removeAttribute(String key) {
        httpSession.removeAttribute(key);
    }

    public void updateAttribute(String key, Object value) {
        httpSession.setAttribute(key, value);
    }
    public void invalidate() {
        httpSession.invalidate();
    }
}
