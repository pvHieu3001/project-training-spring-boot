package com.smartosc.training.validations;

import com.smartosc.training.validations.anotation.PasswordContraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 10/07/2020 - 2:19 PM
 * @created_by Huupd
 */
public class PasswordValidate implements ConstraintValidator<PasswordContraint, String> {
    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    public PasswordValidate() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public void initialize(PasswordContraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validatePassword(value);
    }

    private boolean validatePassword(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
