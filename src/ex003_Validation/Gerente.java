package ex003_Validation;

public class Gerente extends Funcionario implements Autenticavel{

	public double getBonus() {
		return this.getSalario() * 0.3;
	}
	
	public void cobraEntrada(){
		System.err.println("Está pronto?");
	}

	@Override
	public boolean autentica(int senha) {
		return false;
	}
}
