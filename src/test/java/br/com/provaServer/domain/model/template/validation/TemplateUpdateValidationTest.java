package br.com.provaServer.domain.model.template.validation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.provaServer.domain.model.repository.TemplateRepository;
import br.com.provaServer.domain.model.template.Template;
import br.com.provaServer.infrastructure.fixture.TemplateLoader;

public class TemplateUpdateValidationTest {

	@BeforeClass
	public static void setUp() {
		TemplateLoader.loadTemplates();
	}
	
	@Test
	public void shouldNotUpdateTemplateWhoseIdDoesNotExist() {
		TemplateRepository templateRepository = mock(TemplateRepository.class);
		when(templateRepository.load(any(String.class))).thenReturn(null);
		MockValidator validator = new MockValidator();
		Template template = mock(Template.class);
		when(template.getId()).thenReturn("1234567890");
		
		new TemplateUpdateValidation(templateRepository, validator).validate(template);
		
		assertTrue("should not update template whose id does not exist", validator.hasErrors());
		assertTrue(validator.containsMessage("validation.template.noTemplateWithGivenIdToUpdate"));
	}
}
