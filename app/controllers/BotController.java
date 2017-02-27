package controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	public Result getMain() {
		return ok("Hello guys! I am superClient Bot");
	}

	public Result updateOccured() {		
		JsonNode json = request().body().asJson();
		Iterator<JsonNode> it = json.elements();
		
		while(it.hasNext()) {
			Logger.info(it.next().textValue());
		}
		
		return ok("Privet");
	}
}
