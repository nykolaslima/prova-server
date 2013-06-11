package br.com.provaServer.domain.model.template.field.validation;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public class MaxLengthValidation implements FieldValidation {
	
	private Validator validator;
	private int maxLength;
	
	public MaxLengthValidation(Validator validator, int maxLength) {
		this.validator = validator;
		this.maxLength = maxLength;
	}

	@Override
	public Validator validate(final String fieldName, final String value) {
		validator.checking(new Validations() {
			{
				that(value.length() <= maxLength, "validation", "validation.field.maxLength", fieldName, maxLength);
			}
		});
		
		return validator;
	}
	
}
