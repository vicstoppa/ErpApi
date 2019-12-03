package br.com.erpapi.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.erpapi.service.CompraService;
import br.com.erpapi.to.Compra;

@ManagedBean
public class CompraBean {
	
	private Compra compra;
	
	private CompraService service;
	
	@PostConstruct
	private void init() {
		this.compra = new Compra();
		this.service = new CompraService();
	}
	
	public void salvar(){
		FacesMessage msg;
		try {
			service.salvar(compra);
			msg = new FacesMessage("Cadastrado");
		}catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public List<Compra> getCompras() {
		try {
			return service.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(int id) {
		FacesMessage msg;
		try {
			service.delete(id);
			msg = new FacesMessage("Removido Com Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro:");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
