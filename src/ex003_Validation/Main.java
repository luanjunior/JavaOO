package ex003_Validation;

import org.apache.log4j.Logger;

import ex003_Validation.Validador.ValidatorHelper;
import ex003_Validation.Validador.exception.MyException;
import ex003_Validation.Validador.exception.ValidationException;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		Funcionario f = new Funcionario();
		
		f.setNome("Funcionario Teste");
		f.setSalario(6000.00);
		
		try {
			ValidatorHelper.validar(f);
		} catch (ValidationException e) {
			log.error(e.getMessage());
		} catch (MyException e) {
			log.error(e.getMessage());
		}	
	}

}
