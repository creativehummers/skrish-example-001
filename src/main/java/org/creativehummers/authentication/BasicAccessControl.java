package org.creativehummers.authentication;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.java.Log;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a user if the password is the same string, and
 * considers the user "admin" as the only administrator.
 */
@Log
public class BasicAccessControl implements AccessControl {

    @Override
    public boolean signIn(String username, String password) {
        String[] params = new String[] { username};
        String[][] resultArray = new String[2][1];
        resultArray[0][0] ="pwd#1234";
        resultArray[1][0] = "admin";

        log.severe("Logon Credentials : "+ username + " : " + password);
        String pwd = null , role = null;
        try {
            pwd = resultArray[0][0];
            role = resultArray[1][0];
            log.info("Signin info: "+ pwd);
        } catch( Exception e){
            log.severe(" Error executing or unmarshalling result: " + e.getMessage() );
        }

        if (username == null || username.isEmpty()) {
            return false;
        }

        if ( pwd == null || !pwd.equals(password)) {
            return false;
        }

        CurrentUser.set(username);
        CurrentUser.setRole(role);
        log.info("Role for user: " + CurrentUser.get() + " : " + role);
        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getUserRole().equals("ADMIN") || getUserRole().equals("SUPERADMIN");

        }
        if ("back_office".equals(role)) {
            // Only the "patient's" user is in the "patient" role
            return getUserRole().equals("FRONTOFFICE");
        }

        if ("patient".equals(role)) {
            // Only the "patient's" user is in the "patient" role
            return getUserRole().equals("PATIENT");
        }

        // All users are in all non-admin-patient roles
        return true;
    }

    private String getUserRole() {
        return CurrentUser.getRole();
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

    @Override
    public void signOut() {
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().navigate("");
    }
}
