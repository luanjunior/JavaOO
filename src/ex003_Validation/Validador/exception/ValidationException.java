package ex003_Validation.Validador.exception;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -6792343688787102258L;
	
	private List<String> mensagens = newArrayList();
	
	public ValidationException(String message) {
		this(message, Arrays.asList(message));
	}

	public ValidationException(List<String> mensagens) {
		this(null, mensagens);
	}
	
	public ValidationException(String mensagem, List<String> mensagens) {
		super(mensagem);
		this.mensagens = mensagens;
	}
	
	@Override
	public String getMessage() {
		List<String> local = mensagens == null ? Arrays.asList(super.getMessage()) : mensagens;
		return Joiner.on('\n').join(local);
	}
	
	public List<String> getMensagens() {
		return mensagens;
	}
}
