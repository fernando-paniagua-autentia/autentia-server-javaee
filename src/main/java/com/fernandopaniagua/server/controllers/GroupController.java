package com.fernandopaniagua.server.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import com.fernandopaniagua.server.beans.AbstractPersistenceBean;
import com.fernandopaniagua.server.beans.GroupBean;
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

@Path("/groups")
@SessionScoped
public class GroupController implements Serializable {

	@EJB
	private AbstractPersistenceBean<Group> grupoBean;
	
	private Group group = new Group();
	
	public AbstractPersistenceBean<Group> getGastoBean() {
        return grupoBean;
    }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGroup(Group newGrupo) {
		if (grupoBean==null) {
			grupoBean = new GroupBean();
		}
		grupoBean.create(newGrupo);
		return Response.status(Status.CREATED).build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroups() {
		if (grupoBean==null) {
			grupoBean = new GroupBean();
		}
		List<Group> groupList = grupoBean.findAll();
		return Response.ok(groupList).build();
	}

}