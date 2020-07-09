package com.smartosc.training.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 08/07/2020 - 10:34 AM
 * @created_by Namtt
 * @since 08/07/2020
 */
@Documented
@Constraint(validatedBy = TitleValidate.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TitleConstraint {
  String message() default "message.wrongValidateTitle";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
