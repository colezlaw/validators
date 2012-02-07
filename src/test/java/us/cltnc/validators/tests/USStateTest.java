package us.cltnc.validators.tests;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import us.cltnc.validators.tests.beans.Address;

public class USStateTest {
  private Validator validator;
  
  @Before
  public void setUp() {
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    validator = vf.getValidator();
  }
  
  @Test
  public void testValid() {
    Address ad = new Address();
    ad.setState1("NC");
    ad.setState2("DC");
    ad.setState3("AE");
    ad.setState4("North Carolina");
    Set<ConstraintViolation<Address>> violations = validator
        .validate(ad);
    assertEquals(0, violations.size());
  }
  
  @Test
  public void testInvalidShort() {
    Address ad = new Address();
    ad.setState1("NZ");
    ad.setState2("DC");
    ad.setState3("AE");
    ad.setState4("North Carolina");
    Set<ConstraintViolation<Address>> violations = validator
        .validate(ad);
    assertEquals(1, violations.size());    
  }
}
