package us.cltnc.validators.constraints.impl;

import java.text.Collator;
import java.util.Arrays;
import java.util.TreeSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import us.cltnc.validators.constraints.OneOf;

public class OneOfValidator implements ConstraintValidator<OneOf, String> {
  TreeSet<String> value;
  
  public void initialize(OneOf annotation) {
    if (! annotation.caseSensitive()) {
      Collator c = Collator.getInstance();
      c.setStrength(Collator.PRIMARY);
      this.value = new TreeSet<String>(c);
    } else {
      this.value = new TreeSet<String>();
    }
    this.value.addAll(Arrays.asList(annotation.value()));
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || this.value.contains(value);
  }
}
