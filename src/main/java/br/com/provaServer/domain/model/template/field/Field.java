package br.com.provaServer.domain.model.template.field;

import static br.com.provaServer.infrastructure.validation.util.ValidationUtil.notEmpty;

import java.util.List;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public class Field {
	private String label;
	private FieldType type;
	private boolean required;
	private boolean readOnly;
	private String value;
	private Integer maxLength;
	private String placeholder;
	private List<Radio> radios;

	public void validate(Validator validator) {
		validator.checking(new Validations() {
			{
				that(notEmpty(getLabel()), "validation", "validation.required", i18n("field.label"));
				that(getType() != null, "validation", "validation.required", i18n("field.type"));
				
				if(getType() == FieldType.RADIO) {
					that(notEmpty(getRadios()), "validation", "validation.field.atLeastOneRadio");
				}
			}
		});
	}

	public String getLabel() {
		return label;
	}

	public FieldType getType() {
		return type;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public String getValue() {
		return value;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public List<Radio> getRadios() {
		return radios;
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

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public void setRadios(List<Radio> radios) {
		this.radios = radios;
	}
}
