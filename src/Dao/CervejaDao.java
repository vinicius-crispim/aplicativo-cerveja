package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Classes.Cerveja;
import Classes.CervejaNota;

public class CervejaDao {

	public List<Cerveja> consultarPubli(String nome) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		q = em.createQuery("select c from Cerveja c where nomeCerveja = :nomeCerveja" + " and status = 'APROVADA'");
		q.setParameter("nomeCerveja", nome);

		return q.getResultList();
	}

	public void deletar(Cerveja cerveja) throws Exception {

		try {
			EntityManager em = Fabrica.getEntityManager();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root", "vini030902");

			String cSql = "DELETE FROM TB_USUARIO_CERVEJA WHERE cerveja_id = ?";
			PreparedStatement pstmt = con.prepareStatement(cSql);
			pstmt.setInt(1, cerveja.getId());
			pstmt.execute();
			pstmt.close();

			cSql = "DELETE FROM TB_CERVEJA_INGREDIENTES WHERE cerveja_id = ?";
			pstmt = con.prepareStatement(cSql);
			pstmt.setInt(1, cerveja.getId());
			pstmt.execute();
			pstmt.close();

			if (cerveja.getStatus().equals("APROVADA")) {
				CervejaNotaDao cndao = new CervejaNotaDao();
				CervejaNota cervejanota = cndao.achaNota(cerveja.getId()).get(0);
				
				cSql = "DELETE FROM TB_USUARIO_NOTA WHERE cerveja_id = ?";
				pstmt = con.prepareStatement(cSql);
				pstmt.setInt(1, cerveja.getId());
				pstmt.execute();
				pstmt.close();

				cndao.deletar(cervejanota);
			}

			Cerveja c = em.find(Cerveja.class, cerveja.getId());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Erro gravando  Cerveja: " + e.getMessage());
		}
	}

	public List<Cerveja> consultaADM(String nome) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		q = em.createQuery("select c from Cerveja c where nomeCerveja = :nome");
		q.setParameter("nome", nome);

		return q.getResultList();
	}

	public List<Cerveja> consultarPublicaoPostadas(Integer id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		q = em.createQuery("select c from Cerveja c where usuario_id = :usuario_id and status = 'APROVADA'");
		q.setParameter("usuario_id", id);

		return q.getResultList();
	}

	public List<Cerveja> consultarFavoritas(Integer id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q;
		q = em.createQuery("select c from Cerveja c where id = :cerveja_id");
		q.setParameter("cerveja_id", id);

		return q.getResultList();
	}

	public List<Cerveja> pesquisaMultipla(String paramNome, Integer idpais, Integer idestilo, Double teor,
			Integer idamargor, Integer idcoloracao) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		String cSql = "select g from Cerveja g";
		String cWhere = "";
		Query q = null;
		if (!paramNome.equals("")) {

			cWhere = "  nomeCerveja = :paramNome";
		}
		if (teor != 0) {
			if (cWhere.equals("")) {
				cWhere = cWhere + " teorAlcoolico = :teor";
			} else {
				cWhere = cWhere + " and teorAlcoolico = :teor";
			}
		}
		if (idpais != 0) {
			if (cWhere.equals("")) {
				cWhere = cWhere + " pais_id = :pais_id";
			} else {
				cWhere = cWhere + " and pais_id = :pais_id";
			}
		}
		if (idestilo != 0) {
			if (cWhere.equals("")) {
				cWhere = cWhere + " estilo_id = :estilo_id";
			} else {
				cWhere = cWhere + " and estilo_id = :estilo_id";
			}
		}
		if (idamargor != 0) {
			if (cWhere.equals("")) {
				cWhere = cWhere + " amargor_id = :amargor_id";
			} else {
				cWhere = cWhere + " and amargor_id = :amargor_id";
			}
		}
		if (idcoloracao != 0) {
			if (cWhere.equals("")) {
				cWhere = cWhere + " coloracao_id = :coloracao_id";
			} else {
				cWhere = cWhere + " and coloracao_id = :coloracao_id";
			}
		}
		if (paramNome.equals("") && teor == 0 && idpais == 0 && idestilo == 0 && idamargor == 0 && idcoloracao == 0) {
			q = em.createQuery(cSql + " where status = 'APROVADA'");
			System.out.println(cSql);
		} else {
			q = em.createQuery(cSql + " where " + cWhere + " and status = 'APROVADA'");
			System.out.println(cSql + " WHERE " + cWhere + " and status = 'APROVADA'");

			if (!paramNome.equals("")) {

				q.setParameter("paramNome", paramNome);
			}
			if (teor != 0) {
				q.setParameter("teor", teor);
			}
			if (idpais != 0) {
				q.setParameter("pais_id", idpais);
			}
			if (idamargor != 0) {
				q.setParameter("amargor_id", idamargor);
			}
			if (idcoloracao != 0) {
				q.setParameter("coloracao_id", idcoloracao);
			}
			if (idestilo != 0) {
				q.setParameter("estilo_id", idestilo);
			}
		}

		return q.getResultList();
	} 

	public Boolean CriacaoSenha(Cerveja cerveja) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = em.createQuery("select u from Cerveja u" + " where nomeCerveja = :nomeCerveja");
		q.setParameter("nomeCerveja", cerveja.getNomeCerveja());
		if (q.getResultList().size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
