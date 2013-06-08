package br.com.provaServer.infrastructure.deserialization.gson;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.deserialization.Deserializes;
import br.com.caelum.vraptor.http.ParameterNameProvider;

import com.google.gson.JsonDeserializer;

@Deserializes("application/gson")
public class GsonDeserialization extends br.com.caelum.vraptor.deserialization.gson.GsonDeserialization {

	@SuppressWarnings("rawtypes")
	public GsonDeserialization(ParameterNameProvider paramNameProvider,	List<JsonDeserializer> adapters, HttpServletRequest request) {
		super(paramNameProvider, adapters, request);
	}

}
