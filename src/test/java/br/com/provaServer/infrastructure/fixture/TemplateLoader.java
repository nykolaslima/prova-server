package br.com.provaServer.infrastructure.fixture;

import br.com.provaServer.domain.model.Field;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class TemplateLoader {
	public static void loadTemplates() {
		Template.loadTemplates();
		FieldTemplate.loadTemplates();
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
			}});
		}
	}
}
