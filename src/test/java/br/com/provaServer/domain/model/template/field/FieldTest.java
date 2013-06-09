package br.com.provaServer.domain.model.template.field;

import static br.com.provaServer.infrastructure.util.TestUtil.i18n;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.domain.model.template.field.Field;
import br.com.provaServer.domain.model.template.field.FieldType;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class FieldTest {
	private MockValidator validator = new MockValidator();
	
	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void labelShouldBeRequired() {
		Field field = Fixture.from(Field.class).gimme("valid");
		field.setLabel(null);
		
		field.validate(validator);
		
		assertTrue("label should be required", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", i18n("field.label")));
	}
	
	@Test
	public void typeShouldBeRequired() {
		Field field = Fixture.from(Field.class).gimme("valid");
		field.setType(null);
		
		field.validate(validator);
		
		assertTrue("type should be required", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", i18n("field.type")));
	}
	
	@Test
	public void fieldOfRadioTypeShouldHaveAtLeastOneRadio() {
		Field field = Fixture.from(Field.class).gimme("radioField");
		field.setRadios(null);
		
		field.validate(validator);
		
		assertTrue("field of radio type should have at least one Radio", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.field.atLeastOneRadio"));
	}
	
	@Test
	public void fieldOfTypeDifferentFromRadioShouldNotRequireRadios() {
		Field field = Fixture.from(Field.class).gimme("radioField");
		field.setRadios(null);
		
		for(FieldType type : FieldType.values()) {
			if(type != FieldType.RADIO) {
				validator = new MockValidator();
				field.setType(type);
				field.validate(validator);
				
				assertFalse("radios should not be required for types differents from radio", validator.hasErrors());
			}
		}
	}
}
