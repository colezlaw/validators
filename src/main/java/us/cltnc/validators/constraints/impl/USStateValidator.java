package us.cltnc.validators.constraints.impl;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import us.cltnc.validators.constraints.USState;
import us.cltnc.validators.constraints.USState.MatchDomain;
import us.cltnc.validators.constraints.USState.MatchType;

public class USStateValidator implements ConstraintValidator<USState, String> {
  private static final String[] US_SHORT = new String[] {
    "AL","AK","AZ","AR","CA",
    "CO","CT","DE","FL","GA",
    "HI","ID","IL","IN","IA",
    "KA","KY","LA","ME","MD",
    "MA","MI","MN","MS","MO",
    "MT","NE","NV","NH","NJ",
    "NM","NY","NC","ND","OH",
    "OK","OR","PA","RI","SC",
    "SD","TN","TX","UT","VT",
    "VA","WA","WV","WI","WY"
  };
  
  private static final String[] US_LONG = new String[] {
    "Alabama","Alaska","Arizona","Arkansas","California",
    "Colorado","Connecticut","Delaware","Florida","Georgia",
    "Hawaii","Idaho","Illinois","Indiana","Iowa",
    "Kansas","Kentucky","Louisiana","Maine","Maryland",
    "Massachusetts","Michigan","Minnesota","Mississippi","Missouri",
    "Montana","Nebraska","Nevada","New Hampshire","New Jersey",
    "New Mexico","New York","North Carolina","North Dakota","Ohio",
    "Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina",
    "South Dakota","Tennessee","Texas","Utah","Vermont",
    "Virginia","Washington","West Virginia","Wisconsin","Wyoming"
  };
  
  private static final String[] DC_SHORT = new String[] {
    "DC"
  };
  
  private static final String[] DC_LONG = new String[] {
    "District of Columbia"
  };
  
  private static final String[] INTL_SHORT = new String[] {
    "AS","FM","GU","MH",
    "MP","PW","PR","VI",
    "AA","AE","AE","AE",
    "AE","AP"
  };
  
  private static final String[] INTL_LONG = new String[] {
    "American Samoa","Federated States of Micronesia","Guam","Marshall Islands",
    "Northern Mariana Islands","Palau","Puerto Rico","Virgin Islands",
    "Armed Forces Americas","Armed Forces Africa","Armed Forces Canada","Armed Forces Europe",
    "Armed Forces Middle East","Armed Forces Pacific"
  };
  
  private MatchType type;
  private MatchDomain domain;
  
  public void initialize(USState annotation) {
    this.type = annotation.type();
    this.domain = annotation.domain();
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    // I WANTED to do this as an extension of the OneOf validator, but that
    // wouldn't allow me to do a composition with a list that we can't know
    // beforehand. I otherwise would have had to make separate validators
    // for each
    boolean retval = false;
    
    if (MatchType.SHORT == type) {
      if (Arrays.asList(US_SHORT).contains(value))
        retval = true;
      if ((MatchDomain.US_DC == domain) && (Arrays.asList(DC_SHORT).contains(value)))
        retval = true;
      else if ((MatchDomain.US_DC_TERRITORIES == domain) && (Arrays.asList(INTL_SHORT).contains(value)))
        retval = true;
    } else {
      if (Arrays.asList(US_LONG).contains(value))
        retval = true;
      if ((MatchDomain.US_DC == domain) && (Arrays.asList(DC_LONG).contains(value)))
        retval = true;
      else if ((MatchDomain.US_DC_TERRITORIES == domain) && (Arrays.asList(INTL_LONG).contains(value)))
        retval = true;      
    }
    
    return retval;
  }
}
