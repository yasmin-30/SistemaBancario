package sistemaBancario;

import java.util.ArrayList;
import java.util.Scanner;


public class Sistema {

	static Conta buscaDeContas(int indice, ArrayList<Conta> contas) {
		for (Conta conta : contas) {
			if (conta.getId() == indice) {	
				return conta;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		ArrayList<Conta> contas = new ArrayList<Conta>();
		int id = 100;
		
		System.out.println("\n========================================");
		System.out.println("        BEM VINDO AO NUBANCO        ");
		System.out.println("========================================");		
		
		boolean executando = false;
		
		while(!executando) {
			Conta conta = null;
			System.out.println("");
			System.out.println("---O QUE DESEJA FAZER?---");
			System.out.println("0 - Criar conta;\n1 - Realizar Depósito;\n2 - Realizar Saque;\n3 - Realizar Transferência;\n4 - Listar contas;\n5 - Calcular total de tributos;\n6 - Sair.");
			int acao = leitor.nextInt();
			leitor.nextLine();
		
			switch(acao) {
			case 0: //CRIAR CONTA
				System.out.println("Escreva o nome do cliente: ");
				String nome = leitor.nextLine();
				
				System.out.println("Insira o saldo da conta: ");
				double saldo = leitor.nextDouble();
				leitor.nextLine();
				
				int tipoConta = 0;
				while(tipoConta>2 || tipoConta < 1) {
					System.out.println("Digite o número correspondente ao tipo de conta desejada:\n1 - Conta Corrente;\n2 - Conta Poupança.");
					tipoConta = leitor.nextInt();

					switch(tipoConta) {
					case 1:
						ContaCorrente corrente = new ContaCorrente(nome, id, saldo);
						contas.add(corrente);
						System.out.println("Conta corrente criada com sucesso! O número da conta é: " + id);
						id+=1;
						break;
						
					case 2:
						ContaPoupanca poupanca = new ContaPoupanca(nome, id, saldo);
						contas.add(poupanca);
						System.out.println("Conta poupança criada com sucesso! O número da conta é: " + id);
						id+=1;
						break;
						
					default:
						System.out.println("O digito informado não é válido, digite o número da ação novamente!");
						break;
					}
				}
				break;
				
			case 1: //DEPOSITO
				System.out.println("Informe o número da conta: ");
				int idDeposito = leitor.nextInt();
				
				System.out.println("Informe o valor a ser depositado: ");
				double valorDeposito = leitor.nextDouble();
				
				//Achar na lista
				conta = buscaDeContas(idDeposito, contas);
				
				if(conta == null) {
					System.out.println("ERRO! O número de conta informado não existe!");
				} else {
					boolean verificacaoDeposito = conta.depositar(valorDeposito);
					if(verificacaoDeposito == true) {
						System.out.println("Valor depositado com sucesso!");
					} else {
						System.out.println("O valor informado é negativo e, por isso, não será depositado!");
					}
				}
	
				break;
				
			case 2: //SAQUE
				System.out.println("Informe o número da conta: ");
				int idSaque = leitor.nextInt();
				
				System.out.println("Informe o valor a ser sacado: ");
				double valorSaque = leitor.nextDouble();
				
				conta = buscaDeContas(idSaque, contas);
				
				if(conta == null) {
					System.out.println("ERRO! O número de conta informado não existe!");
				} else {
					boolean verificacaoSacar = conta.sacar(valorSaque);
					if(verificacaoSacar == true) {
						System.out.println("Valor sacado com sucesso!");
					} else {
						System.out.println("ERRO! O valor não pôde ser sacado!");
					}
				}
				
				break;
				
			case 3: //TRANSFERENCIA
				System.out.println("Informe o número da conta de origem da transferência: ");
				int idOrigem = leitor.nextInt();
				Conta contaOrigem = buscaDeContas(idOrigem, contas);
				
				System.out.println("Informe o número da conta de destino da transferência: ");
				int idDestino = leitor.nextInt();
				Conta contaDestino = buscaDeContas(idDestino, contas);
				
				System.out.println("Informe o valor a ser transferido: ");
				double valorTransferencia = leitor.nextDouble();
				
				//OLHAR ISSOOOOOO
				if(contaOrigem != null && contaDestino != null) {
					contaOrigem.transferir(contaDestino, valorTransferencia);
				} else if(contaOrigem == null && contaDestino != null) {
					System.out.println("ERRO! O número da conta de origem informado não existe!");
				} else if(contaOrigem != null && contaDestino == null) {
					System.out.println("ERRO! O número da conta de destino informado não existe!");
				} else {
					System.out.println("ERRO! Os números das contas de origem e de destino informado não existem!");
				}
					
				break;
				
			case 4: //LISTAR CONTAS
				System.out.println("---LISTAGEM DAS CONTAS---");
				if(contas.size()==0) {
					System.out.println("Você ainda não criou nenhume conta!");
				}else {
			        System.out.println("");
					System.out.println("\n========================================");
					System.out.println("               NUBANCO               ");
					System.out.println("========================================");
					
					for(Conta contaNaLista: contas) {
						contaNaLista.infosConta(conta);
					}
				}
				break;
			
			case 5: //CALCULAR TOTAL TRIBUTOS
				double totalTributos = 0;
				for (Conta contaNaLista : contas) {
					// Verifica se a conta da iteração atual implementa a interface ITributavel
					if (contaNaLista instanceof ITributavel) {
						// Se sim, fazemos um "cast" para poder chamar o método da interface
						ITributavel contaTributavel = (ITributavel) contaNaLista;
						totalTributos += contaTributavel.calculaTributos();
					}
				}
				
				System.out.println("\n========================================");
				System.out.println("Total de tributos a recolher: R$ " + totalTributos);
				System.out.println("========================================");
				break;
			
			case 6: //SAIR
				executando = true;
				System.out.println("\n========================================");
				System.out.println("              VOLTE SEMPRE!              ");
				System.out.println("========================================");
				break;
				
			default:
				System.out.println("\nAção inválida! Digite um número válido de ação.");
				break;
			}
			
		}
		
		
		leitor.close();
	}

}
