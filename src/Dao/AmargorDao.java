package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Amargor;

public class AmargorDao {

	public String deletar(Amargor cerveja) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			Amargor c = em.find(Amargor.class, cerveja.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro deletando Amargor: " + e.getMessage());
		}
	}

	// consultar
	public List<Amargor> consultar(String nomePesquisa) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		// Apelidos(Alias)
		// Query q = em.createQuery("from Amargor");
		// Query q = em.createQuery("from Amargor g");
		// Query q = em.createQuery("from Amargor as g");

		// Campo
		// Query q = em.createQuery("select g from Amargor g");
		// Query q = em.createQuery("select g from Amargor as g");
		// Query q = em.createQuery("select new Amargor(id, nome) from Amargor as g");

		// Selecionar cerveja com like
		// Começa com (SQL -> SELECT * FROM cerveja WHERE NOME like 'ELETRO%';)
		// Query q = em.createQuery("select g from Amargor g"
		// +" where nome like :nome");
		// q.setParameter("nome", "ELETRO%" );

		// Termina com (SQL -> SELECT * FROM cerveja WHERE NOME like '%ICOS';)
		// Query q = em.createQuery("select g from Amargor g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%ICOS" );

		// Contem (SQL -> SELECT * FROM cerveja WHERE NOME like '%ARR%';)
		// Query q = em.createQuery("select g from Amargor g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%ARR%" );

		// Query q = em.createQuery("select g from Amargor g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%" + "ARR" + "%");
		Query q;
		if (nomePesquisa.equals("")) {
			q = em.createQuery("from Amargor");
		} else {
			q = em.createQuery("select g from Amargor g" + " where amargor like :amargor");
			q.setParameter("amargor", "%" + nomePesquisa + "%");
		}

		return q.getResultList();
	}

	public Boolean CriacaoSenha(Amargor cerveja) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = em.createQuery("select u from Amargor u" + " where amargor = :amargor");
		q.setParameter("amargor", cerveja.getAmargor());
		if (q.getResultList().size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
