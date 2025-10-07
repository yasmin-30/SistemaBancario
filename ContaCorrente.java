package sistemaBancario;

public class ContaCorrente extends Conta implements ITributavel{

	public ContaCorrente(String cliente, int id, double saldo) {
		super(cliente, id, saldo);
		this.tipo = "Corrente";
	}

	@Override
	public boolean sacar(double valor) {
		double verificacao = valor*1.05;
		if(verificacao<=saldo && verificacao>=0) {
			saldo-=verificacao;
			return true;
		} else {
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

	@Override
	public double calculaTributos() {
		return saldo*0.01;
	}

}
