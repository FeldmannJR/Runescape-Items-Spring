package dev.feldmann.runescapeitems.validation.annotations;

import dev.feldmann.runescapeitems.validation.validators.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageValidator.class)
public @interface ValidImage {

    String message() default "Invalid image";

    double ratio() default 0;

    String[] mimeTypes() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
