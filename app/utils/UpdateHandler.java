package utils;

import java.util.Date;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import play.libs.ws.*;
import models.Message;
import models.Reply;
import models.Update;
import models.dao.UpdateDAO;

public class UpdateHandler {
	private WSClient ws;
	private UpdateDAO updateDao;
	
	private static final String url = "https://api.telegram.org/bot283733008:AAGYER7EsbD0ESpkJ3tsaBJgvAet6sg8UiI/sendMessage";
	//private static final String url = "https://api.telegram.org/bot283960461:AAFkG67m6NWfHpPQ3vQN1KVKhu1buMh9m6M/sendMessage";
	
	
	private static long chatId = 0L;
	private static long firstMsgTime;
	
	
	private static final String firtReply = "Привет! Я робот, собирающий отзывы о компаниях. Делаю Клиентов и компании ближе друг к другу. "
			+ "\nОтзыв о какой компании вы хотите оставить (название компании)?";
	private static final String secondReply = "В каком городе находится данная компания?";
	
	
	public UpdateHandler(WSClient ws) {
		this.ws = ws;
		this.updateDao = new UpdateDAO();
	}

	public void handle(Update u) {
		String msgTxt = u.getMessage().getText();
		System.out.println(msgTxt);
		
		if(msgTxt.trim().equalsIgnoreCase("/start")) {
			chatId = u.getMessage().getChat().getId();
			firstMsgTime = u.getMessage().getDate();
			
			Reply r = new Reply();
			r.setQuestionCount(1);
			updateDao.saveReply(r);
			
			
			sendMessage(chatId, firtReply);
		} else {
			
			
			
			long id = u.getMessage().getChat().getId();
			long newTime = u.getMessage().getDate();
			
			System.out.println("ID of old chat " + String.valueOf(chatId));
			System.out.println("ID of new chat " + String.valueOf(id));
			
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
