package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	
	public Result getMain() {
		String reqText = request().body().asJson().textValue();
		Logger.info(reqText);
		return ok("Hello guys! I am superClient Bot");
	}

	public Result updateOccured() {
		String reqText = request().body().asJson().textValue();
		if(!reqText.isEmpty()) {
			System.out.println(reqText);
		}
		
		Logger.info(reqText);
		Logger.info("hello world");
		return ok();
	}
}
