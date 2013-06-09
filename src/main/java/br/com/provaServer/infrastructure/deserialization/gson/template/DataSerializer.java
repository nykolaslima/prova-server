package br.com.provaServer.infrastructure.deserialization.gson.template;

import java.lang.reflect.Type;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.provaServer.domain.model.template.Data;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component
@RequestScoped
public class DataSerializer implements JsonSerializer<Data> {

	@Override
	public JsonElement serialize(Data data, Type type, JsonSerializationContext context) {
		String jsonDataValue = "\"%s\": \"%s\"";
		StringBuilder jsonValues = new StringBuilder();
		for(Map.Entry<String, String> entry : data.getValues().entrySet()) {
			if(jsonValues.length() > 0) {
				jsonValues.append(",");
			}
			jsonValues.append(String.format(jsonDataValue, entry.getKey(), entry.getValue()));
		}
		
		StringBuilder json = new StringBuilder();
		json.append("{").append(jsonValues).append("}");
		
		return new JsonParser().parse(json.toString());
	}

}
