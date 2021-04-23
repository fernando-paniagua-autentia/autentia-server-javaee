package com.fernandopaniagua.server.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.fernandopaniagua.server.entities.Group;

import javax.inject.Named;

@Stateless
@Named(value = "grupoBean")
public class GroupBean extends AbstractPersistenceBean<Group> {
	public GroupBean() {
        super(Group.class);
    }

    @PersistenceContext(unitName = "autentia-server-javaee")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
    	if (em==null) {
    		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("autentia-server-javaee");
    		em = emfactory.createEntityManager( );
    	}
        return em;
    }
}