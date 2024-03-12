package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	// Embora a Collection listaContas seja do tipo Conta (Classe Abstrata), ela
	// consegue armazenar Objetos do tipo ContaCorrente e ContaPoupanca, por se
	// tratarem de Classes que Herdam as características da Classe Conta. Esse
	// conceito é chamado de Polimorfismo de Inserção.

	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		// Var também pode armazenar objetos,como é o caso
		var conta = buscarNaCollection(numero);
		if (conta != null) {
			conta.visualizar();
		} else {
			System.out.println("CONTA INEXISTENTE");
		}

	}

	@Override
	public void listarTodas() {
		for (Conta conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A conta número " + conta.getNumero() + " foi criada");

	}

	@Override
	public void atualizar(Conta conta) {
		var buscarConta = buscarNaCollection(conta.getNumero());
		if (buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), conta);
			/**
			 * Se a conta foi encontrada, os dados do Objeto são atualizados na Collection
			 * listaContas através do Método set(). Observe que dentro do Método set, foram
			 * adicionados 2 parâmetros: listaContas.indexOf(buscaConta): Método que
			 * identifica o índice (posição) do Objeto conta encontrado na Collection
			 * listaContas, ou seja, os dados atuais. conta: O Objeto conta, que foi enviado
			 * como parâmetro do Método (dados obtidos via teclado), onde estão os novos
			 * dados da conta, que substituirão os dados atuais.
			 */
		}

		else {
			System.out.println("A CONTA NÃO EXISTE");
		}

	}

	@Override
	public void deletar(int numero) {
		var buscarConta = buscarNaCollection(numero);
		if (buscarConta != null) {
			if (listaContas.remove(buscarConta) == true) {
				System.out.println("CONTA DELETADA");
			}
		} else {
			System.out.println("Conta com esse número inexistente");
		}

	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {

			if (conta.sacar(valor) == true)
				System.out.println("\nO Saque na Conta numero: " + numero + " foi efetuado com sucesso!");

		} else
			System.out.println("\nA Conta numero: " + numero + " não foi encontrada!");

	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\nO Deposito na Conta numero: " + numero + " foi efetuado com sucesso!");

		} else
			System.out.println("\nA Conta numero: " + numero + " não foi encontrada!");

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaDestino = buscarNaCollection(numeroDestino);
		var contaOrigem = buscarNaCollection(numeroOrigem);
		if(contaDestino != null && contaOrigem!=null) {
			
			if(contaOrigem.sacar(valor)) {
				contaDestino.depositar(valor);
				System.out.println("TRANSFERENCIA COM SUCESSO");
			}
			else {
				System.out.println("A CONTA ORIGEM NÃO POSSUI SALDO SUFICIENTE PARA REALIZAR A TRANSAÇÃO");
			}
		}
		else {
			System.out.println("Numero de conta(s) invalido(s)");
		}

	}

	public int gerarNumero() {
		return ++numero;
	}

	public Conta buscarNaCollection(int numero) {
		for (Conta conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}

		}
		return null;
	}

}
