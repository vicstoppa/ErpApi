package br.com.erpapi.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.erpapi.to.Compra;

public class CompraService {

	private final static String URL = "http://localhost:8080/ErpApi-Server/erpapi/compra";
	
	private Client client = Client.create();
	

	public void cadastrar(Compra compra) throws Exception {
		//solicita a URL
		WebResource resource = client.resource(URL);
		//recupera o objeto vindo do service com a compra vinda da página e envia via post para o servidor.
		ClientResponse resp = resource.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, compra);
		
		if (resp.getStatus() != 201) throw new Exception("Erro:" + response.getStatus());
		
	}
	
	public List<Compra> list() throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse response = resource
					.accept(MediaType.APPLICATION_JSON)
					//requisicao tipo Get
					.get(ClientResponse.class);
		if(response.getStatus() != 200) {
			throw new Exception("Erro:" + response.getStatus());
		}
		return Arrays.asList(response.getEntity(Compra[].class));
	}
	
	public void delete(int id) throws Exception {
		WebResource resource = client.resource(URL)
				//enviar para a url o id para deletar a requisicao de compra
				.path(String.valueOf(id));
		
		//Efetua a delecao
		ClientResponse response = resource.delete(ClientResponse.class);
		
		if (response.getStatus() != 204) {
			throw new Exception("Erro:" + response.getStatus());
		}
		
	}
}
