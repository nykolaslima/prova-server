package br.com.provaServer.domain.model;

import static br.com.provaServer.infrastructure.util.TestUtil.i18n;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
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
}
