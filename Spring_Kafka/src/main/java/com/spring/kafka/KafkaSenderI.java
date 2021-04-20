package com.spring.kafka;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;



public interface KafkaSenderI {

	@Retryable(value = { Throwable.class }, maxAttempts = 2, backoff = @Backoff(delay = 10))
	public void send(String message) throws Throwable;

	@Recover
	public String failedRetry(Throwable e);
}
