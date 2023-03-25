package org.creativehummers.backend.users;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.EmailValidator;
import lombok.Setter;

public class UserValidator  {

    UserService service;

    @Setter
    private boolean enablePasswordValidation;

    public UserValidator(UserService service ) {
        this.service = service;
    }

    public ValidationResult passwordValidator(String pass1, String pass2) {
        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Password should be at least 8 characters long");
        }
        if (!enablePasswordValidation) {
            // user hasn't visited the field yet, so don't validate just yet, but next time.
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }
        return ValidationResult.error("Passwords do not match");
    }
    public ValidationResult validateUserid(String handle, ValueContext ctx) {
        String errorMsg = service.validateUserid(handle);
        if (errorMsg == null) {
            return ValidationResult.ok();
        }
        return ValidationResult.error(errorMsg);
    }

    public class VisibilityEmailValidator extends EmailValidator {
        public VisibilityEmailValidator(String errorMessage) {
            super(errorMessage);
        }
        @Override
        public ValidationResult apply(String value, ValueContext context) {
            //TODO: fill in validations..
            return ValidationResult.ok();
        }
    }

}
