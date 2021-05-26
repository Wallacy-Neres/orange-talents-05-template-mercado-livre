package br.com.zup.mercadolivre.config.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ValidaSeTemIdCategoriaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaSeTemIdCategoriaValue {

	String message() default "Mensagem de erro";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
	String fieldName() default "id";
	Class<?> domaiClass();
}
