package br.com.provaServer.application.controller.rest;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.provaServer.domain.model.repository.TemplateRepository;
import br.com.provaServer.domain.model.template.Data;
import br.com.provaServer.domain.model.template.Template;

@Resource
public class DataController {
	private Result result;
	private TemplateRepository templateRepository;
	
	public DataController(Result result, TemplateRepository templateRepository) {
		this.result = result;
		this.templateRepository = templateRepository;
	}
	
	@Post("/templates/{idTemplate}/data")
	@Consumes("application/json")
	public void add(Data data, String idTemplate) {
		Template template = templateRepository.load(idTemplate);
		template.addData(data);
		
		templateRepository.update(template);
		
		result.use(Results.status()).ok();
	}
	
	@Get("/templates/{idTemplate}/data")
	public void list(String idTemplate) {
		result.use(Results.json()).withoutRoot().from(templateRepository.load(idTemplate)).
			exclude("id", "title").include("fields").
			exclude("fields.type", "fields.required", "fields.readOnly", 
					"fields.value", "fields.maxLength", "fields.placeholder").
			include("data").serialize();
	}
}
