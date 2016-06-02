package ex002_Interface;

public class Desenvolvedor extends Funcionario{

	public double getBonus() {
		return this.getSalario() * 0.25;
	}
}
