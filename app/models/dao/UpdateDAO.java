package models.dao;

import javax.inject.Inject;

import play.Logger;
import play.db.jpa.JPAApi;

public class UpdateDAO {
	
	@Inject
	JPAApi jpaApi;
	
	public void saveReply() {
		Logger.info("Heyy");
	}

}
