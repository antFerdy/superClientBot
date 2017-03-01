package utils;

import javax.inject.Inject;
import play.libs.ws.*;
import models.Message;
import models.Update;

public class UpdateHandler {

	private static final String url = "https://api.telegram.org/bot283733008:AAGYER7EsbD0ESpkJ3tsaBJgvAet6sg8UiI/sendMessage";
	private WSClient ws;
	
	public UpdateHandler(WSClient ws) {
		// TODO Auto-generated constructor stub
		this.ws = ws;
	}

	public void handle(Update u) {
		sendMessage(String.valueOf(u.getMessage().getChat().getId()), "Hello bitches");
		
	}
	
	public void sendMessage(String chat_id, String text) {
		WSRequest request = ws.url(url);
		request.setQueryParameter("chat_id", chat_id);
		request.setQueryParameter("text", text);
		request.get();
	}

}
