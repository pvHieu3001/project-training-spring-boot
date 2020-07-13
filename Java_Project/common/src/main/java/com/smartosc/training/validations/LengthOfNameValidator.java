package com.smartosc.training.validations;

import com.smartosc.training.validations.anotation.LengthOfNameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 13/07/2020 - 11:19 AM
 */
public class LengthOfNameValidator implements ConstraintValidator<LengthOfNameConstraint, String> {

    private Pattern pattern;
    private Matcher matcher;

    private static final String NAME_CONTEXT = "^[aA-zZ]\\w{5,75}$";

    @Override
    public void initialize(LengthOfNameConstraint constraintAnnotation) {}

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        pattern = Pattern.compile(NAME_CONTEXT);
        matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
