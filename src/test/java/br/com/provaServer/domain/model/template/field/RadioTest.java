package br.com.provaServer.domain.model.template.field;

import static br.com.provaServer.infrastructure.util.TestUtil.i18n;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.domain.model.template.field.Radio;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class RadioTest {
	private MockValidator validator = new MockValidator();
	
	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void labelShouldBeRequired() {
		Radio radio = Fixture.from(Radio.class).gimme("valid");
		radio.setLabel(null);
		
		radio.validate(validator);
		
		assertTrue("label should be required", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", i18n("radio.label")));
	}
	
	@Test
	public void valueShouldBeRequired() {
		Radio radio = Fixture.from(Radio.class).gimme("valid");
		radio.setValue(null);
		
		radio.validate(validator);
		
		assertTrue("value should be required", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", i18n("radio.value")));
	}
}
