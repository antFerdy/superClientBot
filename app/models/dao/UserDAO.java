package models.dao;

import models.Client;
import play.db.jpa.JPAApi;

public class UserDAO {
	
	private JPAApi jpaApi;
	
	public UserDAO(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	
	public boolean isUserExist(long chatId) {
		long count = (long) jpaApi.em()
			.createQuery("select count(d) from Client d where d.chatId = :chatId")
			.setParameter("chatId", chatId)
			.getSingleResult();
		
		if(count > 0)
			return true;
		return false;
	}


	public void save(Client client) {
		jpaApi.em().persist(client);
	}

}
