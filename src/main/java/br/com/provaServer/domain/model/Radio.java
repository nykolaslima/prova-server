package br.com.provaServer.domain.model;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public class Radio {
	private String label;
	
	public void validate(Validator validator) {
		validator.checking(new Validations() {
			{
				that(notEmpty(getLabel()), "validation", "validation.required", i18n("radio.label"));
			}
		});
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
