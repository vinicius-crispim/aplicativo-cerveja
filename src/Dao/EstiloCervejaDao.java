package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Cerveja;
import Classes.EstiloCerveja;

public class EstiloCervejaDao {

	public String deletar(EstiloCerveja cerveja) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			EstiloCerveja c = em.find(EstiloCerveja.class, cerveja.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro deletando Cerveja: " + e.getMessage());
		}
	}

	public List<EstiloCerveja> consultar(String nomePesquisa) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		// Apelidos(Alias)
		// Query q = em.createQuery("from Cerveja");
		// Query q = em.createQuery("from Cerveja g");
		// Query q = em.createQuery("from Cerveja as g");

		// Campo
		// Query q = em.createQuery("select g from Cerveja g");
		// Query q = em.createQuery("select g from Cerveja as g");
		// Query q = em.createQuery("select new Cerveja(id, nome) from Cerveja as g");

		// Selecionar cerveja com like
		// Começa com (SQL -> SELECT * FROM cerveja WHERE NOME like 'ELETRO%';)
		// Query q = em.createQuery("select g from Cerveja g"
		// +" where nome like :nome");
		// q.setParameter("nome", "ELETRO%" );

		// Termina com (SQL -> SELECT * FROM cerveja WHERE NOME like '%ICOS';)
		// Query q = em.createQuery("select g from Cerveja g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%ICOS" );

		// Contem (SQL -> SELECT * FROM cerveja WHERE NOME like '%ARR%';)
		// Query q = em.createQuery("select g from Cerveja g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%ARR%" );

		// Query q = em.createQuery("select g from Cerveja g"
		// +" where nome like :nome");
		// q.setParameter("nome", "%" + "ARR" + "%");
		Query q;
		if (nomePesquisa.equals("")) {
			q = em.createQuery("from EstiloCerveja g");
		} else {
			q = em.createQuery("select g from EstiloCerveja g" + " where estilo like :nomeCerveja");
			q.setParameter("nomeCerveja", "%" + nomePesquisa + "%");
		}

		return q.getResultList();
	}
	
	public Boolean CriacaoSenha(EstiloCerveja cerveja) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = em.createQuery("select u from EstiloCerveja u" + " where estilo = :estilocerveja");
		q.setParameter("estilocerveja", cerveja.getEstilo());
		
		if (q.getResultList().size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	
}
