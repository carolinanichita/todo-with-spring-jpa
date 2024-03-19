package cn.todo.utils.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<TitleConstraint, String> {

    @Override
    public void initialize(TitleConstraint name) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        return nameField != null && nameField.indexOf(" ") > 1;
    }
}
