package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtil {

    public static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * Visszaadja az aktuális bejelentkezett felhasználó felhasználónevét
     *
     * @return bejelentkezett felhasználó felhasználónevét
     */
    public static String getCurrentUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return ANONYMOUS_USER.equals(username) ? null : username;
    }
}
