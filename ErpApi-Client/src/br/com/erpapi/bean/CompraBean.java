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
		setCompra(new Compra());
		service = new CompraService();
	}
	
	
	public String cadastrar() {
		try {
			service.cadastrar(compra);
			addMensagem("Cadastrado!");
			return "compra?faces-redirect=true"; 
		} catch (Exception e) {
			e.printStackTrace();
			addMensagem("Erro");
			return "compra";
		}
	}
	
	public List<Compra> getCompras(){
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

	private void addMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext()
								.getFlash().setKeepMessages(true);
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
