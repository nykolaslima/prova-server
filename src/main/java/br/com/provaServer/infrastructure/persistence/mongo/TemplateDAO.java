package br.com.provaServer.infrastructure.persistence.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.provaServer.domain.model.repository.TemplateRepository;
import br.com.provaServer.domain.model.template.Template;

@Component
@RequestScoped
public class TemplateDAO implements TemplateRepository {
	private MongoTemplate mongo;
	
	public TemplateDAO(MongoTemplate mongo) {
		this.mongo = mongo;
	}
	
	@Override
	public void add(Template template) {
		mongo.insert(template);
	}

	@Override
	public Template load(String id) {
		return mongo.findById(id, Template.class);
	}

	@Override
	public List<Template> list() {
		return mongo.findAll(Template.class);
	}

	@Override
	public void update(Template template) {
		if(load(template.getId()) == null) {
			throw new IllegalArgumentException("There is no Template with this ID to be updated");
		}
		
		mongo.save(template);
	}

}
