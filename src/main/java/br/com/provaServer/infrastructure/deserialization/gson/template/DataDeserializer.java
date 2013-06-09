package br.com.provaServer.infrastructure.deserialization.gson.template;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.provaServer.domain.model.template.Data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

@Component
@RequestScoped
public class DataDeserializer implements JsonDeserializer<Data> {

	@Override
	public Data deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = (JsonObject) jsonElement;
		
		Data data = new Data();
		data.setValues(new HashMap<String, String>());
		
		Set<Entry<String,JsonElement>> entrySet = jsonObject.entrySet();
		for(Entry<String,JsonElement> entry : entrySet) {
			data.getValues().put(entry.getKey(), entry.getValue().getAsString());
		}
		
		return data;
	}

}
