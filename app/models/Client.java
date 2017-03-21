package models;

import javax.persistence.*;

@Entity
public class Client {
	@Id
	@GeneratedValue
	public Long id;
	
	@Column
	public String firstName;
	
	@Column
	public String lastName;
	
	@Column
	public String username;
	
	@Column
	public long chatId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
	
	

}
