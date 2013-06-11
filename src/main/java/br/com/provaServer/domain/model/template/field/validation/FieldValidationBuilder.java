package br.com.provaServer.domain.model.template.field.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.provaServer.domain.model.template.field.Field;

@Component
@RequestScoped
public class FieldValidationBuilder {

	private Validator validator;

	public FieldValidationBuilder(Validator validator) {
		this.validator = validator;
	}
	
	public List<FieldValidation> getValidations(Field field) {
		List<FieldValidation> validations = new ArrayList<FieldValidation>();
		
		if(field.isRequired()) {
			validations.add(new RequiredValidation(validator));
		}
		if(field.getMaxLength() != null) {
			validations.add(new MaxLengthValidation(validator, field.getMaxLength()));
		}
		
		return validations;
	}

}
