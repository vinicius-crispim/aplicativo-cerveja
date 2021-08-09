package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Ingrediente;

public class IngredienteDao {

	public String deletar(Ingrediente cerveja) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			Ingrediente c = em.find(Ingrediente.class, cerveja.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro deletando Ingrediente: " + e.getMessage());
		}
	}

	// consultar
	public List<Ingrediente> consultar(String nomePesquisa) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		if (nomePesquisa.equals("")) {
			q = em.createQuery("from Ingrediente");
		} else {
			q = em.createQuery("select g from Ingrediente g" + " where ingrediente = :ingrediente");
			q.setParameter("ingrediente",nomePesquisa );
		}

		return q.getResultList();
	}
	
	public List<Ingrediente> consultarID(int id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		
			q = em.createQuery("select g from Ingrediente g" + " where id = :id");
			q.setParameter("id",id );
		

		return q.getResultList();
	}

	public Boolean CriacaoSenha(Ingrediente cerveja) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = em.createQuery("select u from Ingrediente u" + " where ingrediente = :ingrediente");
		q.setParameter("ingrediente", cerveja.getIngrediente());
		if (q.getResultList().size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
