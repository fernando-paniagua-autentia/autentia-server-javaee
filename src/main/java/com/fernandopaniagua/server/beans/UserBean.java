package com.fernandopaniagua.server.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.fernandopaniagua.server.entities.User;

import javax.inject.Named;

@Stateless
@Named(value = "usuarioBean")
public class UserBean extends AbstractPersistenceBean<User> {
	public UserBean() {
        super(User.class);
    }

    @PersistenceContext(unitName = "autentia-server-javaee")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
    	if (em==null) {
    		System.out.println("***********ENTITYMANAGER A NULL***********");
    		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("autentia-server-javaee");
    		em = emfactory.createEntityManager( );
    	}
        return em;
    }
}