package us.cltnc.validators.constraints.impl;

import java.text.Collator;
import java.util.Arrays;
import java.util.TreeSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import us.cltnc.validators.constraints.USState;
import us.cltnc.validators.constraints.USState.MatchDomain;
import us.cltnc.validators.constraints.USState.MatchType;

public class USStateValidator implements ConstraintValidator<USState, String> {
  private TreeSet<String> states;
  
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
    
  public void initialize(USState annotation) {
    if (!annotation.caseSensitive()) {
      System.out.println("Doing a case-insensitve match");
      Collator c = Collator.getInstance();
      c.setStrength(Collator.PRIMARY);
      states = new TreeSet<String>(c);
    } else {
      states = new TreeSet<String>();
    }
    
    if ((MatchType.ANY == annotation.type()) || MatchType.SHORT == annotation.type()) {
      states.addAll(Arrays.asList(US_SHORT));
      if ((MatchDomain.US_DC == annotation.domain()) || (MatchDomain.US_DC_TERRITORIES == annotation.domain()))
        states.addAll(Arrays.asList(DC_SHORT));
      if (MatchDomain.US_DC_TERRITORIES == annotation.domain())
        states.addAll(Arrays.asList(INTL_SHORT));
    }
    if ((MatchType.ANY == annotation.type()) || MatchType.LONG == annotation.type()) {
      states.addAll(Arrays.asList(US_LONG));
      if ((MatchDomain.US_DC == annotation.domain()) || (MatchDomain.US_DC_TERRITORIES == annotation.domain()))
        states.addAll(Arrays.asList(DC_LONG));
      if (MatchDomain.US_DC_TERRITORIES == annotation.domain())
        states.addAll(Arrays.asList(INTL_LONG));
    }
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    // I WANTED to do this as an extension of the OneOf validator, but that
    // wouldn't allow me to do a composition with a list that we can't know
    // beforehand. I otherwise would have had to make separate validators
    // for each
    return this.states.contains(value);
  }
}
