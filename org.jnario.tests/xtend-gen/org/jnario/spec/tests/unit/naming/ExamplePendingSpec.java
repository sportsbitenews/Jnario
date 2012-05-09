package org.jnario.spec.tests.unit.naming;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jnario.lib.ExampleTable;
import org.jnario.lib.ExampleTableIterators;
import org.jnario.lib.Should;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.jnario.spec.tests.unit.naming.ExamplePendingSpecExamples;
import org.jnario.spec.tests.unit.naming.ExampleSpec;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(ExampleGroupRunner.class)
@Named("Pending")
public class ExamplePendingSpec extends ExampleSpec {
  @Before
  public void _initExamplePendingSpecExamples() {
    examples = ExampleTable.create("examples", 
      java.util.Arrays.asList("example", "expected"), 
      new ExamplePendingSpecExamples(  java.util.Arrays.asList("\"fact \'with description\'\"", "true"), "fact \'with description\'", true),
      new ExamplePendingSpecExamples(  java.util.Arrays.asList("\"fact \'with description and empty block\' {}\"", "true"), "fact \'with description and empty block\' {}", true),
      new ExamplePendingSpecExamples(  java.util.Arrays.asList("\"fact \'with description and code block\' {1 => 1}\"", "false"), "fact \'with description and code block\' {1 => 1}", false),
      new ExamplePendingSpecExamples(  java.util.Arrays.asList("\"fact 1 => 1\"", "false"), "fact 1 => 1", false)
    );
  }
  
  protected ExampleTable<ExamplePendingSpecExamples> examples;
  
  @Test
  @Named("examples.forEach[pendingStateOf[example] should be expected]")
  @Order(99)
  public void examplesForEachPendingStateOfExampleShouldBeExpected() throws Exception {
    final Procedure1<ExamplePendingSpecExamples> _function = new Procedure1<ExamplePendingSpecExamples>() {
        public void apply(final ExamplePendingSpecExamples it) {
          boolean _pendingStateOf = ExamplePendingSpec.this.pendingStateOf(it.example);
          boolean _should_be = Should.<Boolean>should_be(_pendingStateOf, it.expected);Assert
          .assertTrue("\nExpected pendingStateOf(example) should be expected but"
           + "\n     pendingStateOf(example) is " + _pendingStateOf
           + "\n     example is " + "\"" + it.example + "\""
           + "\n     expected is " + it.expected + "\n", _should_be);
          
        }
      };
    ExampleTableIterators.<ExamplePendingSpecExamples>forEach(this.examples, _function);
  }
}
