package models.dao;

import java.util.List;

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
		List<Reply> list = jpaApi.em()
				.createQuery("select d from Reply d where d.chatId = :chatId and d.questionCount <> 4 order by d.id desc")
				.setParameter("chatId", chatId)
				.setMaxResults(1)
				.getResultList();
		if(list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public void remove(Reply reply) {
		jpaApi.em().remove(reply);
	}

}
