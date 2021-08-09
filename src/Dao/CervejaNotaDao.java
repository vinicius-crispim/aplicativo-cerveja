package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.CervejaNota;

public class CervejaNotaDao {

	public String deletar(CervejaNota cerveja) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			CervejaNota c = em.find(CervejaNota.class, cerveja.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro deletando CervejaNota: " + e.getMessage());
		}
	}

	// consultar
	public List<CervejaNota> consultar(Double nota) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;

		q = em.createQuery("select g from CervejaNota g" + " where media = :nota");
		q.setParameter("nota", nota);

		return q.getResultList();
	}

	public List<CervejaNota> achaNota(Integer cerveja_id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;

		q = em.createQuery("select g from CervejaNota g" + " where cerveja_id = :cerveja_id");
		q.setParameter("cerveja_id", cerveja_id);
		
		return q.getResultList();
	}
	
	public List<CervejaNota> autentica(Integer cerveja_id, Integer usuario_id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;

		q = em.createQuery("select g from CervejaNota g" + " where cerveja_id = :cerveja_id and usuario_id = :usuario_id");
		q.setParameter("cerveja_id", cerveja_id);
		q.setParameter("usuario_id", usuario_id);
		
		return q.getResultList();
	}
	
}
