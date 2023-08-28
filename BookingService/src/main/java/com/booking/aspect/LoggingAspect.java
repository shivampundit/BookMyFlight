package com.booking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.booking.bookingservice.controller.*.*(..))")
	public void logControllerMethodCall(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Controller method called: " + methodName);
	}

	@Before("execution(* com.booking.bookingservice.service.*.*(..))")
	public void logServiceMethodCall(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Service method called: " + methodName);
	}
}
