package controllers;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Update;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import utils.UpdateHandler;

public class BotController extends Controller{
	@Inject 
	WSClient ws;
	
	@Inject
	JPAApi jpaApi;
	
	@Transactional
	public Result update() {
		JsonNode json = request().body().asJson();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Update u = mapper.readValue(json.toString(), Update.class);
			UpdateHandler handler = new UpdateHandler(ws, jpaApi);
			handler.handle(u);
		} catch (Exception e) {
			return internalServerError(e.getMessage());
		}
		return ok();
	}
	
	@Transactional
	public Result get() {
		return ok("Hello guys! I am superClient Bot");
	}
}
