package ex003_Validation.Validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ex003_Validation.Funcionario;

public class SalarioValidator implements ConstraintValidator<SalarioValid, Object> {
	
	public static final String message = "O salário não deve ser maior que R$ 5000,00";
	
	@Override
	public void initialize(SalarioValid arg0) {}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value != null && value instanceof Funcionario) {
			Funcionario funcionario = (Funcionario) value;
			return funcionario != null && funcionario.getSalario() <= 5000;
		}
		return true;
	}

}
