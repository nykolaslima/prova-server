package br.com.provaServer.infrastructure.persistence.mongo.configuration;

import javax.servlet.ServletContext;

import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

import com.mongodb.Mongo;

@Component
@ApplicationScoped
public class MongoTemplateFactory implements ComponentFactory<MongoTemplate> {
	private MongoTemplate mongoTemplate;
	
	public MongoTemplateFactory(ServletContext context) throws Exception {
		mongoTemplate = new MongoTemplate(new Mongo("localhost"), "provaServer");
	}

	@Override
	public MongoTemplate getInstance() {
		return mongoTemplate;
	}
}
