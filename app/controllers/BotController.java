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
		String txt = request().body().asJson().textValue();
		System.out.println(txt);
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
