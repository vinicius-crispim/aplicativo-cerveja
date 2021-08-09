package Bo;

import java.util.List;

import Classes.Administrador;
import Classes.Cerveja;
import Dao.AdministradorDao;
import Dao.GenericDao;

public class AdministradorBo {

	public void SalvarOuAlterar(Administrador u) throws Exception {
		try {
			GenericDao<Administrador> usuarioGeneric = new GenericDao<Administrador>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void Deletar(Administrador u) throws Exception {
		AdministradorDao usuariodao = new AdministradorDao();
		try {
			usuariodao.deletar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Administrador> ValidaçãoSenha(String login, String senha) throws Exception {
		AdministradorDao usuariodao = new AdministradorDao();
		try {
			return usuariodao.autentica(login,senha);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Cerveja> ConsultarPendentes() throws Exception {
		AdministradorDao usuariodao = new AdministradorDao();
		try {
			return usuariodao.consultar();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
