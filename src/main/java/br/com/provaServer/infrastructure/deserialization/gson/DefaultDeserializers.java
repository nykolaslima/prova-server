package br.com.provaServer.infrastructure.deserialization.gson;

import br.com.caelum.vraptor.deserialization.Deserializer;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@ApplicationScoped
@Component
public class DefaultDeserializers extends br.com.caelum.vraptor.deserialization.DefaultDeserializers {
	@Override
	public Deserializer deserializerFor(String contentType, Container container) {
		register(GsonDeserialization.class);
		
		return super.deserializerFor(contentType, container);
	}
}
