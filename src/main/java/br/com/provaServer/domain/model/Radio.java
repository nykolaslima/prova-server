package br.com.provaServer.domain.model;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public class Radio {
	private String label;
	private String value;
	
	public void validate(Validator validator) {
		validator.checking(new Validations() {
			{
				that(notEmpty(getLabel()), "validation", "validation.required", i18n("radio.label"));
				that(notEmpty(getValue()), "validation", "validation.required", i18n("radio.value"));
			}
		});
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
