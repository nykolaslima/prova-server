package br.com.provaServer.domain.model;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Document
public class Template {
	@Id
	private String id;
	private String title;
	private List<Field> fields;

	public String getTitle() {
		return title;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public void validate(Validator validator) {
		validator.checking(new Validations() {
			{
				that(notEmpty(getTitle()), "validation", "validation.required", i18n("template.title"));
				that(notEmpty(getFields()), "validation", "validation.template.atLeastOneField");
			}
		});
	}
}
