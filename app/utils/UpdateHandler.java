package utils;

import javax.inject.Inject;
import play.libs.ws.*;
import models.Message;
import models.Update;

public class UpdateHandler {

	private static final String url = "https://api.telegram.org/bot283733008:AAGYER7EsbD0ESpkJ3tsaBJgvAet6sg8UiI/sendMessage";
	private WSClient ws;
	
	private static final String firtReply = "Привет! Я робот, собирающий отзывы о компаниях. Делаю Клиентов и компании ближе друг к другу. "
			+ "\nОтзыв о какой компании вы хотите оставить (название компании)?";
	
	public UpdateHandler(WSClient ws) {
		// TODO Auto-generated constructor stub
		this.ws = ws;
	}

	public void handle(Update u) {
		String msgTxt = u.getMessage().getText();
		long chatId = u.getMessage().getChat().getId();
		if(msgTxt.equals("/start")) {
			sendMessage(chatId, firtReply);
		}
		
		
		
		
	}
	
	public void sendMessage(long chat_id, String text) {
		WSRequest request = ws.url(url);
		request.setQueryParameter("chat_id", String.valueOf(chat_id));
		request.setQueryParameter("text", text);
		request.get();
	}

}
