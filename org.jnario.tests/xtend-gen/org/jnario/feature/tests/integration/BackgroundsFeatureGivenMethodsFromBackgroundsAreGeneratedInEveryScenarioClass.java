package org.jnario.feature.tests.integration;

import org.jnario.jnario.test.util.FeatureExecutor;
import org.jnario.lib.Should;
import org.jnario.runner.FeatureRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

@RunWith(FeatureRunner.class)
@Named("Scenario: Given methods from backgrounds are generated in every scenario class")
@SuppressWarnings("all")
public class BackgroundsFeatureGivenMethodsFromBackgroundsAreGeneratedInEveryScenarioClass {
  @Test
  @Order(0)
  @Named("Given I have a feature with a background")
  public void givenIHaveAFeatureWithABackground() {
    this.jnarioFile = "\r\n\t\t\t\tpackage bootstrap\r\n\t\t\t\tFeature: Some feature\r\n\t\t\t\t\tBackground:\r\n\t\t\t\t\t\tGiven a user name\r\n\t\t\t\t\t\t\tthrow new RuntimeException()\r\n\t\t\t\t\tScenario: Scenario 1\r\n\t\t\t\t\tScenario: Scenario 2\r\n\t\t\t";
  }
  
  @Test
  @Order(1)
  @Named("Then every class should have a method that throws a RuntimeExeception")
  public void thenEveryClassShouldHaveAMethodThatThrowsARuntimeExeception() {
    Result _execute = FeatureExecutor.execute(this.jnarioFile);
    int _failureCount = _execute.getFailureCount();
    Should.operator_doubleArrow(Integer.valueOf(_failureCount), Integer.valueOf(2));
  }
  
  public String jnarioFile;
}
