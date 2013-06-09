package br.com.provaServer.domain.model.template.validation;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.provaServer.domain.model.repository.TemplateRepository;
import br.com.provaServer.domain.model.template.Template;

public class TemplateUpdateValidation {
	private TemplateRepository templateRepository;
	private Validator validator;
	
	public TemplateUpdateValidation(TemplateRepository templateRepository, Validator validator) {
		this.templateRepository = templateRepository;
		this.validator = validator;
	}
	
	public void validate(Template template) {
		if(templateRepository.load(template.getId()) == null) {
			I18nMessage errorMessage = new I18nMessage("validation", "validation.template.noTemplateWithGivenIdToUpdate");
			errorMessage.setBundle(ResourceBundle.getBundle("messages"));
			validator.add(errorMessage);
			return;
		}
		
		template.validate(validator);
	}
}
