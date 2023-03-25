package org.creativehummers.authentication;

import java.io.Serializable;

/**
 * Simple interface for authentication and authorization checks.
 */
public interface AccessControl extends Serializable {

    String ADMIN_ROLE_NAME = "admin";
    String ADMIN_USERNAME = "administrator";

    String BACKOFFICE_ROLE_NAME = "back_office";
    String BACKOFFICE_USERNAME = "Pooja";

    String PATIENT_ROLE_NAME = "patient";
    boolean signIn(String username, String password);

    boolean isUserSignedIn();

    boolean isUserInRole(String role);

    String getPrincipalName();

    void signOut();
}
