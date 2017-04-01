package utils;

import java.util.concurrent.CompletionStage;

import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Client;
import models.Message;
import models.Reply;
import models.Update;
import models.User;
import models.dao.UpdateDAO;
import models.dao.UserDAO;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

public class UpdateHandler {
	private WSClient ws;
	private UpdateDAO updateDao;
	private UserDAO userDao;
	
	//SuperClientBot
	private static final String urlForNotification = "https://api.telegram.org/bot283733008:AAGYER7EsbD0ESpkJ3tsaBJgvAet6sg8UiI/sendMessage";
	//chat id of admins
	private static final long adminChatId = 308064562;
	private static final long salimChatId = 98166212;
	
	//KnigaOtzyvovBot
	private static final String url = "https://api.telegram.org/bot283960461:AAFkG67m6NWfHpPQ3vQN1KVKhu1buMh9m6M/sendMessage";
	
	private static final String[] questions = new String[5];
	
	public UpdateHandler(WSClient ws, JPAApi jpaApi) {
		this.ws = ws;
		this.updateDao = new UpdateDAO(jpaApi);
		this.userDao = new UserDAO(jpaApi);

		questions[0] = "Отзыв о какой компании вы хотите оставить (название компании)?";
		questions[1] = "Пожалуйста, отправьте ваше местоположение или напишите адрес заведения (город, улицу)";
		questions[2] = "Напишите, пожалуйста, ваш отзыв о данной компании 👍 👎";
		questions[3] = "Спасибо за отзыв! Насколько вероятно, что Вы порекомендуете данную компанию другу или коллеге? (От 0 до 10 варианты оценок) 😉";
		questions[4] = "Благодарю за отзыв и оценку!👌👍";
	}
	
	
	public void handleUpdate(Update update) {
		long chatId = update.getMessage().getChat().getId();
		long msgTime = update.getMessage().getDate();
		Reply reply = updateDao.getLastUnfinishedReply(chatId);
		
		//если пользователь впервые запускает бот, то поясняем кто мы и сохраняем клиента в базу +отправляем уведомление
		if(!userDao.isUserExist(chatId)) {
			String firstQuestion = "Привет! Я робот, собирающий отзывы о компаниях. Делаю Клиентов и компании ближе друг к другу.😀"
					+ "\nОтзыв о какой компании вы хотите оставить (название компании)?";
			saveUser(update.getMessage());
			sendNotifAboutNewUser(adminChatId, "New user was created: " + update.getMessage().getFrom().getFirst_name());
			sendNotifAboutNewUser(salimChatId, "New user was created: " + update.getMessage().getFrom().getFirst_name());
			
			initReply(chatId, msgTime);
			sendMessage(chatId, firstQuestion);
		} else if(reply == null) { //если до этого вопросов не задавалось
			initReply(chatId, msgTime);
			sendMessage(chatId, questions[0]);
		} else if(msgTime - reply.getMsgTime() > 3600L) { //если прошло более часа
			reply.setFinished(true);
			updateDao.saveReply(reply);
			
			initReply(chatId, msgTime);
			sendMessage(chatId, questions[0]);
		} else if(update.getMessage().getText() == "/start") { //если хочет заново запустить отзыв
			reply.setFinished(true);
			updateDao.saveReply(reply);
			
			initReply(chatId, msgTime);
			sendMessage(chatId, questions[0]);
		} else {
			handleCheckedUpdate(update, reply);
		}
	}

	
	
	
	public void handleCheckedUpdate(Update u, Reply reply) {
		long chatId = u.getMessage().getChat().getId();
		long msgTime = u.getMessage().getDate();
		String msgTxt = u.getMessage().getText();
		int counter = reply.getQuestionCount();
			
		//если был отправлен стикер или другой формат данных или пустое сообщение
		if((msgTxt == null || msgTxt.trim().isEmpty()) && reply.getQuestionCount() != 1) {
			resendMsg(chatId, reply, msgTime, questions[counter]);
		} else {
			//set time and counter
			reply.setMsgTime(msgTime);
			reply.setQuestionCount(counter + 1);
			
			//set data to entity
			if(counter == 0) {
				reply.setCompany(msgTxt);
				ObjectNode kBoard = getKeyboards();
				
				
				//save entity
				updateDao.saveReply(reply);
				postMessage(chatId, questions[1], kBoard);
				return;
			} else if(counter == 1) {
				reply.setCity(msgTxt);
			} else if(counter == 2) {
				reply.setFirstReply(msgTxt);
				
				processMedia();
			} else if(counter == 3) {
				Integer rating = null;
				try {
					rating = Integer.valueOf(msgTxt);
					if (rating > 10 || rating < 0) {
						resendMsg(chatId, reply, msgTime, "Формат некорректен. Пожалуйста, поставьте оценку от 0 до 10. ");
						return;
					}
					
				} catch (NumberFormatException e) {
					resendMsg(chatId, reply, msgTime, "Формат некорректен. Пожалуйста, поставьте оценку от 0 до 10.");
					return;
				}
				reply.setRating(rating);
				
				
			}
			//save entity
			updateDao.saveReply(reply);
			//send responce
//			sendMessage(chatId, questions[counter + 1]);
			postMessage(chatId, questions[counter + 1], null);
		}
	}

	private void initReply(long chatId, long firstMsgTime) {
		Reply r = new Reply();
		r.setChatId(chatId);
		r.setQuestionCount(0); //first asked question
		r.setMsgTime(firstMsgTime);
		r.setFinished(false);
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
	
	

	private void saveUser(Message message) {
		User user = message.getFrom();
		long chatId = message.getChat().getId();
		
		Client client = new Client();
		client.setFirstName(user.getFirst_name());
		client.setLastName(user.getLast_name());
		client.setUsername(user.getUsername());
		client.setChatId(chatId);
		userDao.save(client);
	}
	
	private void sendNotifAboutNewUser(long chat_id, String text) {
		WSRequest request = ws.url(urlForNotification);
		request.setQueryParameter("chat_id", String.valueOf(chat_id));
		request.setQueryParameter("text", text);
		CompletionStage<WSResponse> rs = request.get();
		
	}
	
	private void processMedia() {
		
	}
	
	private void postMessage(long chat_id, String text, ObjectNode reply_markup) {
		WSRequest request = ws.url(url);
		ObjectNode postObj = Json.newObject();
		postObj.put("chat_id", String.valueOf(chat_id));
		postObj.put("text", text);
		if(reply_markup != null)
			postObj.put("reply_markup", reply_markup);
		
		CompletionStage<WSResponse> rs = request.post(postObj);
	}

	private ObjectNode getKeyboards() {
		ObjectNode btn = Json.newObject().put("request_location", true).put("text", "Отправить текущее местоположение");
		ObjectNode reply_markup = Json.newObject();
		reply_markup.put("keyboard", Json.newArray().add(Json.newArray().add(btn)));
		return reply_markup;
	}

}
