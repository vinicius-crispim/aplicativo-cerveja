package Bo;

import java.util.List;

import Classes.Coloracao;
import Dao.ColoracaoDao;
import Dao.GenericDao;

public class ColoracaoBo {

	public boolean AutenticaCadastro(Coloracao u) throws Exception {
		ColoracaoDao udao = new ColoracaoDao();
		try {
			return udao.CriacaoSenha(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void SalvarOuAlterar(Coloracao u) throws Exception {
		try {
			GenericDao<Coloracao> usuarioGeneric = new GenericDao<Coloracao>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void Deletar(Coloracao u) throws Exception {
		ColoracaoDao usuariodao = new ColoracaoDao();
		try {
			usuariodao.deletar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Coloracao> Consultar(String nomePesquisa) throws Exception {
		ColoracaoDao usuariodao = new ColoracaoDao();
		try {
			return usuariodao.consultar(nomePesquisa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
