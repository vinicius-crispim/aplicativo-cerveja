package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Usuario;

public class UsuarioDao {

	// excluir
	public String deletar(Usuario usuario) throws Exception {
		try {
			EntityManager em = Fabrica.getEntityManager();
			Usuario c = em.find(Usuario.class, usuario.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			return "Ok";
		} catch (Exception e) {
			throw new Exception("Erro gravando Usuario: " + e.getMessage());
		}
	}

	// consultar
	public List<Usuario> consultar(String senha, String login) throws Exception {
		EntityManager em = Fabrica.getEntityManager();

		Query q;
		q = em.createQuery("select u from Usuario u where senha = :senha and login = :login");
		q.setParameter("senha", senha);
		q.setParameter("login", login);
		return q.getResultList();
	}

	public List<Usuario> consultarNome(String nome) throws Exception {
		EntityManager em = Fabrica.getEntityManager();

		Query q;
		q = em.createQuery("select u from Usuario u where nome = :nome");
		q.setParameter("nome", nome);

		return q.getResultList();
	}

	public Boolean ValidacaoLogin(Usuario usuario, String login, String senha) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = em.createQuery("select u from Usuario u" + " where login = :login" + " and senha = :senha");
		q.setParameter("login", usuario.getLogin());
		q.setParameter("senha", usuario.getSenha());
		if (q.getResultList().size() > 0) {

			return true;
		} else {
			return false;
		}
	}

	public String CriacaoSenha(Usuario usuario) {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		q = em.createQuery("select u from Usuario u" + " where nome = :nome");
		q.setParameter("nome", usuario.getNome());
		if (q.getResultList().size() > 0) {
			return "USUARIO";
		} else {
			q = em.createQuery("select u from Usuario u" + " where login = :login");
			q.setParameter("login", usuario.getLogin());
			if (q.getResultList().size() > 0) {
				return "LOGIN";
			} else {
				q = em.createQuery("select u from Usuario u" + " where senha = :senha");
				q.setParameter("senha", usuario.getSenha());
				if (q.getResultList().size() > 0) {
					return "SENHA";
				} else {
					q = em.createQuery("select u from Usuario u" + " where email = :email");
					q.setParameter("email", usuario.getEmail());
					if (q.getResultList().size() > 0) {
						return "EMAIL";
					} else {
						q = em.createQuery("select u from Usuario u" + " where CPF = :cpf");
						q.setParameter("cpf", usuario.getCPF());
						if (q.getResultList().size() > 0) {
							return "CPF";
						} else {
							q = em.createQuery("select u from Usuario u" + " where telefone = :telefone");
							q.setParameter("telefone", usuario.getTelefone());
							if (q.getResultList().size() > 0) {
								return "TELEFONE";
							} else {
								return "APROVADO";
							}
						}
					}
				}
			}
		}
	}

}
