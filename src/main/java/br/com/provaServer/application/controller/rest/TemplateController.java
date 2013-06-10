package br.com.provaServer.application.controller.rest;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.provaServer.domain.model.repository.TemplateRepository;
import br.com.provaServer.domain.model.template.Data;
import br.com.provaServer.domain.model.template.Template;
import br.com.provaServer.domain.model.template.validation.TemplateUpdateValidation;

@Resource
public class TemplateController {
	private Result result;
	private Validator validator;
	private TemplateRepository templateRepository;
	
	public TemplateController(Result result, Validator validator, TemplateRepository templateRepository) {
		this.result = result;
		this.validator = validator;
		this.templateRepository = templateRepository;
	}
	
	@Post("/templates")
	@Consumes("application/json")
	public void add(Template template) {
		template.validate(validator);
		validator.onErrorSendBadRequest();
		
		templateRepository.add(template);
		
		result.use(Results.json()).withoutRoot().from(templateRepository.load(template.getId())).include("fields").include("fields.radios").serialize();
	}
	
	@Get("/templates/{id}")
	public void load(String id) {
		result.use(Results.json()).withoutRoot().from(templateRepository.load(id)).include("fields").include("fields.radios").serialize();
	}
	
	@Get("/templates")
	public void list() {
		result.use(Results.json()).withoutRoot().from(templateRepository.list()).include("fields").serialize();
	}
	
	@Put("/templates/{template.id}")
	@Consumes("application/json")
	public void update(Template template) {
		new TemplateUpdateValidation(templateRepository, validator).validate(template);
		validator.onErrorSendBadRequest();
		
		templateRepository.update(template);
		result.use(Results.json()).withoutRoot().from(templateRepository.load(template.getId())).include("fields").include("fields.radios").serialize();
	}
	
	@Delete("/templates/{id}")
	public void remove(String id) {
		templateRepository.remove(id);
		
		result.use(Results.status()).ok();
	}
	
	@Post("/templates/{idTemplate}/data")
	@Consumes("application/json")
	public void addData(Data data, String idTemplate) {
		Template template = templateRepository.load(idTemplate);
		template.addData(data);
		
		templateRepository.update(template);
		
		result.use(Results.status()).ok();
	}
	
	@Get("/templates/{idTemplate}/data")
	public void listData(String idTemplate) {
		result.use(Results.json()).withoutRoot().from(templateRepository.load(idTemplate)).
			exclude("id", "title").include("fields").
			exclude("fields.type", "fields.required", "fields.readOnly", 
					"fields.value", "fields.maxLength", "fields.placeholder").
			include("data").serialize();
	}
}
