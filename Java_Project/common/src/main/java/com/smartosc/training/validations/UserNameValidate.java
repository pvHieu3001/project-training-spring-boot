package com.smartosc.training.validations;

import com.smartosc.training.validations.anotation.UserNameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 13/07/2020 - 10:13 AM
 * @created_by Huupd
 */
public class UserNameValidate implements ConstraintValidator<UserNameConstraint, String> {
    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[aA-zZ]\\w{5,29}$";

    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validateUserName(value);
    }

    private boolean validateUserName(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
