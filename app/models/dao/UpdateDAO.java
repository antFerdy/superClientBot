package models.dao;

import models.Reply;
import play.db.jpa.JPAApi;

public class UpdateDAO {
	
	
	private JPAApi jpaApi;

	public UpdateDAO(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	public void saveReply(Reply r) {
		jpaApi.em().persist(r);
	}
	
	public Reply getReplyByChatId(long chatId) {
		return (Reply) jpaApi.em()
				.createQuery("select d from Reply d where d.chatId = :chatId")
				.setParameter("chatId", chatId)
				.getSingleResult();
	}

}
