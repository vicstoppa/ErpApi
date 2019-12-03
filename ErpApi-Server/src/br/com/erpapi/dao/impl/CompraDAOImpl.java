package br.com.erpapi.dao.impl;

import javax.persistence.EntityManager;

import br.com.erpapi.dao.CompraDAO;
import br.com.erpapi.entity.Compra;

public class CompraDAOImpl extends GenericDAOImpl<Compra, Integer> 
implements CompraDAO{

	public CompraDAOImpl(EntityManager em) {
		super(em);

	}


}
