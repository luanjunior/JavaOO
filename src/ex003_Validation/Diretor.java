package ex003_Validation;

public class Diretor extends Funcionario implements Autenticavel{

	public double getBonus() {
		return this.getSalario() * 0.50;
	}
	
	public void	cobraRelatorios() {
		System.out.println("Preciso dos relatorios...");
	}

	@Override
	public boolean autentica(int senha) {
		return false;
	}
}
