package controllers;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Update;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import utils.UpdateHandler;

public class BotController extends Controller{
	@Inject 
	WSClient ws;
	
	
	
	public Result update() {
		JsonNode json = request().body().asJson();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Update u = mapper.readValue(json.toString(), Update.class);
			System.err.println("DATE " + u.getMessage().getDate());
			UpdateHandler handler = new UpdateHandler(ws);
			handler.handle(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok("Hello guys! I am superClient Bot");
	}

	public Result get() {
		return ok("Hello guys! I am superClient Bot");
	}
}
