package br.com.erpapi.to;

public class Compra {
	
	private int id;
	
	private String nr;
		
	private int qtd;
		
	private double valor;
	
	private String nome;
	
	//getter setters and constructor 
	//abaixo, getters, setters e construtores
	public Compra() {
		super();
	}
	
	
	public Compra(int id,String nr, int qtd, double valor, String nome) {
		super();
		this.id = id;
		this.nr = nr;
		this.qtd = qtd;
		this.valor = valor;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
