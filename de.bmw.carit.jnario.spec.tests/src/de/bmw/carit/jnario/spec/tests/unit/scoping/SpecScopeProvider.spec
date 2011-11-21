package de.bmw.carit.jnario.spec.tests.unit.scoping

describe "SpecScopeProvider" {
 
	it "should resolve static imports"{
		val spec = '
			package bootstrap
			
			import static org.junit.Assert.*
	
			describe "Example" {
			
				it "should resolve static imports"{
					assertTrue(true) 
				} 
						
			}
		'
		val result = de::bmw::carit::jnario::spec::tests::util::SpecExecutor::execute(spec)
		org::junit::Assert::assertThat(result, org::junit::experimental::results::ResultMatchers::successful)
	} 
			
}
	