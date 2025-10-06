package sistemaBancario;

public class ContaPoupanca  extends Conta{
	
	public ContaPoupanca(String cliente, int id, double saldo) {
		super(cliente, id, saldo);
	}

	@Override
	public boolean sacar(double valor) {
		if(valor<=saldo && valor>=0) {
			saldo-=valor;
			return true;
		} else if (valor>=saldo){
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
}
