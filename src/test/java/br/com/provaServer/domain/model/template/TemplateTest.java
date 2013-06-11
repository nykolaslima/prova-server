package br.com.provaServer.domain.model.template;

import static br.com.provaServer.infrastructure.util.TestUtil.i18n;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.domain.model.template.field.Field;
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
	
	@Test
	public void shouldCallFieldsValidationIfThereIsAnyField() {
		Template template = Fixture.from(Template.class).gimme("valid");
		Field field = mock(Field.class);
		template.setFields(Arrays.asList(field));
		
		template.validate(validator);
		
		verify(field).validate(validator);
	}
	
	@Test
	public void shouldReturnTheFieldWithGivingLabel() {
		Template template = Fixture.from(Template.class).gimme("valid");
		Field field = Fixture.from(Field.class).gimme("valid");
		field.setLabel("myLabeledField");
		
		template.getFields().add(field);
		
		Field findedField = template.getFieldWithLabel("myLabeledField");
		
		assertTrue("should find field by his label", field == findedField);
	}
	
	@Test
	public void shouldReturnTheFieldWithGivingLabelCaseInsensitive() {
		Template template = Fixture.from(Template.class).gimme("valid");
		Field field = Fixture.from(Field.class).gimme("valid");
		field.setLabel("myLabeledField");
		
		template.getFields().add(field);
		
		Field findedField = template.getFieldWithLabel("mYLaBelEdFiEld");
		
		assertTrue("should find field by his label with case insensitive comparation", field == findedField);
	}
	
	@Test
	public void shouldReturnNullWhenTryingToFindFieldWithInvalidLabel() {
		Template template = Fixture.from(Template.class).gimme("valid");
		
		assertThat(template.getFieldWithLabel("myLabeledField"), nullValue());
	}
}
