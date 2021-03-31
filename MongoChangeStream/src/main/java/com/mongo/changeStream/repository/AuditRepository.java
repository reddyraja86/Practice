package com.mongo.changeStream.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.changeStream.entity.Audit;

public interface AuditRepository extends MongoRepository<Audit, String> {

	public Audit findByFirstName(String firstName);
	public List<Audit> findByLastName(String lastName);

}
