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
	
	public void salvar(Compra compra) throws Exception{
		//cria acesso ao web service com a URL definida 
		WebResource resource = client.resource(URL);
		//Chama o webservice
		ClientResponse response = resource
				//recebendo json
				.type(MediaType.APPLICATION_JSON)
				//requisição do tipo post para a api
				.post(ClientResponse.class,compra);
		//Valido se aconteceu algum problema no servidor		
		if(response.getStatus() != 201) {
			throw new Exception("Erro:" + response.getStatus());
		}
	}
	
	public List<Compra> list() throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse response = resource
					.accept(MediaType.APPLICATION_JSON)
					//requisição tipo Get
					.get(ClientResponse.class);
		if(response.getStatus() != 200) {
			throw new Exception("Erro:" + response.getStatus());
		}
		return Arrays.asList(response.getEntity(Compra[].class));
	}
	
	public void delete(int id) throws Exception {
		WebResource resource = client.resource(URL)
				//enviará para a url o id para deletar a requisicao de compra
				.path(String.valueOf(id));
		
		//Efetua a delecao
		ClientResponse response = resource.delete(ClientResponse.class);
		
		if (response.getStatus() != 204) {
			throw new Exception("Erro:" + response.getStatus());
		}
		
	}
	
	
	
}
