package com.fernandopaniagua.server.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fernandopaniagua.server.entities.Share;

import javax.inject.Named;

@Stateless
@Named(value = "gastoBean")
public class ShareBean extends AbstractPersistenceBean<Share> {
	public ShareBean() {
        super(Share.class);
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

    public List<Share> findAllByOwnerId(int idPropietario) {
    	EntityManager em = getEntityManager();
    	openTransaction(em);
    	String sentencia = "Select g from Share as g where g.idPropietario = " + idPropietario + " order by g.fecha desc";
    	Query gastoQuery = em.createQuery(sentencia);
    	List<Share> searchList = gastoQuery.getResultList();
        closeTransation(em);
        return searchList;
    }
}