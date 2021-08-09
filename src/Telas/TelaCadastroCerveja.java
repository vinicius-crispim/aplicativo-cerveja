package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
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
import javax.swing.ListSelectionModel;

public class TelaCadastroCerveja extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCerveja;
	private JTextField txtTeor;
	private JTable Ingredientes;
	private JScrollPane scroll;
	private List<Ingrediente> listaingrediente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCerveja frame = new TelaCadastroCerveja(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param u
	 */
	public TelaCadastroCerveja(Usuario u) {
		setTitle("Cadastrar Cerveja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 209, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(135, 206, 250));
		contentPane_1.setBounds(0, 0, 574, 411);
		contentPane.add(contentPane_1);

		JLabel lblNomeCerveja = new JLabel("Nome:");
		lblNomeCerveja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomeCerveja.setBounds(46, 123, 46, 14);
		contentPane_1.add(lblNomeCerveja);

		txtNomeCerveja = new JTextField();
		txtNomeCerveja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNomeCerveja.setBounds(85, 120, 306, 20);
		contentPane_1.add(txtNomeCerveja);
		txtNomeCerveja.setColumns(10);

		JComboBox<EstiloCerveja> comboEstilo = new JComboBox<EstiloCerveja>();
		comboEstilo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboEstilo.setToolTipText("");
		comboEstilo.setBounds(70, 161, 184, 22);
		contentPane_1.add(comboEstilo);
		EstiloCervejaBo ebp = new EstiloCervejaBo();

		try {
			List<EstiloCerveja> list1 = ebp.Consultar("");
			for (EstiloCerveja estiloCerveja : list1) {
				comboEstilo.addItem(estiloCerveja);
			}
		} catch (Exception e1) {
			System.out.println("Erro: " + e1.getMessage());
			e1.printStackTrace();
		}

		JLabel lblEstilo = new JLabel("  Estilo:");
		lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstilo.setBounds(25, 165, 55, 14);
		contentPane_1.add(lblEstilo);

		JLabel lblTeor = new JLabel("Teor Alcoolico:");
		lblTeor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeor.setBounds(411, 123, 89, 14);
		contentPane_1.add(lblTeor);

		txtTeor = new JTextField();
		txtTeor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTeor.setBounds(497, 120, 55, 20);
		contentPane_1.add(txtTeor);
		txtTeor.setColumns(3);

		JLabel lblAmargor = new JLabel(" Amargor:");
		lblAmargor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmargor.setBounds(10, 207, 70, 20);
		contentPane_1.add(lblAmargor);

		JComboBox<Amargor> comboAmargor = new JComboBox<Amargor>();
		comboAmargor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboAmargor.setBounds(70, 206, 184, 22);
		contentPane_1.add(comboAmargor);

		try {
			AmargorBo abo = new AmargorBo();
			List<Amargor> list1 = abo.Consultar("");
			for (Amargor amargor : list1) {
				comboAmargor.addItem(amargor);
			}
		} catch (Exception e1) {
			System.out.println("Erro: " + e1.getMessage());
			e1.printStackTrace();
		}

		JComboBox<Pais> comboPais = new JComboBox<Pais>();
		comboPais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboPais.setBounds(338, 161, 175, 22);
		contentPane_1.add(comboPais);

		try {
			PaisBo pbo = new PaisBo();
			List<Pais> list1 = pbo.Consultar("");
			for (Pais pais : list1) {
				comboPais.addItem(pais);
			}
		} catch (Exception e1) {
			System.out.println("Erro: " + e1.getMessage());
			e1.printStackTrace();
		}

		JLabel lblPaís = new JLabel(" Pa\u00EDs:");
		lblPaís.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPaís.setBounds(305, 163, 46, 14);
		contentPane_1.add(lblPaís);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrar.setBounds(317, 380, 145, 20);
		contentPane_1.add(btnCadastrar);

		JComboBox<Coloracao> comboColoracao = new JComboBox<Coloracao>();
		comboColoracao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboColoracao.setBounds(368, 206, 145, 22);
		contentPane_1.add(comboColoracao);
		try {
			ColoracaoBo cbo = new ColoracaoBo();
			List<Coloracao> listaco = cbo.Consultar("");
			for (Coloracao coloracao : listaco) {
				comboColoracao.addItem(coloracao);
			}
		} catch (Exception e1) {
			System.out.println("Erro:" + e1.getMessage());
			e1.printStackTrace();
		}

		JLabel lblColoracao = new JLabel("   Colora\u00E7\u00E3o:");
		lblColoracao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblColoracao.setBounds(291, 208, 85, 18);
		contentPane_1.add(lblColoracao);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescricao.setBounds(25, 325, 65, 30);
		contentPane_1.add(lblDescricao);

		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(149, 11, 242, 98);
		contentPane_1.add(lblFoto);

		Cerveja cer = new Cerveja();

		JButton btnNewButton = new JButton("Adicionar Foto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser arquivo = new JFileChooser();
				arquivo.setDialogTitle("Selecione a imagem");
				arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int opc = arquivo.showOpenDialog(arquivo);
				if (opc == JFileChooser.APPROVE_OPTION) {
					File path = new File("Caminho");
					path = arquivo.getSelectedFile(); // recebe caminho
					String filename = path.getAbsolutePath();
					ImageIcon imagem = new ImageIcon(arquivo.getSelectedFile().getPath());
					lblFoto.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(120,
							80, Image.SCALE_DEFAULT)));
					cer.setPath(filename);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(10, 53, 129, 23);
		contentPane_1.add(btnNewButton);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Deseja voltar para o menu?");
				if (confirm == 0) {
					new TelaMenu(u);
					dispose();
				}
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(85, 380, 100, 20);
		contentPane_1.add(btnVoltar);

		JTextPane txtDescricao = new JTextPane();
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescricao.setBounds(85, 325, 428, 44);
		contentPane_1.add(txtDescricao);

		JButton btnAddEstilo = new JButton("+");
		btnAddEstilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroEstilo(u, comboEstilo);
			}
		});
		btnAddEstilo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddEstilo.setBounds(254, 161, 46, 22);
		contentPane_1.add(btnAddEstilo);

		JButton btnAddAmargor = new JButton("+");
		btnAddAmargor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroAmargor(u, comboAmargor);
			}
		});
		btnAddAmargor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddAmargor.setBounds(254, 206, 46, 21);
		contentPane_1.add(btnAddAmargor);

		JButton btnAddPais = new JButton("+");
		btnAddPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroPais(u, comboPais);
			}
		});
		btnAddPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddPais.setBounds(513, 161, 46, 22);
		contentPane_1.add(btnAddPais);

		JButton btnAddColoracao = new JButton("+");
		btnAddColoracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroColoracao(u, comboColoracao);
			}
		});
		btnAddColoracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddColoracao.setBounds(513, 206, 46, 23);
		contentPane_1.add(btnAddColoracao);

		Ingredientes = new JTable();
		Ingredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Ingredientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "NOME" }) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int lin, int col) {
				return false;
			}
		});

		Ingredientes.setBounds(70, 312, 145, -57);
		contentPane_1.add(Ingredientes);
		scroll = new JScrollPane(Ingredientes);
		scroll.setBounds(46, 238, 232, 83);
		scroll.setBackground(new Color(135, 206, 250));
		contentPane_1.add(scroll);
		JButton btnAddIngrediente = new JButton("+");

		btnAddIngrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new TelaCadastroIngrediente();

			}
		});

		pesquisarIngrediente();

		btnAddIngrediente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddIngrediente.setBounds(276, 237, 46, 25);
		contentPane_1.add(btnAddIngrediente);
		List<Ingrediente> ingselecionados = new ArrayList<>();
		JButton btnAdicionar = new JButton("Adicionar ingrediente na cerveja");
		btnAdicionar.addActionListener(new ActionListener() {
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
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdicionar.setBounds(317, 272, 217, 23);
		contentPane_1.add(btnAdicionar);

		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					Pais p = new Pais();
					Coloracao co = new Coloracao();
					Amargor a = new Amargor();
					EstiloCerveja ec = new EstiloCerveja();
					p = (Pais) comboPais.getSelectedItem();

					co = (Coloracao) comboColoracao.getSelectedItem();

					a = (Amargor) comboAmargor.getSelectedItem();

					ec = (EstiloCerveja) comboEstilo.getSelectedItem();

					if ("".equals(txtNomeCerveja.getText()) || "".equals(txtTeor.getText()) || comboPais == null
							|| comboAmargor == null || comboColoracao == null || comboEstilo == null
							|| "".equals(txtDescricao.getText()) || lblFoto.getIcon() == null) {
						JOptionPane.showMessageDialog(null, "Favor preecher todos os campos");
					} else if (Double.parseDouble(txtTeor.getText()) > 50.0
							|| Double.parseDouble(txtTeor.getText()) < 0) {
						JOptionPane.showMessageDialog(null,
								"O maximo de teor alcoolico permitido no aplicativo é 50% e o minimo é 0%");
					} else {

						cer.setNomeCerveja(txtNomeCerveja.getText().toUpperCase());
						cer.setDescricaoCerveja(txtDescricao.getText().toUpperCase());
						cer.setTeorAlcoolico(Double.parseDouble(txtTeor.getText()));
						cer.setAmargor(a);
						cer.setColoracao(co);
						cer.setPais(p);
						cer.setEstilo(ec);

						cer.setUsuario(u);
						cer.setStatus("ANALISANDO");
						CervejaBo cbo1 = new CervejaBo();
						boolean valida = cbo1.AutenticaCadastro(cer);
						if (valida == true) {
							cbo1.SalvarOuAlterar(cer);
						} else {
							JOptionPane.showMessageDialog(null, "Este nome de cerveja já está em uso");
						}
						Connection con = null;
						con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root",
								"vini030902");

						for (Ingrediente ingrediente : ingselecionados) {
							System.out.println(ingrediente.getIngrediente());
							String cSql = "insert into tb_cerveja_ingredientes (cerveja_id, ingrediente_id) "
									+ "values (?, ?);";
							PreparedStatement pstnt = con.prepareStatement(cSql);
							pstnt.setInt(1, cer.getId());
							pstnt.setInt(2, ingrediente.getId());
							pstnt.execute();

						}
						con.close();
						JOptionPane.showMessageDialog(null, "Cerveja " + cer.getNomeCerveja() + " cadastrada!");
						new TelaMenu(u);
						dispose();
					}
				} catch (Exception e1) {
					System.out.println("Erro: " + e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		this.setVisible(true);
	}

	private DefaultTableModel getModelo() {
		String[][] linhas = new String[][] { { "INGREDIENTE 1" } };
		String[] colunas = new String[] { "NOME" };
		new Color(135, 206, 250);
		return new DefaultTableModel(linhas, colunas);
	}

	private void pesquisarIngrediente() {
		// Carregar o model na JTable
		DefaultTableModel modelo = (DefaultTableModel) this.Ingredientes.getModel();
		modelo.setRowCount(0);
		Ingredientes.setModel(modelo);
		List<Ingrediente> ingrelist = new ArrayList<>();
		try {
			IngredienteBo ibo = new IngredienteBo();
			ingrelist = ibo.Consultar("");
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "ERRO: " + exc.getMessage());
		}
		try {

			for (Ingrediente cer : ingrelist) {
				modelo.addRow(

						new Object[] { cer.getIngrediente() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro consultando: " + e.getMessage());
		}
	}
}
