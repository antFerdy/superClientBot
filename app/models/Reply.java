package models;

import javax.persistence.*;

@Entity
public class Reply {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private long chatId;
	
	@Column(name="COUNT_OF_QUEST")
	private int questionCount;

	@Column(name = "LAST_MSG_TIME")
	private long lastMsgTime;
	
	@Column(length=4000)
	private String company;
	
	@Column
	private String city;
	
	@Column
	private String street;
	
	
	@Column(name = "FIRST_REPLY", length = 4000)
	private String firstReply;
	
	@Column
	private int rating;
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getFirstReply() {
		return firstReply;
	}

	public void setFirstReply(String firstReply) {
		this.firstReply = firstReply;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public void setMsgTime(long msgTime) {
		this.lastMsgTime = msgTime;
		
	}
	
	public long getMsgTime() {
		return this.lastMsgTime;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	

}
