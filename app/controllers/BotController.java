package controllers;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import models.TestEntity;
import models.Update;
import utils.UpdateHandler;

public class BotController extends Controller{
	@Inject 
	WSClient ws;
	
	@Inject
	JPAApi jpaApi;
	
	
	public Result update() {
		JsonNode json = request().body().asJson();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Update u = mapper.readValue(json.toString(), Update.class);
			UpdateHandler handler = new UpdateHandler(ws);
			handler.handle(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok("Hello guys! I am superClient Bot");
	}
	
	@Transactional
	public Result get() {
		TestEntity entity = new TestEntity();
		entity.setId(1);
		entity.setName("RUSTEM");
		jpaApi.em().persist(entity);
		return ok("Hello guys! I am superClient Bot");
	}
}
