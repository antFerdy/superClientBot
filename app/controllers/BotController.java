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
		
//		RequestBody body = request().body();
//		Logger.info("Size of body as json: " + body.asJson().size());
		
//		play.mvc.Http.MultipartFormData form = request().body().asMultipartFormData();
//		List files = form.getFiles();
		
		
		
		
		
		
		Map<String, String[]> mapForm = request().body().asFormUrlEncoded();
		if(mapForm != null || !mapForm.equals(null))
			Logger.info("Size of map form " + mapForm.size());
		
//		Set<String> keysForm = mapForm.keySet();
//		for(String key : keysForm) {
//			Logger.info("The key in FORM is: " + key);
//			String[] values = mapForm.get(key);
//			for(String value : values) {
//				Logger.info("The Values of the form key: " + value);
//			}
//		}
		
		return ok("Privet");
	}
}
