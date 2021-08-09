package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Coloracao;

public class ColoracaoDao {
	
	public String deletar(Coloracao cerveja) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			Coloracao c = em.find(Coloracao.class, cerveja.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro deletando Coloracao: " + e.getMessage());
		}
	}

	// consultar
	public List<Coloracao> consultar(String nomePesquisa) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		// Apelidos(Alias)
		// Query q = em.createQuery("from Coloracao");
		// Query q = em.createQuery("from Coloracao g");
		// Query q = em.createQuery("from Coloracao as g");

		// Campo
		// Query q = em.createQuery("select g from Coloracao g");
		// Query q = em.createQuery("select g from Coloracao as g");
		// Query q = em.createQuery("select new Coloracao(id, nome) from Coloracao as g");

		// Selecionar cerveja com like
		// Começa com (SQL -> SELECT * FROM cerveja WHERE NOME like 'ELETRO%';)
		// Query q = em.createQuery("select g from Coloracao g"
		// +" where nome like :nome");
		// q.setParameter("nome", "ELETRO%" );

		// Termina com (SQL -> SELECT * FROM cerveja WHERE NOME like '%ICOS';)
		// Query q = em.createQuery("select g from Coloracao g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%ICOS" );

		// Contem (SQL -> SELECT * FROM cerveja WHERE NOME like '%ARR%';)
		// Query q = em.createQuery("select g from Coloracao g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%ARR%" );

		// Query q = em.createQuery("select g from Coloracao g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%" + "ARR" + "%");
		Query q;
		if (nomePesquisa.equals("")) {
			q = em.createQuery("from Coloracao");
		} else {
			q = em.createQuery("select g from Coloracao g" + " where coloracao like :coloracao");
			q.setParameter("coloracao", "%" + nomePesquisa + "%");
		}

		return q.getResultList();
	}

	public Boolean CriacaoSenha(Coloracao cerveja) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = em.createQuery("select u from Coloracao u" + " where coloracao = :coloracao");
		q.setParameter("coloracao", cerveja.getColoracao());
		if (q.getResultList().size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
