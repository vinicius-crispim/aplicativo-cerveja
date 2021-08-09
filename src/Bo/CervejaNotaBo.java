package Bo;

import java.util.List;

import Classes.CervejaNota;
import Dao.GenericDao;
import Dao.CervejaNotaDao;

public class CervejaNotaBo {

	public void SalvarOuAlterar(CervejaNota u) throws Exception {
		try {
			GenericDao<CervejaNota> cervejanotaGeneric = new GenericDao<CervejaNota>();
			cervejanotaGeneric.salvarOuAtualizar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void Deletar(CervejaNota u) throws Exception {
		CervejaNotaDao cervejanotadao = new CervejaNotaDao();
		try {
			cervejanotadao.deletar(u);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<CervejaNota> achaNota(Integer idcerveja) throws Exception {
		CervejaNotaDao cervejanotadao = new CervejaNotaDao();
		try {
			return cervejanotadao.achaNota(idcerveja);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<CervejaNota> Consultar(Double nota) throws Exception {
		CervejaNotaDao cervejanotadao = new CervejaNotaDao();
		try {
			return cervejanotadao.consultar(nota);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<CervejaNota> autentica(Integer cerveja_id, Integer usuario_id) throws Exception {
		CervejaNotaDao cervejanotadao = new CervejaNotaDao();
		try {
			return cervejanotadao.autentica(cerveja_id,usuario_id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
