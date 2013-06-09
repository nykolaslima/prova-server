package br.com.provaServer.domain.model.repository;

import java.util.List;

import br.com.provaServer.domain.model.Template;

public interface TemplateRepository {
	public void add(Template template);
	
	public Template load(String id);
	
	public List<Template> list();
}
