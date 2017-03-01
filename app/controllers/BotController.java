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
		String txt = request().body().asJson().textValue();
		System.out.println(txt);
		return ok("Hello guys! I am superClient Bot");
	}

	public Result updateOccured() {		
		String txt = request().body().asJson().textValue();
		System.out.println(txt);
		
		return ok("Privet");
	}
}
