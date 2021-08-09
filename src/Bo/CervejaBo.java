package Bo;

import java.util.List;

import Classes.Cerveja;
import Classes.Usuario;
import Dao.CervejaDao;
import Dao.GenericDao;

public class CervejaBo {

	public boolean AutenticaCadastro(Cerveja u) throws Exception {
		CervejaDao udao = new CervejaDao();
		try {
			return udao.CriacaoSenha(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void SalvarOuAlterar(Cerveja u) throws Exception {
		try {
			GenericDao<Cerveja> usuarioGeneric = new GenericDao<Cerveja>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	public List<Cerveja> ConsultarFavoritas(Integer id) throws Exception {
		CervejaDao usuariodao = new CervejaDao();
		try {
			return usuariodao.consultarFavoritas(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void excluirCerveja(Cerveja c) throws Exception {
		CervejaDao usuariodao = new CervejaDao();
		try {
			usuariodao.deletar(c);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Cerveja> ConsultarPubli(String nome) throws Exception {
		CervejaDao usuariodao = new CervejaDao();
		try {
			return usuariodao.consultarPubli(nome);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Cerveja> consultarPesquisaMulti(String paramNome,Integer idpais, Integer idestilo,Double teor,Integer idamargor,Integer idcoloracao) throws Exception {
		CervejaDao usuariodao = new CervejaDao();
		try {
			return usuariodao.pesquisaMultipla(paramNome,idpais,idestilo,teor,idamargor,idcoloracao);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Cerveja> ConsultarCervejasUsuario(Integer id) throws Exception {
		CervejaDao usuariodao = new CervejaDao();
		try {
			return usuariodao.consultarPublicaoPostadas(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Cerveja> ConsultarPostagemADM(String nome) throws Exception {
		CervejaDao usuariodao = new CervejaDao();
		try {
			return usuariodao.consultaADM(nome);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
