package models.dao;

import javax.inject.Inject;

import models.Reply;
import play.db.jpa.JPAApi;

public class UpdateDAO {
	
	@Inject
	JPAApi jpaApi;

	public void saveReply(Reply r) {
		// TODO Auto-generated method stub
		jpaApi.em().persist(r);
	}

}
