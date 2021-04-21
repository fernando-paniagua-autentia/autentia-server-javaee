package com.fernandopaniagua.server.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import com.fernandopaniagua.server.beans.AbstractPersistenceBean;
import com.fernandopaniagua.server.beans.UserBean;
import com.fernandopaniagua.server.entities.User;

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

@Path("/users")
@SessionScoped
public class UserController implements Serializable {

	@EJB
	private AbstractPersistenceBean<User> usuarioBean;
	
	private User usuario = new User();
	
	public AbstractPersistenceBean<User> getUsuarioBean() {
        return usuarioBean;
    }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User newUser) {
		if (usuarioBean==null) {
			System.out.println("************ HA FALLADO LA INYECCION DE USUARIO ***********");
			usuarioBean = new UserBean();
		}
		usuarioBean.create(newUser);
		//return Response.ok(listaUsuarios).build();
		return Response.ok().build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		if (usuarioBean==null) {
			usuarioBean = new UserBean();
		}
		List<User>listaUsuarios = usuarioBean.findAll();
		return Response.ok(listaUsuarios).build();
	}

}