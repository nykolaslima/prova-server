package br.com.provaServer.domain.model;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Validations;

public class Template {
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void validate(MockValidator validator) {
		validator.checking(new Validations() {
			{
				that(notEmpty(getTitle()), "validation", "validation.required", i18n("template.title"));
			}
		});
	}
}
