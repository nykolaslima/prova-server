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
	@Test
	public void shouldDeserializeDataWithOneDataField() {
		String json = "{name: \"nykolas\"}";
		JsonElement jsonElement = new JsonParser().parse(json);
		
		DataDeserializer deserializer = new DataDeserializer();
		Data data = deserializer.deserialize(jsonElement, mock(Type.class), mock(JsonDeserializationContext.class));
		
		assertTrue(data.getValues().containsKey("name"));
		assertEquals(data.getValues().get("name"), "nykolas");
	}
}
