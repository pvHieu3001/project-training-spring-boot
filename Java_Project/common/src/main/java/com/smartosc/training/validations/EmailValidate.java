package com.smartosc.training.validations;

import com.smartosc.training.validations.anotation.EmailContraint;
import com.smartosc.training.validations.anotation.PasswordContraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 13/07/2020 - 9:54 AM
 * @created_by Huupd
 */

public class EmailValidate implements ConstraintValidator<EmailContraint, String> {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    public EmailValidate() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public void initialize(EmailContraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validatePassword(value);
    }

    private boolean validatePassword(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
