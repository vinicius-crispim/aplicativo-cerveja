package Bo;

import java.util.List;

import Classes.EstiloCerveja;
import Dao.EstiloCervejaDao;
import Dao.GenericDao;

public class EstiloCervejaBo {

	public boolean AutenticaCadastro(EstiloCerveja u) throws Exception {
		EstiloCervejaDao udao = new EstiloCervejaDao();
		try {
			return udao.CriacaoSenha(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void SalvarOuAlterar(EstiloCerveja u) throws Exception {
		try {
			GenericDao<EstiloCerveja> usuarioGeneric = new GenericDao<EstiloCerveja>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void Deletar(EstiloCerveja u) throws Exception {
		EstiloCervejaDao usuariodao = new EstiloCervejaDao();
		try {
			usuariodao.deletar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<EstiloCerveja> Consultar(String nomePesquisa) throws Exception {
		EstiloCervejaDao usuariodao = new EstiloCervejaDao();
		try {
			return usuariodao.consultar(nomePesquisa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
