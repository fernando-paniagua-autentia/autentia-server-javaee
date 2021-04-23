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
import javax.ws.rs.QueryParam;
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSharedByGroup(
			@QueryParam("groupOwnerId") int groupOwnerId) {
		if (gastoBean==null) {
			gastoBean = new ShareBean();
		}
		List<Share> listaGastos = gastoBean.findAllByOwnerId(groupOwnerId);
		return Response.ok(listaGastos).build();
	}

//	@GET
//	@Path("/byGroup")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getSharedByGroup(Group grupo) {
//		if (gastoBean==null) {
//			gastoBean = new ShareBean();
//		}
//		List<Share> listaGastos = gastoBean.findAllByIdPropietario(grupo.getIdPropietario());
//		return Response.ok(listaGastos).build();
//	}

}