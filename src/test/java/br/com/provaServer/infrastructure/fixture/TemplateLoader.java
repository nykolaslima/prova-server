package br.com.provaServer.infrastructure.fixture;

import br.com.provaServer.domain.model.Field;
import br.com.provaServer.domain.model.FieldType;
import br.com.provaServer.domain.model.Radio;
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
			Fixture.of(br.com.provaServer.domain.model.Template.class).addTemplate("valid", new Rule() {{
				add("title", regex("\\w{15}"));
			}});
		}
	}
	
	private static class FieldTemplate {
		public static void loadTemplates() {
			Fixture.of(Field.class).addTemplate("valid", new Rule() {{
				add("label", random("name", "age", "last name"));
				add("type", random(FieldType.class));
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
