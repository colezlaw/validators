package us.cltnc.validators.constraints.impl;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import us.cltnc.validators.constraints.OneOf;

public class OneOfValidator implements ConstraintValidator<OneOf, String> {
  private String[] value;
  
  public void initialize(OneOf constraintAnnotation) {
    this.value = constraintAnnotation.value();
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    return Arrays.asList(this.value).contains(value);
  }
}
