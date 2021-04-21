package com.fernandopaniagua.server.beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractPersistenceBean<T> {

    private Class<T> entityClass;

    public AbstractPersistenceBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    protected void openTransaction(EntityManager em) {
    	em.getTransaction().begin();
    }
    protected void closeTransation(EntityManager em) {
    	em.getTransaction().commit();
        em.close();
    }
    
    public void create(T entity) {
    	EntityManager em = getEntityManager();
    	openTransaction(em);
        em.persist(entity);
        closeTransation(em);
    }

    public void edit(T entity) {
    	EntityManager em = getEntityManager();
    	openTransaction(em);
        em.merge(entity);
        closeTransation(em);
    }

    public void remove(T entity) {
    	EntityManager em = getEntityManager();
    	openTransaction(em);
        em.remove(getEntityManager().merge(entity));
        closeTransation(em);
    }

    public T find(Object id) {
    	EntityManager em = getEntityManager();
    	openTransaction(em);
    	T searchObject = getEntityManager().find(entityClass, id);
    	closeTransation(em);
        return searchObject;
    }

    public List<T> findAll() {
    	EntityManager em = getEntityManager();
    	openTransaction(em);
        CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> searchList = (List<T>) getEntityManager().createQuery(cq).getResultList();
        closeTransation(em);
        return searchList;
    }
    
    /*
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    */
}

