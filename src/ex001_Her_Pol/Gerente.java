package ex001_Her_Pol;

public class Gerente extends Funcionario{

	public double getBonus() {
		return this.getSalario() * 0.3;
	}
	
	public void cobraEntrada(){
		System.err.println("Está pronto?");
	}
}
