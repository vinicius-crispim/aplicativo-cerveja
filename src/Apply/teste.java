package Apply;

import Classes.Amargor;
import Classes.Coloracao;
import Classes.EstiloCerveja;
import Classes.Pais;
import Classes.Usuario;
import Telas.TelaMenu;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pais p = new Pais();
		p.setNomePais("Brasil");
		Amargor a = new Amargor();
		a.setAmargor("Amargo");
		EstiloCerveja ec = new EstiloCerveja();
		ec.setEstilo("Estilo cachorro");
		Coloracao co = new Coloracao();
		co.setColoracao("Escuro");
		Usuario u = new Usuario();
		
		u.setCPF("444-444-444-11");
		u.setEmail("ccc");
		u.setId(4);
		u.setIdade(15);
	
		new TelaMenu(u);
		
	}

}
