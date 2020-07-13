package com.smartosc.training.validations.anotation;

import com.smartosc.training.validations.LengthOfNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 13/07/2020 - 11:20 AM
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthOfNameValidator.class)
public @interface LengthOfNameConstraint {
    String message() default "message.invalid.name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
