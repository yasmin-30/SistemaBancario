package sistemaBancario;

public abstract class Conta {
	private int numeroId;
	private String cliente;
	public double saldo;
	protected String tipo;

	public Conta(String cliente, int id, double saldo) {
		this.cliente = cliente;
		this.numeroId = id;
		this.saldo = saldo;
	}
	
	//Métodos getters
	public String getCliente() {
		return this.cliente;
	}
	
	public int getId() {
		return this.numeroId;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	//listagem de contas
	public void infosConta(Conta conta) {
		
		System.out.println("");
		System.out.println("Cliente: " + this.cliente);
		System.out.println("N° da conta: " + this.numeroId);
		System.out.println("Saldo: " + this.saldo);
		System.out.println("Tipo de conta: " + this.tipo);		
		System.out.println("----------------------------------------");
	}
	
	//Manuseio do dinheiro da conta
	public boolean depositar(double valor) {
		if(valor>=0) {
			saldo+=valor;
			return true;
		} else {
			return false;
		}
	}
	
	//SAQUE E TRANSFERENCIA ABSTRATOS
	public abstract boolean sacar(double valor);
	public abstract void transferir(Conta destino, double valor);
}
