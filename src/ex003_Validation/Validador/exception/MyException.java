package ex003_Validation.Validador.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9122826864987095654L;

	public MyException() {
		super("inválido");
	}
	
	public MyException(String mensagem) {
		super(mensagem);
	}
	
	
}
