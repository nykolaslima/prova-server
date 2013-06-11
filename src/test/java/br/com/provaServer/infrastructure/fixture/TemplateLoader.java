package br.com.provaServer.infrastructure.fixture;

import br.com.provaServer.domain.model.template.field.Field;
import br.com.provaServer.domain.model.template.field.FieldType;
import br.com.provaServer.domain.model.template.field.Radio;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class TemplateLoader {
	public static void loadTemplates() {
		Template.loadTemplates();
		FieldTemplate.loadTemplates();
		RadioTemplate.loadTemplates();
	}
	
	private static class Template {
		public static void loadTemplates() {
			Fixture.of(br.com.provaServer.domain.model.template.Template.class).addTemplate("valid", new Rule() {{
				add("title", regex("\\w{15}"));
				add("fields", has(3).of(Field.class, "valid"));
			}});
		}
	}
	
	private static class FieldTemplate {
		public static void loadTemplates() {
			Fixture.of(Field.class).addTemplate("valid", new Rule() {{
				add("label", regex("\\w{10}"));
				add("type", random(FieldType.class));
			}})
			.addTemplate("requiredField").inherits("valid", new Rule() {{
				add("required", true);
			}})
			.addTemplate("fieldWithMaxLength").inherits("valid", new Rule() {{
				add("maxLength", 10);
			}})
			.addTemplate("radioField").inherits("valid", new Rule() {{
				add("type", FieldType.RADIO);
				add("radios", has(2).of(Radio.class, "valid"));
			}});
		}
	}
	
	private static class RadioTemplate {
		public static void loadTemplates() {
			Fixture.of(Radio.class).addTemplate("valid", new Rule() {{
				add("label", "sex");
				add("value", random("male", "female"));
			}});
		}
	}
}
