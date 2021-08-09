package Bo;

import java.util.List;

import Classes.Usuario;
import Dao.GenericDao;
import Dao.UsuarioDao;

public class UsuarioBo {
	
	public boolean Autenticar(Usuario u) throws Exception {
		UsuarioDao udao = new UsuarioDao();
		try {
		return udao.ValidacaoLogin(u, u.getLogin(), u.getSenha());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public String AutenticaCadastro(Usuario u) throws Exception{
		UsuarioDao udao = new UsuarioDao();
		try {
			return udao.CriacaoSenha(u);
		} catch (Exception e) {
			throw new Exception (e.getMessage());
		}
	}
	
	
	public void SalvarOuAlterar(Usuario u) throws Exception {
		try {
			GenericDao<Usuario> usuarioGeneric = new GenericDao<Usuario>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void Deletar(Usuario u) throws Exception {
		UsuarioDao usuariodao = new UsuarioDao();
		try {
			usuariodao.deletar(u);
		}catch (Exception e){
			throw new Exception (e.getMessage());
		}
	}
	
	public List<Usuario> Consultar(String senha, String login) throws Exception {
		UsuarioDao usuariodao = new UsuarioDao();
		try {
			return usuariodao.consultar(senha, login);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Usuario> ConsultarUsuarioNome(String nome) throws Exception {
		UsuarioDao usuariodao = new UsuarioDao();
		try {
			return usuariodao.consultarNome(nome);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
}
