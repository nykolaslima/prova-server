package br.com.provaServer.domain.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.provaServer.domain.model.template.Data;
import br.com.provaServer.domain.model.template.Template;
import br.com.provaServer.domain.model.template.field.Field;
import br.com.provaServer.domain.model.template.field.validation.FieldValidation;
import br.com.provaServer.domain.model.template.field.validation.FieldValidationBuilder;

@Component
@RequestScoped
public class FieldDataValidationService {

	private Validator validator;
	private FieldValidationBuilder fieldValidationBuilder;
	
	public FieldDataValidationService(Validator validator, FieldValidationBuilder fieldValidationBuilder) {
		this.validator = validator;
		this.fieldValidationBuilder = fieldValidationBuilder;
	}
	
	public void validateDataForTemplate(Template template, Data data) {
		for(Entry<String, String> entry : data.getValues().entrySet()) {
			Field fieldForLabel = template.getFieldWithLabel(entry.getKey());
			
			if(fieldForLabel != null) {
				List<FieldValidation> validations = fieldValidationBuilder.getValidations(fieldForLabel);
				for(FieldValidation validation : validations) {
					validation.validate(entry.getKey(), entry.getValue());
				}
			} else {
				I18nMessage message = new I18nMessage("validation", "validation.field.invalid", entry.getKey());
				message.setBundle(ResourceBundle.getBundle("messages"));
				validator.add(message);
			}
		}
	}
	
}
