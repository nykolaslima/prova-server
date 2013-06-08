package br.com.provaServer.application.controller.rest;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.provaServer.domain.model.Template;
import br.com.provaServer.domain.model.repository.TemplateRepository;

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
	@Consumes("application/gson")
	public void add(Template template) {
		template.validate(validator);
		validator.onErrorSendBadRequest();
		
		templateRepository.add(template);
		
		result.nothing();
	}
}
