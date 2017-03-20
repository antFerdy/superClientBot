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
		long chatId = u.getMessage().getChat().getId();
		long msgTime = u.getMessage().getDate();
		
		//Получаем сущность отзыва по чат айди
		Reply reply = updateDao.getReplyByChatId(chatId);
		
		//Получаем текст сообщения
		String msgTxt = u.getMessage().getText();
		
		if(reply != null) {
			System.out.println("REPLY IS NOT NULL");
		} else {
			System.out.println("REPLY IS NULL");
		}
		
		System.out.println(msgTxt);
		
		//если вопросы ранее не задавались, или юзер хочет заново начать, то запускаем первый вопрос
		if((reply == null && msgTxt == null) || msgTxt.trim().equalsIgnoreCase("/start")) {
			initReply(chatId, msgTime);
			
		//если был отправлен стикер или другой формат данных или пустое сообщение
		} else if(msgTxt == null || msgTxt.trim().isEmpty()) {
			resendMsg(chatId, reply, msgTime, "Формат ответа некорректен. Пожалуйста повторите ответ");
		
		//если прошло более часа, то делаем старый отзыв завершенным и инициализируем новый
		} else if(msgTime - reply.getMsgTime() > 36000L) {
			reply.setQuestionCount(4);
			updateDao.saveReply(reply);
			
			initReply(chatId, msgTime);
		} else {
			int counter = reply.getQuestionCount();
			
			//set data to entity
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
					if (rating > 10 || rating < 0) {
						resendMsg(chatId, reply, msgTime, "Формат некорректен. Пожалуйста, поставьте рейтинг от 0 до 10. ");
					}
					
				} catch (NumberFormatException e) {
					resendMsg(chatId, reply, msgTime, "Формат некорректен. Пожалуйста, поставьте рейтинг от 0 до 10.");
				}
				reply.setRating(rating);
			}
			
			//set time and counter
			reply.setMsgTime(msgTime);
			reply.setQuestionCount(counter + 1);
			
			//save entity
			updateDao.saveReply(reply);
			
			
			//send responce
			sendMessage(chatId, questions[counter + 1]);
		}
	}
	
	private void initReply(long chatId, long firstMsgTime) {
		Reply r = new Reply();
		r.setChatId(chatId);
		r.setQuestionCount(0); //first asked question
		r.setMsgTime(firstMsgTime);
		updateDao.saveReply(r);
		
		sendMessage(chatId, questions[0]);
	}

	public void sendMessage(long chat_id, String text) {
		WSRequest request = ws.url(url);
		request.setQueryParameter("chat_id", String.valueOf(chat_id));
		request.setQueryParameter("text", text);
		CompletionStage<WSResponse> rs = request.get();
		
	}
	
	public void resendMsg(long chatId, Reply reply, long msgTime, String msgTxt) {
		sendMessage(chatId, msgTxt);
		reply.setMsgTime(msgTime);
		updateDao.saveReply(reply);
	}

}
