package br.com.provaServer.infrastructure.persistence.mongo.configuration;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

import com.mongodb.Mongo;

@Component
@ApplicationScoped
public class MongoTemplateFactory implements ComponentFactory<MongoTemplate> {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("prova-server");
	private static final String HOST = BUNDLE.getString("mongo.database.host");
	private static final String DATABASE_NAME = BUNDLE.getString("mongo.database.name");
	private MongoTemplate mongoTemplate;
	
	public MongoTemplateFactory(ServletContext context) throws Exception {
		mongoTemplate = new MongoTemplate(new Mongo(HOST), DATABASE_NAME);
	}

	@Override
	public MongoTemplate getInstance() {
		return mongoTemplate;
	}
}
