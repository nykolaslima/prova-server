package br.com.provaServer.infrastructure.deserialization.gson.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;

import br.com.provaServer.domain.model.template.Data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class DataDeserializerTest {
	private DataDeserializer deserializer = new DataDeserializer();
	
	@Test
	public void shouldDeserializeDataWithOneDataField() {
		String json = "{name: \"nykolas\"}";
		JsonElement jsonElement = new JsonParser().parse(json);
		
		Data data = deserializer.deserialize(jsonElement, mock(Type.class), mock(JsonDeserializationContext.class));
		
		assertTrue(data.getValues().containsKey("name"));
		assertEquals(data.getValues().get("name"), "nykolas");
	}
	
	@Test
	public void shouldDeserializeDataWithManyFields() {
		String json = "{name: \"nykolas\", age: \"22\", city: \"sao paulo\"}";
		JsonElement jsonElement = new JsonParser().parse(json);
		
		Data data = deserializer.deserialize(jsonElement, mock(Type.class), mock(JsonDeserializationContext.class));
		
		assertTrue(data.getValues().size() == 3);
		assertTrue(data.getValues().containsKey("name"));
		assertEquals(data.getValues().get("name"), "nykolas");
		assertTrue(data.getValues().containsKey("age"));
		assertEquals(data.getValues().get("age"), "22");
		assertTrue(data.getValues().containsKey("city"));
		assertEquals(data.getValues().get("city"), "sao paulo");
	}
}
