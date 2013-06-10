package br.com.provaServer.infrastructure.deserialization.gson.template.field;

import java.lang.reflect.Type;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.provaServer.domain.model.template.field.FieldType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

@Component
@RequestScoped
public class FieldTypeDeserializer implements JsonDeserializer<FieldType> {

	@Override
	public FieldType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
		
		return FieldType.valueOf(jsonPrimitive.getAsString().toUpperCase());
	}

}
