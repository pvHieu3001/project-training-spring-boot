package com.smartosc.training.validations.anotation;

import com.smartosc.training.validations.EmailValidate;
import com.smartosc.training.validations.UserNameValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 13/07/2020 - 10:13 AM
 * @created_by Huupd
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidate.class)
public @interface UserNameConstraint {
    String message() default "message.wrongValidateUserName";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
