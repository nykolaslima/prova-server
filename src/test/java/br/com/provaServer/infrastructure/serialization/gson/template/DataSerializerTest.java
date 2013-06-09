package br.com.provaServer.infrastructure.serialization.gson.template;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.provaServer.domain.model.template.Data;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

public class DataSerializerTest {
	
	@Test
	public void shouldSerializeDataWithOneDataField() {
		String expectedResult = "{\"name\":\"nykolas\"}";
		
		Data data = new Data();
		Map<String, String> dataFields = new HashMap<String, String>();
		dataFields.put("name", "nykolas");
		data.setValues(dataFields);
		
		JsonElement jsonElement = new DataSerializer().serialize(data, mock(Type.class), mock(JsonSerializationContext.class));
		
		assertEquals(expectedResult, jsonElement.getAsJsonObject().toString());
	}
	
	@Test
	public void shouldSerializeDataWithManyDataFields() {
		String expectedResult = "{\"age\":\"22\",\"name\":\"nykolas\"}";
		
		Data data = new Data();
		Map<String, String> dataFields = new HashMap<String, String>();
		dataFields.put("name", "nykolas");
		dataFields.put("age", "22");
		data.setValues(dataFields);
		
		JsonElement jsonElement = new DataSerializer().serialize(data, mock(Type.class), mock(JsonSerializationContext.class));
		
		assertEquals(expectedResult, jsonElement.getAsJsonObject().toString());
	}
}
