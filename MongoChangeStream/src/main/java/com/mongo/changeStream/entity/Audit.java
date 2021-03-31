package com.mongo.changeStream.entity;

import org.bson.BsonDocument;
import org.springframework.data.annotation.Id;

public class Audit {

	@Id
	public String id;

	private String firstName;
	private String lastName;

	private BsonDocument resumeToken;

	public BsonDocument getResumeToken() {
		return resumeToken;
	}

	public void setResumeToken(BsonDocument resumeToken) {
		this.resumeToken = resumeToken;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	
	
	public Audit() {
	}

	public Audit(String firstName, String lastName,BsonDocument resumeToken) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.resumeToken = resumeToken;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

}
