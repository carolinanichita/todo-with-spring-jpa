package cn.todo.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TitleValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TitleConstraint {

    String message() default "Invalid title";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
