package models;

import javax.persistence.*;

@Entity
public class Reply {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String city;
	
	@Column
	private String street;
	
	@Column
	private String firstReply;
	
	@Column
	private int rating;
	
	@Column
	private int questionCount;

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
	
	
	
	
//	@Column
//	@ManyToOne
//	public Client client;

}
