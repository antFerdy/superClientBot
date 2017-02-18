package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	
	public Result getMain() {
		return ok("Hello guys! I am superClient Bot");
	}

	public Result updateOccured() {
		String reqText = request().body().asText();
		Logger.info(reqText);
		return ok();
	}
}
