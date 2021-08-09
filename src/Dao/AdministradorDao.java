package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Administrador;
import Classes.Cerveja;

public class AdministradorDao {
	public String deletar(Administrador cerveja) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			Administrador c = em.find(Administrador.class, cerveja.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro deletando Administrador: " + e.getMessage());
		}
	}

	// consultar
	public List<Cerveja> consultar() throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
			q = em.createQuery("select g from Cerveja g" + " where status = 'ANALISANDO'");
		

		return q.getResultList();
	}

	public List<Administrador> autentica(String login, String senha) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
			q = em.createQuery("select g from Administrador g" + " where login = :login and senha = :senha");
			q.setParameter("login", login);
			q.setParameter("senha", senha);
		

		return q.getResultList();
	}
	
}
