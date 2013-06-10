package br.com.provaServer.infrastructure.deserialization.gson.template.field;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;

import br.com.provaServer.domain.model.template.field.FieldType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonPrimitive;

public class FieldTypeDeserializerTest {
	@Test
	public void shouldDeserializeFieldTypeEnumLowerCase() {
		JsonPrimitive jsonPrimitive = new JsonPrimitive("date");
		
		FieldType fieldType = new FieldTypeDeserializer().deserialize(jsonPrimitive, mock(Type.class), mock(JsonDeserializationContext.class));
		
		assertEquals("should deserialize Field Type enum with lower case", FieldType.DATE, fieldType);
	}
	
	@Test
	public void shouldDeserializeFieldTypeEnumCamelCase() {
		JsonPrimitive jsonPrimitive = new JsonPrimitive("Date");
		
		FieldType fieldType = new FieldTypeDeserializer().deserialize(jsonPrimitive, mock(Type.class), mock(JsonDeserializationContext.class));
		
		assertEquals("should deserialize Field Type enum with camel case", FieldType.DATE, fieldType);
	}
}
