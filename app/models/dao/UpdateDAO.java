package models.dao;

import models.Reply;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

public class UpdateDAO {
	
	
	private JPAApi jpaApi;

	public UpdateDAO(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	public void saveReply(Reply r) {
		jpaApi.em().persist(r);
	}

}
