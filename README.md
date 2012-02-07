## US.CLTC Validators

This is the first push of some simple validators to
test out JSR-303 as well as add some useful validators.

## Use It

    <dependency>
      <groupId>us.cltnc.validators</groupId>
      <artifactId>validators</artifactId>
      <version>1.0-SNAPSHOT</version>
      <scope>compile</scope>
    </dependency>
    <!-- It's up to you to pick a JSR-303
         implementation. This does not specifically
         enforce one -->
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-validator-annotation-processor</artifactId>
      <version>4.2.0.Final</version>
      <scope>compile</scope>
    </dependency>
    <!-- or you could pick this one
    <dependency>
      <groupId>org.apache.geronimo.specs</groupId>
      <artifactId>geronimo-validation_1.0_spec</artifactId>
      <version>1.1</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.bval</groupId>
      <artifactId>org.apache.bval.bundle</artifactId>
      <version>0.3-incubating</version>
      <scope>test</scope>
    </dependency>
    -->

## Validators

There are only two included validators right now, `OneOf`
and `USState`.

### `OneOf`

    @OneOf({"FOO","BAR","BAZ"})
    private String coderBingo;

### `USState`

`USState` checks the value to see if it's one of the USPS allowed
states. The default match `type` is `SHORT` (i.e., `AZ`, `NC`, etc.)
and the default `domain` is `US` (only the 50 US States).

If you use `LONG` for the `type`, you can match full state names,
such as `North Carolina`, `Mississippi`, or `Louisiana`.

If you use `US_DC` for the `domain`, you can match the 50 states
plus DC. `US_DC_TERRIROTIES` also gives you the US Territories, such
as PR (Puerto Rico) or GU (Guam).

    @USState(type=MatchType.SHORT,domain=US)
    private String state;

