package com.fernandopaniagua.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.fernandopaniagua.server.entities.User;
import java.util.List;
public class DesktopLauncher {

	private static final String PERSISTENCE_UNIT_NAME = "autentia-server-javaee";
    private static EntityManagerFactory factory;
	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Usuario u");
        List<User> usuarios = q.getResultList();
        for (User usuario : usuarios) {
            System.out.println(usuario);
        }
        System.out.println("Size: " + usuarios.size());
        em.getTransaction().begin();
        User usuario = new User ("Usuario de prueba");
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
	}

}
