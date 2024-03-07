package conta.model;

public class ContaCorrente extends Conta {

	private float limite;
	

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = limite;
	}
	
	//Se eu tiver mais de um construtor na classe Conta,eu posso chamar ele também

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	public boolean sacar(float valor) {
		if(this.getSaldo() + this.getLimite() > valor) {
			this.setSaldo(this.getSaldo() - valor);
			return true;
		}
		System.out.println("SALDO E LIMITES INSUFICIENTES");
		return false;
	}
	//Sobrepondo o metodo que existe na classe Conta,adicionando o atributo de limite pra ser mostrado
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite : " + this.limite);
	}

}
