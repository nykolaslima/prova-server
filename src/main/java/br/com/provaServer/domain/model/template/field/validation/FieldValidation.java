package br.com.provaServer.domain.model.template.field.validation;

import br.com.caelum.vraptor.Validator;

public interface FieldValidation {
	
	public Validator validate(final String fieldName, final String value);
	
}
