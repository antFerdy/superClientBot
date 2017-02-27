package controllers;

import java.util.concurrent.CompletionStage;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	public Result getMain() {
		return ok("Hello guys! I am superClient Bot");
	}

	public Result updateOccured() {
		String reqText = request().body().asRaw().toString();
		Logger.info(reqText);
		Logger.info("hello world");
		return ok();
	}
}
