package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Pais;

public class PaisDao {
	public String deletar(Pais pais) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			Pais c = em.find(Pais.class, pais.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro deletando Pais: " + e.getMessage());
		}
	}

	// consultar
	public List<Pais> consultar(String nomePesquisa) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		if (nomePesquisa.equals("")) {
			q = em.createQuery("from Pais");
		} else {
			q = em.createQuery("select g from Pais g" + " where nomePais like :nomePais");
			q.setParameter("nomePais", "%" + nomePesquisa + "%");
		}

		return q.getResultList();
	}

	public Boolean CriacaoSenha(Pais pais) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = em.createQuery("select u from Pais u" + " where nomePais = :nomePais");
		q.setParameter("nomePais", pais.getNomePais());
		if (q.getResultList().size() == 0) {
			return true;
		} else {
			return false;
		}
	}

//	public void InserirTeste(Pais p) throws Exception {
//		EntityManager em = Fabrica.getEntityManager();
//		Query q = em.createQuery("select u from Pais u" + " where nomePais = :nomePais");
//		q.setParameter("NomePais", p.getNomePais());
//		q.getResultList();
//		
//		
//	}
	
}
