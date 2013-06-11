package br.com.provaServer.domain.model.template.field.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.domain.model.template.field.Field;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class FieldValidationBuilderTest {
	
	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void shouldBuildRequiredValidationIfRequiredAttributeIsTrue() {
		Field field = Fixture.from(Field.class).gimme("requiredField");
		
		List<FieldValidation> validations = new FieldValidationBuilder(new MockValidator()).getValidations(field);
		
		assertThat(validations, hasSize(1));
		assertThat(validations.get(0), is(instanceOf(RequiredValidation.class)));
	}
	
}
