package ex002_Interface;

public class DBA extends Funcionario{

	public double getBonus() {
		return this.getSalario() * 0.27;
	}
}
