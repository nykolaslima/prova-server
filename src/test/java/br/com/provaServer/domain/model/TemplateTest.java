package br.com.provaServer.domain.model;

import static br.com.provaServer.infrastructure.util.TestUtil.i18n;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.domain.model.template.Template;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class TemplateTest {
	private MockValidator validator = new MockValidator();
	
	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void titleShouldBeRequired() {
		Template template = Fixture.from(Template.class).gimme("valid");
		template.setTitle(null);
		
		template.validate(validator);
		
		assertTrue("title should be required", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.required", i18n("template.title")));
	}
	
	@Test
	public void shouldHaveAtLeastOneField() {
		Template template = Fixture.from(Template.class).gimme("valid");
		template.setFields(null);
		
		template.validate(validator);
		
		assertTrue(validator.hasErrors());
		assertTrue(validator.containsMessage("validation.template.atLeastOneField"));
		
		validator = new MockValidator();
		template.setFields(new ArrayList<Field>());
		
		template.validate(validator);
		
		assertTrue("should have at least one field", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.template.atLeastOneField"));
	}
}
