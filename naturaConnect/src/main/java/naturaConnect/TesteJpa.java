package naturaConnect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Tags;
import entidades.Usuario;


public class TesteJpa {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("naturaConnect");

		EntityManager em = emf.createEntityManager();
		Usuario usuario = new Usuario();
		usuario.setId(1);
		
		
	
		Tags teste= new Tags();

		teste.setDescricao("Teste2");
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(teste);

		tx.commit();

		em.close();

		emf.close();
	}
}
