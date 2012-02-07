package us.cltnc.validators.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import us.cltnc.validators.constraints.impl.USStateValidator;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy=USStateValidator.class)
public @interface USState {
  public enum MatchType {
    SHORT,
    LONG,
    ANY
  }
  
  public enum MatchDomain {
    US,
    US_DC,
    US_DC_TERRITORIES
  }
  
  MatchType type() default MatchType.SHORT;
  
  MatchDomain domain() default MatchDomain.US; 
  
  String message() default "{us.cltnc.validators.constraints.USState.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
  
  boolean caseSensitive() default true;

  @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
  @Retention(RUNTIME)
  @Documented
  @interface List {
      USState[] value();
  }  
}
