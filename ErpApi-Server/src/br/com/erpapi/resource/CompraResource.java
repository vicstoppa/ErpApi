package br.com.erpapi.resource;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.erpapi.dao.CompraDAO;
import br.com.erpapi.dao.impl.CompraDAOImpl;
import br.com.erpapi.entity.Compra;
import br.com.erpapi.exception.CommitException;
import br.com.erpapi.singleton.EntityManagerFactorySingleton;

@Path("/compra")
public class CompraResource {

		
	private CompraDAO dao;
	
	public CompraResource(){

		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		dao = new CompraDAOImpl(em);
	}
	
	
	//Metodo Post pare receber JSON do ERP
	@POST
	@Consumes(MediaType.APPLICATION_JSON) //Recebe o JSON
	public Response cadastrar(Compra compra, @Context UriInfo uri) {
		try {
			//testa se a compra veio com algum campo faltando.
				dao.cadastrar(compra);
				dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			//retorna status 500 no console (internal server error )porém na pagina entra como no content
			//A Classe Ja está validando os campos
			System.out.println(Response.serverError().build());
			return Response.noContent().build();
		}
		//recupera url atual
		UriBuilder builder = uri.getAbsolutePathBuilder();
		//adiciona codico na selecao da url
		builder.path(String.valueOf(compra.getId()));
		//retorna status 201 com a url para acessar selecao para caso queira acessar apenas este item
		return Response.created(builder.build()).build();
	}
	
	
	
	//Metodo de recuperacao das compras já recebidas no sistema interno
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compra> listar(){
		return dao.listar();
	}
	
	
	@Path("{id}")
	@DELETE
	public void remover(@PathParam("id") int id) {
		try {
			String fileName;
			Compra compra = new Compra(); 
			compra = dao.buscar(id);
			Date now = new Date();
			SimpleDateFormat formatDate = new SimpleDateFormat("MM-dd-yyyy-mm-ss");
			fileName = formatDate.format(now);
			FileOutputStream file = new FileOutputStream(new File("C:/DeletedLog/compra"+fileName+".xml"));
			XMLEncoder encoder = new XMLEncoder(file);
			encoder.writeObject(compra);
			encoder.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
				dao.excluir(id);
				dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
				
	}
		
}
