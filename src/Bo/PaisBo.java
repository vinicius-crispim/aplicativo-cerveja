package Bo;

import java.util.List;

import Classes.Pais;
import Dao.PaisDao;
import Dao.GenericDao;

public class PaisBo {

	public boolean AutenticaCadastro(Pais u) throws Exception {
		PaisDao udao = new PaisDao();
		try {
			return udao.CriacaoSenha(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void SalvarOuAlterar(Pais u) throws Exception {
		try {
			GenericDao<Pais> usuarioGeneric = new GenericDao<Pais>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void Deletar(Pais u) throws Exception {
		PaisDao usuariodao = new PaisDao();
		try {
			usuariodao.deletar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Pais> Consultar(String nomePesquisa) throws Exception {
		PaisDao usuariodao = new PaisDao();
		try {
			return usuariodao.consultar(nomePesquisa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	
}
