package utils;

import java.util.Date;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import play.libs.ws.*;
import models.Message;
import models.Update;

public class UpdateHandler {

	private static final String url = "https://api.telegram.org/bot283733008:AAGYER7EsbD0ESpkJ3tsaBJgvAet6sg8UiI/sendMessage";
	private WSClient ws;
	private long chatId = 0L;
	private long firstMsgTime;
	
	
	private static final String firtReply = "Привет! Я робот, собирающий отзывы о компаниях. Делаю Клиентов и компании ближе друг к другу. "
			+ "\nОтзыв о какой компании вы хотите оставить (название компании)?";
	private static final String secondReply = "В каком городе находится данная компания?";
	
	
	public UpdateHandler(WSClient ws) {
		// TODO Auto-generated constructor stub
		this.ws = ws;
	}

	public void handle(Update u) {
		String msgTxt = u.getMessage().getText();
		System.out.println(msgTxt);
		
		if(msgTxt.trim().equalsIgnoreCase("/start")) {
			sendMessage(chatId, firtReply);
			chatId = u.getMessage().getChat().getId();
			firstMsgTime = u.getMessage().getDate();
		} else {
			long id = u.getMessage().getChat().getId();
			long newTime = u.getMessage().getDate();
			
			if(id == chatId) {
				System.out.println(new Date(newTime));
				System.out.println(new Date(firstMsgTime));
				
				if(newTime - firstMsgTime < 36000L) {
					sendMessage(chatId, secondReply);
				}
			}
		}
		
		
		
		
	}
	
	public void sendMessage(long chat_id, String text) {
		WSRequest request = ws.url(url);
		request.setQueryParameter("chat_id", String.valueOf(chat_id));
		request.setQueryParameter("text", text);
		CompletionStage<WSResponse> rs = request.get();
		
	}

}
