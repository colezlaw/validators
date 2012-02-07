package us.cltnc.validators.tests.beans;

import javax.validation.constraints.NotNull;

import us.cltnc.validators.constraints.USState;
import us.cltnc.validators.constraints.USState.MatchDomain;
import us.cltnc.validators.constraints.USState.MatchType;

public class Address {
  @NotNull
  @USState(domain=MatchDomain.US)
  private String state1;
  
  @NotNull
  @USState(domain=MatchDomain.US_DC)
  private String state2;
  
  @NotNull
  @USState(domain=MatchDomain.US_DC_TERRITORIES)
  private String state3;
  
  @NotNull
  @USState(type=MatchType.LONG)
  private String state4;
  
  public Address() {}
  
  public void setState1(String state1) { this.state1 = state1; }
  public String getState1() { return state1; }

  public void setState2(String state2) { this.state2 = state2; }
  public String getState2() { return state2; }

  public void setState3(String state3) { this.state3 = state3; }
  public String getState3() { return state3; }

  public void setState4(String state4) { this.state4 = state4; }
  public String getState4() { return state4; }
}
