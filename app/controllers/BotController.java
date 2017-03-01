package controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	public Result getMain() {
		JsonNode json = request().body().asJson();
		JsonNode idNode = json.path("update_id");
		if(idNode == null) {
			System.out.println("FUCK((");
		}
		if(idNode.isMissingNode()) {
			System.out.println("MISSING NODE");
		} else {
			System.out.print("ID IS: " + idNode.asInt());
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
