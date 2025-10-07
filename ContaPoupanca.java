package sistemaBancario;

public class ContaPoupanca  extends Conta{
	
	public ContaPoupanca(String cliente, int id, double saldo) {
		super(cliente, id, saldo);
		this.tipo = "Poupança";
	}

	@Override
	public boolean sacar(double valor) {
		if(valor<=saldo && valor>=0) {
			saldo-=valor;
			return true;
		} else {
			System.out.println("O valor passado é negativo e, por isso, não será sacado!");
			return false;
		}
	}

	@Override
	public void transferir(Conta destino, double valor) {
		if(sacar(valor) == true) {
			destino.depositar(valor);
		} else {
			System.out.println("A transferência não foi concluída, pois o valor passado não é válido!");
		}
		
	}
}
