package br.com.provaServer.domain.model.template;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.provaServer.domain.model.template.field.Field;

@Document
public class Template {
	@Id
	private String id;
	private String title;
	private List<Field> fields;
	private List<Data> data;
	
	public void validate(final Validator validator) {
		validator.checking(new Validations() {
			{
				that(notEmpty(getTitle()), "validation", "validation.required", i18n("template.title"));
				if(that(notEmpty(getFields()), "validation", "validation.template.atLeastOneField")) {
					for(Field field : fields) {
						field.validate(validator);
					}
				}
			}
		});
	}
	
	public void addData(Data data) {
		if(this.data == null) this.data = new ArrayList<Data>();
		
		this.data.add(data);
	}

	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	public List<Data> getData() {
		return data;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
