package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.EntityBase;

public class GenericDao <T extends EntityBase> {
	private static EntityManager em = Fabrica.getEntityManager();
	
	public void salvarOuAtualizar(T obj) throws Exception {
		try {
			em.getTransaction().begin();
			if (obj.getId() == 0) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			throw new Exception("Erro salvando : " + e.getMessage());
		}
	}

	public void remove(Class<T> classe, int id) throws Exception {
		T t = findById(classe, id);
		try {
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();

		} catch (Exception e) {
			throw new Exception("Erro deletando : " + e.getMessage());
		}
	}

	public T findById(Class<T> classe, int id) {
		return em.find(classe, id);
	}

	public List<T> list(Class<T> classe) {
		Query q = em.createQuery("select t from " + classe.getSimpleName().toString() + " t");
		return q.getResultList();
	}

}