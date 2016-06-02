package ex003_Validation.Validador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.validator.messageinterpolation.ValueFormatterMessageInterpolator;

import ex003_Validation.Validador.decorator.PropertyMessageInterpolatorDecorator;
import ex003_Validation.Validador.exception.MyException;
import ex003_Validation.Validador.exception.ValidationException;


public abstract class ValidatorHelper {

	public static void validar(Object obj) throws ValidationException, MyException {
		// Valida o cara
		String[] validationList = buildValidationErrorList(obj);
		// Joga uma exceção no ar caso houveram erros
		if(validationList.length > 0)
			throw new ValidationException(Arrays.asList(validationList));
	}
	
	public static String[] buildValidationErrorList(Object obj) {
		return buildValidationErrorList(obj, (String[]) null);
	}
	
	public static String[] buildValidationErrorList(Object obj, Class<?> group) {
		return buildValidationErrorList(obj, group, (String[])null);
	}
	
	public static String[] buildValidationErrorList(Object obj, String... propertiesToValidate) {
		return buildValidationErrorList(obj, null, propertiesToValidate);
	}
	
	/**
	 * Valida o objeto.
	 * @param obj
	 * @param group 
	 * @return um array com a lista de erros encontrados.
	 */
	public static String[] buildValidationErrorList(Object obj, Class<?> group, String... propertiesToValidate) {
		// Adiciona o message interpolator.
		PropertyMessageInterpolatorDecorator messageInterpolator = new PropertyMessageInterpolatorDecorator(new ValueFormatterMessageInterpolator());
		
		ValidatorFactory factory = Validation.byDefaultProvider().configure().messageInterpolator(messageInterpolator).buildValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = group == null ? validator.validate(obj) : validator.validate(obj, group);
		
		List<String> mensagens = new ArrayList<String>();
		
		for (ConstraintViolation<Object> violation : violations) {
			// Se nenhuma property foi informada ou se a property está no array de propriedades
			if((propertiesToValidate == null || propertiesToValidate.length == 0) 
					|| propertiesToValidate != null 
					&& propertiesToValidate.length > 0 
					&& ArrayUtils.contains(propertiesToValidate, violation.getPropertyPath().toString())) {
				mensagens.add(violation.getMessage());
			}
		}
		
		return mensagens.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
	}
}
