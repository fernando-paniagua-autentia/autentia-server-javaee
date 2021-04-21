package com.fernandopaniagua.server.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import com.fernandopaniagua.server.beans.AbstractPersistenceBean;
import com.fernandopaniagua.server.beans.ShareBean;
import com.fernandopaniagua.server.beans.GroupBean;
import com.fernandopaniagua.server.entities.Share;
import com.fernandopaniagua.server.entities.Group;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/shares")
@Named(value = "shareService")
@SessionScoped
public class ShareController implements Serializable {

	@EJB
	private ShareBean gastoBean;
	
	private Share gasto = new Share();
	
	public AbstractPersistenceBean<Share> getGastoBean() {
        return gastoBean;
    }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGasto(Share newGasto) {
		if (gastoBean==null) {
			System.out.println("************ HA FALLADO LA INYECCION DE USUARIO ***********");
			gastoBean = new ShareBean();
		}
		gastoBean.create(newGasto);
		//return Response.ok(listaUsuarios).build();
		return Response.status(Status.CREATED).build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		if (gastoBean==null) {
			gastoBean = new ShareBean();
		}
		List<Share> listaGastos = gastoBean.findAll();
		return Response.ok(listaGastos).build();
	}
	
	@GET
	@Path("/byGroup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGastosCompartios(Group grupo) {
		if (gastoBean==null) {
			gastoBean = new ShareBean();
		}
		List<Share> listaGastos = gastoBean.findAllByIdPropietario(grupo.getIdPropietario());
		return Response.ok(listaGastos).build();
	}

//	/**
//	 * URL: http://localhost:8080/API_REST_WS-RS/api/users/Rosa
//	 * 
//	 * @param name String
//	 * @return Response
//	 */
//	@GET
//	@Path("/{name}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getUserById(@PathParam("name") String name) {
//		Usuario found = null;
//		for (int i = 0; i < listaUsuarios.size(); i++) {
//			if (listaUsuarios.get(i).getNombre().equalsIgnoreCase(name)) {
//				found = listaUsuarios.get(i);
//			}
//		}
//		if (found == null) {
//			return Response.status(Status.BAD_REQUEST).entity("User not found").build();
//		} else {
//			return Response.ok(found).build();
//		}
//	}
//
//	/**
//	 * URL: http://localhost:8080/API_REST_WS-RS/api/users/createUser Parameters in
//	 * Postman: {"name":"Rosa3333","username":"Marfi3333l"}
//	 * 
//	 * @param User
//	 * @return Response list NOTA: Si no existe el constructor vacío de User, da un
//	 *         error y el userRequest viene null.
//	 */
//	@POST
//	@Path("/createUser")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response createUser(Usuario userRequest) {
//
//		this.listaUsuarios.add(userRequest);
//		// return Response.status(Status.CREATED).build();
//		return Response.ok(listaUsuarios).build();
//
//	}
//
//	/**
//	 * URL: http://localhost:8080/API_REST_WS-RS/api/users/updateUser Parameters in
//	 * Postman: {"name":"Rosa","username":"Marfil3333"}
//	 * 
//	 * @param User
//	 * @return user modified NOTA: Si no existe el constructor vacío de User, da un
//	 *         error y el userRequest viene null.
//	 */
//	@PUT
//	@Path("/updateUser")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateUser(Usuario userUpdate) {
//		Usuario found = null;
//		for (int i = 0; i < listaUsuarios.size(); i++) {
//			if (listaUsuarios.get(i).getNombre().equalsIgnoreCase(userUpdate.getNombre())) {
//				found = listaUsuarios.get(i);
//			}
//		}
//
//		if (found == null) {
//			return Response.status(Status.BAD_REQUEST).entity("User not found").build();
//		} else {
//			found.setNombre(userUpdate.getNombre());
//			return Response.ok(found).build();
//		}
//	}
//
//	/**
//	 * URL: http://localhost:8080/API_REST_WS-RS/api/users/deleteUser/Rosa
//	 * 
//	 * @param User
//	 * @return Response
//	 */
//	@DELETE
//	@Path("/deleteUser/{name}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response deleteUser(@PathParam("name") String name) {
//		Usuario found = null;
//		for (int i = 0; i < listaUsuarios.size(); i++) {
//			if (listaUsuarios.get(i).getNombre().equalsIgnoreCase(name)) {
//				found = listaUsuarios.get(i);
//				listaUsuarios.remove(found);
//			}
//		}
//
//		
//	}

}