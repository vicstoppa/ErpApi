package br.com.erpapi.dao;

import java.util.List;

import br.com.erpapi.exception.CommitException;
import br.com.erpapi.exception.RegistroNaoEncontradoException;

/*Criei um DAO Genérico para implementar o CRUD para todas as classes,
sendo necessário apenas criar metodos de pesquisa caso precise por classe*/
public interface GenericDAO <Tabela, Chave> {

	void cadastrar(Tabela tabela);
	
	void atualizar(Tabela tabela);
	
	void excluir(Chave codigo) throws RegistroNaoEncontradoException;
	
	Tabela buscar(Chave codigo);
	
	void commit() throws CommitException;
	
	List<Tabela> listar();
	
}


