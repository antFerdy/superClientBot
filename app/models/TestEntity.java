package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestEntity {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Column
	public String text;
	
}