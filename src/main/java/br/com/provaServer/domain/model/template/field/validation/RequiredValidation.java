package br.com.provaServer.domain.model.template.field.validation;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public class RequiredValidation implements FieldValidation {

	private Validator validator;

	public RequiredValidation(Validator validator) {
		this.validator = validator;
	}
	
	@Override
	public Validator validate(final String fieldName, final String value) {
		validator.checking(new Validations() {
			{
				that(notEmpty(value), "validation", "validation.required", fieldName);
			}
		});
		
		return validator;
	}

}
