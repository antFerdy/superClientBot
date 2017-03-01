package controllers;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Update;
import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	public Result getMain() {
		JsonNode json = request().body().asJson();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Update u = mapper.readValue(json.toString(), Update.class);
			System.err.println("DATE " + u.getMessage().getDate());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonNode idNode = json.path("update_id");
		if(idNode == null) {
			System.err.println("FUCK((");
		}
		if(idNode.isMissingNode()) {
			System.err.println("MISSING NODE");
		} else {
			System.err.println("ID IS: " + idNode.asInt());
		}
		
		
		return ok("Hello guys! I am superClient Bot");
	}

	public Result updateOccured() {		
		Optional<String> type = request().contentType();
		if(type.isPresent()) {
			String rs = type.get();
			
			System.out.println(rs);
			return ok(rs);
		}
		
		return badRequest();
	}
}
