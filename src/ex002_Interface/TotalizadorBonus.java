package ex002_Interface;

public class TotalizadorBonus extends Funcionario{
	private double total = 0;
	
	public void adiciona(Funcionario f) {
		total += f.getBonus();
	}
	
	public double getTotal() {
		return total;
	}
}
