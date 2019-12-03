package br.com.erpapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="T_ERPAPI_COMPRA")
public class Compra {
	
	
	//mappying primary key
	//mapeamento de chave primaria
	@Id
	@Column(name="id_compra")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//mappying sell number
	//mapeando numero da compra
	@NotEmpty(message="NOK:ERP Number cannot be empty")
	@NotNull(message="ERP Number cannot be null")
	@Column(name="numero_erp")
	private String nr;
	
	//mapeando quantidade de itens da compra
	//mappying sell number of items
	@Min(1)
	@NotNull(message="There must be at least 1 item")
	@Column(name="qtd_itens")
	private int qtd;
	
	//mappying sell totel price
	//mapeando valor total da compra
	@Min(1)
	@NotNull(message="Total Value cannot be null")
	@Column(name="valor_item")
	private double valor;

	//mappying sell items
	//mapeamento dos itens relacioanos a esta compra
	@NotEmpty(message="NOK:Item must have a name NOK")
	@NotNull(message="Item must have a name")
	@Column(name="nome_item")
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
