package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Bo.AmargorBo;
import Bo.CervejaBo;
import Bo.CervejaNotaBo;
import Bo.ColoracaoBo;
import Bo.EstiloCervejaBo;
import Bo.IngredienteBo;
import Bo.PaisBo;
import Classes.Amargor;
import Classes.Cerveja;
import Classes.CervejaNota;
import Classes.Coloracao;
import Classes.EstiloCerveja;
import Classes.Ingrediente;
import Classes.Pais;
import Classes.Usuario;
import Dao.Fabrica;
import javax.swing.JRadioButton;

public class TelaPesquisa extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtTeor;
	private List<Ingrediente> listaingrediente;
	private JScrollPane scroll;
	private JTable Ingredientes;
	private JTextField txtNota;
	private List<Cerveja> listafinal = new ArrayList<>();
	private JComboBox<String> comboFiltro;

	public TelaPesquisa(Usuario u) {
		setTitle("EXPLORAR CERVEJA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(135, 206, 250));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(41, 50, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setBounds(80, 47, 234, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		Ingredientes = new JTable();
		Ingredientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "INGREDIENTE" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int lin, int col) {
				return false;
			}
		}

		);
		Ingredientes.setBounds(70, 312, 145, -57);
		contentPane.add(Ingredientes);
		scroll = new JScrollPane(Ingredientes);
		scroll.setBounds(45, 202, 252, 102);
		scroll.setBackground(new Color(135, 206, 250));
		contentPane.add(scroll);

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPais.setBounds(48, 167, 46, 14);
		contentPane.add(lblPais);

		JComboBox<Pais> comboPais = new JComboBox<Pais>();
		comboPais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboPais.setBounds(76, 163, 165, 22);
		contentPane.add(comboPais);

		try {
			PaisBo pbo = new PaisBo();
			List<Pais> list1 = pbo.Consultar("");
			comboPais.addItem(new Pais(0, ""));
			for (Pais pais : list1) {
				comboPais.addItem(pais);
			}
		} catch (Exception e1) {
			System.out.println("Erro: " + e1.getMessage());
			e1.printStackTrace();
		}

		JLabel lblAmargor = new JLabel("  Amargor:");
		lblAmargor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmargor.setBounds(254, 107, 63, 19);
		contentPane.add(lblAmargor);

		JComboBox<Amargor> comboAmargor = new JComboBox<Amargor>();
		comboAmargor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboAmargor.setBounds(318, 105, 182, 22);
		contentPane.add(comboAmargor);
		try {
			AmargorBo abo = new AmargorBo();
			List<Amargor> list1 = abo.Consultar("");
			comboAmargor.addItem(new Amargor(0, ""));
			for (Amargor amargor : list1) {
				comboAmargor.addItem(amargor);
			}
		} catch (Exception e1) {
			System.out.println("Erro: " + e1.getMessage());
			e1.printStackTrace();
		}

		JComboBox<EstiloCerveja> comboEstilo = new JComboBox<EstiloCerveja>();
		comboEstilo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboEstilo.setBounds(76, 107, 165, 22);
		contentPane.add(comboEstilo);
		try {
			EstiloCervejaBo ebp = new EstiloCervejaBo();
			List<EstiloCerveja> list1 = ebp.Consultar("");
			comboEstilo.addItem(new EstiloCerveja(0, ""));
			for (EstiloCerveja estiloCerveja : list1) {
				comboEstilo.addItem(estiloCerveja);
			}
		} catch (Exception e1) {
			System.out.println("Erro: " + e1.getMessage());
			e1.printStackTrace();
		}

		JLabel lblEstilo = new JLabel("   Estilo:");
		lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstilo.setBounds(27, 108, 63, 19);
		contentPane.add(lblEstilo);

		JLabel lblColoracao = new JLabel("Colora\u00E7\u00E3o:");
		lblColoracao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblColoracao.setBounds(254, 165, 63, 18);
		contentPane.add(lblColoracao);

		JComboBox<Coloracao> comboColoracao = new JComboBox<Coloracao>();
		comboColoracao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboColoracao.setBounds(318, 163, 182, 22);
		contentPane.add(comboColoracao);

		try {
			ColoracaoBo cbo = new ColoracaoBo();
			List<Coloracao> listaco = cbo.Consultar("");
			comboColoracao.addItem(new Coloracao(0, ""));
			for (Coloracao coloracao : listaco) {
				comboColoracao.addItem(coloracao);
			}
		} catch (Exception e1) {
			System.out.println("Erro:" + e1.getMessage());
			e1.printStackTrace();
		}

		JLabel lblTeor = new JLabel("Teor Alco\u00F3lico:");
		lblTeor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeor.setBounds(333, 50, 95, 14);
		contentPane.add(lblTeor);

		txtTeor = new JTextField();
		txtTeor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTeor.setBounds(421, 47, 38, 20);
		contentPane.add(txtTeor);
		txtTeor.setColumns(10);

		List<Ingrediente> ingselecionados = new ArrayList<>();

		JButton btnIngrediente = new JButton("Adicionar ingrediente");
		btnIngrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = Ingredientes.getSelectedRow();
				String nome = (String) Ingredientes.getValueAt(linha, 0);

				IngredienteBo pbo = new IngredienteBo();

				try {
					List<Ingrediente> temp = pbo.Consultar(nome);
					for (Ingrediente ing : temp) {
						ingselecionados.add(ing);

					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnIngrediente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIngrediente.setBounds(333, 244, 167, 23);
		contentPane.add(btnIngrediente);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar(u, comboPais, comboAmargor, comboEstilo, comboColoracao, ingselecionados);
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPesquisar.setBounds(383, 396, 102, 25);
		contentPane.add(btnPesquisar);
		pesquisarIngrediente();
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaMenu(u);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(71, 397, 89, 23);
		contentPane.add(btnVoltar);

		comboFiltro = new JComboBox();
		comboFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboFiltro.setBounds(127, 326, 200, 22);
		contentPane.add(comboFiltro);

		comboFiltro.addItem("");
		comboFiltro.addItem("MAIS RECENTES");
		comboFiltro.addItem("MELHORES NOTAS");
		comboFiltro.addItem("MAIS FAVORITADAS");

		JLabel lblFiltro = new JLabel("Filtros de pesquisa:");
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFiltro.setBounds(13, 328, 126, 20);
		contentPane.add(lblFiltro);

		JLabel lblLimitar = new JLabel("Nota:");
		lblLimitar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLimitar.setBounds(379, 330, 31, 18);
		contentPane.add(lblLimitar);

		txtNota = new JTextField();
		txtNota.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNota.setBounds(411, 328, 38, 20);
		contentPane.add(txtNota);
		txtNota.setColumns(10);

		this.setVisible(true);
	}

	private void pesquisar(Usuario u, JComboBox<Pais> comboPais, JComboBox<Amargor> comboAmargor,
			JComboBox<EstiloCerveja> comboEstilo, JComboBox<Coloracao> comboColoracao, List<Ingrediente> lingre) {
		CervejaBo cbo = new CervejaBo();
		Cerveja c = new Cerveja();
		c.setNomeCerveja(txtNome.getText());
		Double teor;
		if (txtTeor.getText().equals("")) {
			teor = 0.0;
		} else {
			teor = Double.parseDouble(txtTeor.getText());
		}
		double nota;
		if (txtNota.getText().equals("")) {
			nota = 0.0;
		} else {
			nota = Double.parseDouble(txtNota.getText());
		}

		Pais p = new Pais();
		Coloracao co = new Coloracao();
		Amargor a = new Amargor();
		EstiloCerveja ec = new EstiloCerveja();
		p = (Pais) comboPais.getSelectedItem();
		try {
			ec = (EstiloCerveja) comboEstilo.getSelectedItem();
			a = (Amargor) comboAmargor.getSelectedItem();
			co = (Coloracao) comboColoracao.getSelectedItem();
			List<Cerveja> listacerveja = cbo.consultarPesquisaMulti(txtNome.getText(), p.getId(), ec.getId(), teor,
					a.getId(), co.getId());

			if (lingre.size() == 0) {
				if (nota == 0) {
					listafinal.addAll(listacerveja);
				} else {
					Connection con;
					con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root",
							"vini030902");
					PreparedStatement pstnt = null;
					ResultSet rs = null;
					CervejaNotaBo cnbo = new CervejaNotaBo();
					List<Cerveja> cervejacomNota = new ArrayList<>();
					List<CervejaNota> cnota = (cnbo.Consultar(Double.parseDouble(txtNota.getText())));
					for (Cerveja c_id : listacerveja) {
						for (CervejaNota cervejaNota : cnota) {
							String cSql = "select * from tb_usuario_nota where cervejanota_id = (?) and cerveja_id = (?)";
							System.out.println(cervejaNota.getId() + " - " + cervejaNota.getMedia());
							pstnt = con.prepareStatement(cSql);
							pstnt.setInt(1, cervejaNota.getId());
							pstnt.setInt(2, c_id.getId());
							pstnt.execute();
							rs = pstnt.executeQuery();

							while (rs.next()) {
								Integer id_i = rs.getInt(3);
								List<Cerveja> lcervejateste = cbo.ConsultarFavoritas(id_i);
								for (Cerveja cer : lcervejateste) {
									cervejacomNota.add(cer);
								}
							}
						}
					}
					List<Integer> ids = new ArrayList<>();
					for (Cerveja cervejaNota : cervejacomNota) {
						ids.add(cervejaNota.getId());
					}

					Set<Integer> semrepetir = new LinkedHashSet<Integer>(ids);
					List<Cerveja> listfinal = new ArrayList<>();
					List<Cerveja> auxlist = new ArrayList<>();
					for (Integer integer : semrepetir) {
						System.out.println("ID CONSULTADO " + integer);
						auxlist = (cbo.ConsultarFavoritas(integer));
						listfinal.add(auxlist.get(0));
					}

					pstnt.close();
					con.close();
					rs.close();
					listafinal.addAll(listfinal);
				}
			} else if (lingre.size() != 0) {
				List<Cerveja> cin = new ArrayList<>();
				Connection con;
				con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root",
						"vini030902");
				PreparedStatement pstnt = null;
				ResultSet rs = null;
				Integer totalingrediente = lingre.size();
				int aux = 0;
				for (Cerveja cerveja : listacerveja) {
					aux = 0;
					for (Ingrediente ingrediente : lingre) {
						String cSql = "select * from tb_cerveja_ingredientes where ingrediente_id = (?) and cerveja_id = (?)";
						pstnt = con.prepareStatement(cSql);
						pstnt.setInt(1, ingrediente.getId());
						pstnt.setInt(2, cerveja.getId());

						rs = pstnt.executeQuery();

						while (rs.next()) {
							aux++;
							if (aux == totalingrediente) {
								Integer id_i = rs.getInt(1);
								List<Cerveja> lcervejateste = cbo.ConsultarFavoritas(id_i);
								for (Cerveja cer : lcervejateste) {
									cin.add(cer);
								}
							}
						}

					}
				}
				if (nota == 0) {
//					new TelaCervejaConsulta(cin, u);
					listafinal.addAll(cin);
				} else if (nota >= 1) {
					con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root",
							"vini030902");
					CervejaNotaBo cnbo = new CervejaNotaBo();
					List<Cerveja> cervejacomNota = new ArrayList<>();
					List<CervejaNota> cnota = (cnbo.Consultar(nota));
					for (Cerveja c_id : cin) {
						for (CervejaNota cervejaNota : cnota) {
							String cSql = "select * from tb_usuario_nota where cervejanota_id = (?) and cerveja_id = (?)";
							pstnt = con.prepareStatement(cSql);
							pstnt.setInt(1, cervejaNota.getId());
							pstnt.setInt(2, c_id.getId());
							pstnt.execute();
							rs = pstnt.executeQuery();

							while (rs.next()) {
								Integer id_i = rs.getInt(3);
								List<Cerveja> lcervejateste = cbo.ConsultarFavoritas(id_i);
								for (Cerveja cer : lcervejateste) {
									cervejacomNota.add(cer);
								}
							}
						}
					}
					List<Integer> ids = new ArrayList<>();
					for (Cerveja cervejaNota : cervejacomNota) {
						ids.add(cervejaNota.getId());
					}

					Set<Integer> semrepetir = new LinkedHashSet<Integer>(ids);
					List<Cerveja> listfinal = new ArrayList<>();
					List<Cerveja> auxlist = new ArrayList<>();
					for (Integer integer : semrepetir) {
						System.out.println("ID CONSULTADO " + integer);
						auxlist = (cbo.ConsultarFavoritas(integer));
						listfinal.add(auxlist.get(0));
					}
					listafinal.addAll(listfinal);
					rs.close();
					pstnt.close();
					con.close();
				}
			}
			filtros(listafinal, comboFiltro, u);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	public void filtros(List<Cerveja> cervejalist, JComboBox combofiltro, Usuario u) {
		EntityManager em = Fabrica.getEntityManager();
		try {
			List<Integer> auxlista = new ArrayList<>();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root",
					"vini030902");
			PreparedStatement pstnt = null;
			ResultSet rs = null;
			List<Cerveja> fim = new ArrayList<>();
			List<Cerveja> list = new ArrayList<>();
			CervejaBo cbo = new CervejaBo();
				if (comboFiltro.getSelectedItem().equals("MELHORES NOTAS")) {
					String cSql = "SELECT media, tb_usuario_nota.cerveja_id FROM cervejanota"
							+ " INNER JOIN tb_usuario_nota on tb_usuario_nota.cervejanota_id = cervejanota.id"
							+ " INNER JOIN cerveja on cerveja.id =tb_usuario_nota.cerveja_id"
							+ " group by tb_usuario_nota.cerveja_id" + " order by media desc";
					pstnt = con.prepareStatement(cSql);
					pstnt.execute();
					rs = pstnt.executeQuery();
					while (rs.next()) {
						Integer id = rs.getInt(2);
						auxlista.add(id);
					}
					List<Integer> teste = new ArrayList<>();
					for (Cerveja cerveja : cervejalist) {
						teste.add(cerveja.getId());
					}
					List<Integer> finalid = new ArrayList<>();
					for (Integer integer : auxlista) {
						if (teste.contains(integer)) {
							finalid.add(integer);
						}
					}
					List<Cerveja> espero = new ArrayList<>();
					int total = cervejalist.size();
					for (Integer inte : finalid) {
						total++;
						if (total >= espero.size()) {
							List<Cerveja> temp = cbo.ConsultarFavoritas(inte);
							espero.add(temp.get(0));
						}
					}
					new TelaCervejaConsulta(espero, u);
				} else if (comboFiltro.getSelectedItem().equals("MAIS FAVORITADAS")) {
					String cSql = "SELECT COUNT(cerveja_id) 'total', cerveja_id"
							+ " FROM `projetointegrador`.`tb_usuario_cerveja`"
							+ " group BY cerveja_id"
							+ " order by count(*) desc";
					pstnt = con.prepareStatement(cSql);
					pstnt.execute();
					rs = pstnt.executeQuery();
					while (rs.next()) {
						Integer id = rs.getInt(2);
						auxlista.add(id);
					}
					List<Integer> teste = new ArrayList<>();
					for (Cerveja cerveja : cervejalist) {
						teste.add(cerveja.getId());
					}
					List<Integer> finalid = new ArrayList<>();
					for (Integer integer : auxlista) {
						if (teste.contains(integer)) {
							finalid.add(integer);
						}
					}
					List<Cerveja> espero = new ArrayList<>();
					int total = cervejalist.size();
					for (Integer inte : finalid) {
						total++;
						if (total >= espero.size()) {
							List<Cerveja> temp = cbo.ConsultarFavoritas(inte);
							espero.add(temp.get(0));
						}
					}
					new TelaCervejaConsulta(espero, u);
				}
			
			if (comboFiltro.getSelectedItem().equals("MAIS RECENTES")) {
				for (int i = listafinal.size() - 1; i >= 0; i--) {
					list.add(cervejalist.get(i));
				}
				new TelaCervejaConsulta(list,u);

			} else if (comboFiltro.getSelectedItem().equals("")) {
				new TelaCervejaConsulta(cervejalist,u);
			} 
			dispose();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, x.getMessage());
		}
	}

	private void pesquisarIngrediente() {
		// Carregar o model na JTable
		DefaultTableModel modelo = (DefaultTableModel) this.Ingredientes.getModel();
		modelo.setRowCount(0);
		Ingredientes.setModel(modelo);
		try {

			IngredienteBo cbo = new IngredienteBo();
			List<Ingrediente> list = cbo.Consultar("");
			for (Ingrediente cer : list) {
				modelo.addRow(new Object[] { cer.getIngrediente() });
			}

		} catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "ERRO: " + exc.getMessage());
		}
	}
}
