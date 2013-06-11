package br.com.provaServer.domain.model.template.field.validation;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;

public class RequiredValidationTest {
	
	private MockValidator validator = new MockValidator();
	
	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void shouldValidateNullValuesAsRequired() {
		new RequiredValidation(validator).validate("name", null);
		
		assertTrue(validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", "name"));
	}
	
	@Test
	public void shouldValidateEmptyValuesAsRequired() {
		new RequiredValidation(validator).validate("name", "");
		
		assertTrue(validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", "name"));
	}
	
	@Test
	public void shouldValidateEmptyValuesWithSpacesAsRequired() {
		new RequiredValidation(validator).validate("name", "       ");
		
		assertTrue(validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", "name"));
	}
	
}
