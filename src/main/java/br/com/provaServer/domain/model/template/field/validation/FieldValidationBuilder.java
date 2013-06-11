package br.com.provaServer.domain.model.template.field.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Validator;
import br.com.provaServer.domain.model.template.field.Field;

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
		
		return validations;
	}

}
