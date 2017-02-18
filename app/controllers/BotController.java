package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	
	public Result getMain() {
		return ok("Hello guys! I am superClient Bot");
	}
}
