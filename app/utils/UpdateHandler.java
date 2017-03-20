package utils;

import java.util.concurrent.CompletionStage;

import models.Reply;
import models.Update;
import models.dao.UpdateDAO;
import play.db.jpa.JPAApi;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

public class UpdateHandler {
	private WSClient ws;
	private UpdateDAO updateDao;
	
	private static final String url = "https://api.telegram.org/bot283733008:AAGYER7EsbD0ESpkJ3tsaBJgvAet6sg8UiI/sendMessage";
	//private static final String url = "https://api.telegram.org/bot283960461:AAFkG67m6NWfHpPQ3vQN1KVKhu1buMh9m6M/sendMessage";
	
	private String[] questions = new String[5];
	
	
	
	
	public UpdateHandler(WSClient ws, JPAApi jpaApi) {
		this.ws = ws;
		this.updateDao = new UpdateDAO(jpaApi);

		questions[0] = "Привет! Я робот, собирающий отзывы о компаниях. Делаю Клиентов и компании ближе друг к другу. "
				+ "\nОтзыв о какой компании вы хотите оставить (название компании)?";
		questions[1] = "В каком городе и на какой улице находится данная компания?";
		questions[2] = "Напишите, пожалуйста, ваш отзыв о данной компании";
		questions[3] = "Спасибо за отзыв! Насколько вероятно, что Вы порекомендуете данную компанию другу или коллеге? (От 0 до 10 варианты оценок)";
		questions[4] = "Благодарю за отзыв и оценку!";
	}

	public void handle(Update u) {
		String msgTxt = u.getMessage().getText();
		long chatId = u.getMessage().getChat().getId();
				
		if(msgTxt.trim().equalsIgnoreCase("/start")) {
			long firstMsgTime = u.getMessage().getDate();
			
			Reply r = new Reply();
			r.setChatId(chatId);
			r.setQuestionCount(0); //first asked question
			r.setMsgTime(firstMsgTime);
			updateDao.saveReply(r);
			
			sendMessage(chatId, questions[0]);
		} else {
			
			//get reply by chat id
			Reply reply = updateDao.getReplyByChatId(chatId);
			if(reply == null) {
				System.err.println("reply is not found");
				return;
			}
			
			//set data to entity
			int counter = reply.getQuestionCount();
			if(counter == 0) {
				reply.setCompany(msgTxt);
			} else if(counter == 1) {
				reply.setCity(msgTxt);
			} else if(counter == 2) {
				reply.setFirstReply(msgTxt);
			} else if(counter == 3) {
				Integer rating = null;
				try {
					rating = Integer.valueOf(msgTxt);
				} catch (NumberFormatException e) {
					System.err.println("Format of rating is incorrect. Only numbers required");
				}
				reply.setRating(rating);
			}
			
			//send responce
			long newTime = u.getMessage().getDate();
			if(newTime - reply.getMsgTime() < 36000L) {
				sendMessage(chatId, questions[counter + 1]);
			}
			
			//save entity
			reply.setQuestionCount(counter + 1);
			updateDao.saveReply(reply);
		}
		
		
		
		
	}
	
	public void sendMessage(long chat_id, String text) {
		WSRequest request = ws.url(url);
		request.setQueryParameter("chat_id", String.valueOf(chat_id));
		request.setQueryParameter("text", text);
		CompletionStage<WSResponse> rs = request.get();
		
	}

}
