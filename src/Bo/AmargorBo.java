package Bo;

import java.util.List;

import Classes.Amargor;
import Dao.AmargorDao;
import Dao.GenericDao;

public class AmargorBo {

	public boolean AutenticaCadastro(Amargor u) throws Exception {
		AmargorDao udao = new AmargorDao();
		try {
			return udao.CriacaoSenha(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void SalvarOuAlterar(Amargor u) throws Exception {
		try {
			GenericDao<Amargor> usuarioGeneric = new GenericDao<Amargor>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void Deletar(Amargor u) throws Exception {
		AmargorDao usuariodao = new AmargorDao();
		try {
			usuariodao.deletar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Amargor> Consultar(String nomePesquisa) throws Exception {
		AmargorDao usuariodao = new AmargorDao();
		try {
			return usuariodao.consultar(nomePesquisa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
