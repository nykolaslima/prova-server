package br.com.provaServer.domain.model.template.field.validation;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;

public class MaxLengthValidationTest {
	
	private MockValidator validator = new MockValidator();
	
	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void shouldValidateValuesWithLengthGreaterThanConfiguredMaxLength() {
		new MaxLengthValidation(validator, 10).validate("name", "name with more than 10 characters");
		
		assertTrue(validator.hasErrors());
		assertTrue(validator.containsMessage("validation.field.maxLength", "name", 10));
	}
}
