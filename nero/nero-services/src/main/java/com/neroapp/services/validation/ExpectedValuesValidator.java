package com.neroapp.services.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neroapp.services.validation.constraints.Expected;

public class ExpectedValuesValidator implements ConstraintValidator<Expected, Object>{

	private Expected constraint;
	
	@Override
	public void initialize(Expected constraint) {
		this.constraint = constraint;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		
		List<String> expectedValues = Arrays.asList(this.constraint.values());
		if (!expectedValues.contains(value.toString())) {
			return false;
		}
		return true;
	}
}
