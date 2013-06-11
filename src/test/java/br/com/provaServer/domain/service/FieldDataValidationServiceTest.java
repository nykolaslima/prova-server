package br.com.provaServer.domain.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.domain.model.template.Data;
import br.com.provaServer.domain.model.template.Template;
import br.com.provaServer.domain.model.template.field.Field;
import br.com.provaServer.domain.model.template.field.validation.FieldValidationBuilder;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class FieldDataValidationServiceTest {
	
	private MockValidator validator = new MockValidator();
	
	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void shouldValidateIfExistsFieldWithGivingLabelInTemplate() {
		Template template = Fixture.from(Template.class).gimme("valid");
		
		Data data = new Data();
		Map<String, String> dataValues = new HashMap<String, String>();
		dataValues.put("invalidFieldLabel", "nykolas");
		data.setValues(dataValues);
		
		new FieldDataValidationService(validator, mock(FieldValidationBuilder.class)).validateDataForTemplate(template, data);
		
		assertTrue(validator.hasErrors());
		assertTrue(validator.containsMessage("validation.field.invalid", "invalidFieldLabel"));
	}
	
	@Test
	public void shouldBuildValidationsOfExistingFieldsPassedFromData() {
		Template template = Fixture.from(Template.class).gimme("valid");
		
		FieldValidationBuilder validationBuilder = mock(FieldValidationBuilder.class);
		
		Data data = new Data();
		Map<String, String> dataValues = new HashMap<String, String>();
		for(Field field : template.getFields()) {
			dataValues.put(field.getLabel(), "field value for label " + field.getLabel());
		}
		data.setValues(dataValues);
		
		new FieldDataValidationService(validator, validationBuilder).validateDataForTemplate(template, data);
		
		for(Field field : template.getFields()) {
			verify(validationBuilder, atLeastOnce()).getValidations(field);
		}
	}
}
