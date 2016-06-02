package ex003_Validation.Validador;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=SalarioValidator.class)
public @interface SalarioValid {
	
	String message() default SalarioValidator.message;
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
