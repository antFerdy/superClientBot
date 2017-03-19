package models;

import javax.persistence.*;

@Entity
public class Client {
	@Id
	@GeneratedValue
	public Long id;
	
	@Column
	String firstName;
	
	

}
