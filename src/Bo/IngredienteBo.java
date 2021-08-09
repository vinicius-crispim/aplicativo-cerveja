package Bo;

import java.util.List;

import Classes.Ingrediente;
import Dao.IngredienteDao;
import Dao.GenericDao;

public class IngredienteBo {

	public boolean AutenticaCadastro(Ingrediente u) throws Exception {
		IngredienteDao udao = new IngredienteDao();
		try {
			return udao.CriacaoSenha(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void SalvarOuAlterar(Ingrediente u) throws Exception {
		try {
			GenericDao<Ingrediente> usuarioGeneric = new GenericDao<Ingrediente>();
			usuarioGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void Deletar(Ingrediente u) throws Exception {
		IngredienteDao usuariodao = new IngredienteDao();
		try {
			usuariodao.deletar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Ingrediente> Consultar(String nomePesquisa) throws Exception {
		IngredienteDao usuariodao = new IngredienteDao();
		try {
			return usuariodao.consultar(nomePesquisa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Ingrediente> ConsultarID(int id) throws Exception {
		IngredienteDao usuariodao = new IngredienteDao();
		try {
			return usuariodao.consultarID(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
