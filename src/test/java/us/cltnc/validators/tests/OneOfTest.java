package us.cltnc.validators.tests;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import us.cltnc.validators.tests.beans.DiamondPosition;

public class OneOfTest {
  private Validator validator;
  
  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }
  
  @Test
  public void testInvalidShortName() {
    DiamondPosition dp = new DiamondPosition("BB", "First Base");
    Set<ConstraintViolation<DiamondPosition>> violations =
        validator.validate(dp);
    assertEquals(1, violations.size());
    assertEquals("The supplied value is not one of the allowed list.",
        violations.iterator().next().getMessage());
  }
  
  @Test
  public void testInvalidLongName() {
    DiamondPosition dp = new DiamondPosition("P", "Babe Ruth");
    Set<ConstraintViolation<DiamondPosition>> violations =
        validator.validate(dp);
    assertEquals(1, violations.size());
    assertEquals("The supplied value is not one of the allowed list.",
        violations.iterator().next().getMessage());
  }
  
  @Test
  public void testNullShortName() {
    DiamondPosition dp = new DiamondPosition();
    dp.setLongName("Pitcher");
    Set<ConstraintViolation<DiamondPosition>> violations =
        validator.validate(dp);
    assertEquals(1, violations.size());
  }
  
  @Test
  public void testValid() {
    DiamondPosition dp = new DiamondPosition("1B", "First Base");
    Set<ConstraintViolation<DiamondPosition>> violations =
        validator.validate(dp);
    assertEquals(0, violations.size());
  }
  
  @Test
  public void testCaseInsensitive() {
    DiamondPosition dp = new DiamondPosition("1b", "fIrSt BaSe");
    Set<ConstraintViolation<DiamondPosition>> violations =
        validator.validate(dp);
    assertEquals(0, violations.size());
  }
}
