package br.com.provaServer.domain.model;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public class Field {
	private String label;
	private FieldType type;
	private boolean required;

	public String getLabel() {
		return label;
	}
	
	public FieldType getType() {
		return type;
	}
	
	public boolean isRequired() {
		return required;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setType(FieldType type) {
		this.type = type;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}

	public void validate(Validator validator) {
		validator.checking(new Validations() {
			{
				that(notEmpty(getLabel()), "validation", "validation.required", i18n("field.label"));
				that(getType() != null, "validation", "validation.required", i18n("field.type"));
			}
		});
	}
}
