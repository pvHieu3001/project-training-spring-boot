package com.smartosc.training.validations.anotation;

import com.smartosc.training.validations.PasswordValidate;
import com.smartosc.training.validations.TitleValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 10/07/2020 - 2:20 PM
 * @created_by Huupd
 */
@Documented
@Constraint(validatedBy = PasswordValidate.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordContraint {
    String message() default "message.wrongValidatePassword";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
