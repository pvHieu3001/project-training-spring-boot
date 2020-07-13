package com.smartosc.training.validations.anotation;

import com.smartosc.training.validations.EmailValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 13/07/2020 - 9:54 AM
 * @created_by Huupd
 */
@Documented
@Constraint(validatedBy = EmailValidate.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailContraint {
    String message() default "message.wrongValidateEmail";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
