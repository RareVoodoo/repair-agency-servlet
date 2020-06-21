package ua.testing.repairagency.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

public class FormValidator <T> {
    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private Validator validator = validatorFactory.getValidator();
    private Set<String> errors = new HashSet<>();


    public boolean isValidFields(T object){
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        if(!constraintViolations.isEmpty()){
            for (ConstraintViolation<T> constraintViolation:constraintViolations){
                errors.add(constraintViolation.getMessage());
            }
            return false;
        }
        return true;
    }


    public Set<String> getErrors() {
        return errors;
    }
}
