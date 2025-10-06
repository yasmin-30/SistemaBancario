package sistemaBancario;

public class ContaCorrente extends Conta implements ITributavel{

	public ContaCorrente(String cliente, int id, double saldo) {
		super(cliente, id, saldo);
	}

	@Override
	public boolean sacar(double valor) {
		double verificacao = valor*1.05;
		if(verificacao<=saldo && verificacao>=0) {
			saldo-=verificacao;
			return true;
		} else if (verificacao>=saldo){
			System.out.println("Saldo insuficiente!");
			return false;
		} else {
			System.out.println("O valor passado é negativo, passe uma valor válido!");
			return false;
		}
		
	}

	@Override
	public boolean transferir(double valor) {
		return sacar(valor);	
		
	}

	@Override
	public double calculaTributos() {
		return saldo*0.01;
	}

}
