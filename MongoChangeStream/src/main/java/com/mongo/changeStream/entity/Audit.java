package com.mongo.changeStream.entity;

import org.springframework.data.annotation.Id;


public class Audit {

	@Id
	public String id;

	public String firstName;
	public String lastName;

	public Audit() {}

	public Audit(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%s, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}

}

