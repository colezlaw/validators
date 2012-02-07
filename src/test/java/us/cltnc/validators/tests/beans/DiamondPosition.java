package us.cltnc.validators.tests.beans;

import javax.validation.constraints.NotNull;

import us.cltnc.validators.constraints.OneOf;

/**
 * Admittedly this is quite contrived as you'd reasonably
 * just use an enum for this.
 * 
 * @author guillermo
 *
 */
public class DiamondPosition {
  @NotNull
  @OneOf(value={"P","C","1B","2B","3B","SS","LF","CF","RF"}, caseSensitive=false)
  private String shortName;
  
  @NotNull
  @OneOf(value={
      "Pitcher","Catcher","First Base","Second Base","Third Base","Shortstop","Left Field","Center Field","Right Field"},
      caseSensitive=false)
  private String longName;
  
  public DiamondPosition() {}
  
  public DiamondPosition(String shortName) {
    this.shortName = shortName;
  }
  
  public DiamondPosition(String shortName, String longName) {
    this.shortName = shortName;
    this.longName = longName;
  }
  
  public void setShortName(String shortName) { this.shortName = shortName; }
  public String getShortName() { return shortName; }
  
  public void setLongName(String longName) { this.longName = longName; }
  public String getLongName() { return longName; }
}
