package com.smartosc.training.validations;

import com.smartosc.training.validations.anotation.TitleConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 08/07/2020 - 10:34 AM
 * @created_by Namtt
 * @since 08/07/2020
 */
public class TitleValidate implements ConstraintValidator<TitleConstraint, String> {
  public void initialize(TitleConstraint constraint) {}

  public boolean isValid(String obj, ConstraintValidatorContext context) {
    return obj != null && obj.length() > 8;
  }
}
