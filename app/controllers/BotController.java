package controllers;

import java.util.Map;
import java.util.Set;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class BotController extends Controller{
	
	public Result getMain() {
		return ok("Hello guys! I am superClient Bot");
	}

	public Result updateOccured() {
//		String reqText = request().getHeader(CONTENT_TYPE);
//		Logger.info("Content type: ");
//		Logger.info(reqText);
		
		Map<String, String[]> headers = request().headers();
		Set<String> keys = headers.keySet();
		
		for(String key : keys) {
			Logger.info("The key is: " + key);
			String[] values = headers.get(key);
			for(String value : values) {
				Logger.info("The Values of the key: " + value);
			}
		}
		
		return ok("Privet");
	}
}
