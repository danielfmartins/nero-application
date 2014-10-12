package com.neroapp.services.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.neroapp.services.validation.ExpectedValuesValidator;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExpectedValuesValidator.class)
public @interface Expected {

	String message();
	
	String[] values();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
