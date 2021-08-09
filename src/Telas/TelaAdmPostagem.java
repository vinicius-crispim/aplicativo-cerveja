package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Bo.CervejaBo;
import Bo.CervejaNotaBo;
import Bo.IngredienteBo;
import Classes.Administrador;
import Classes.Cerveja;
import Classes.CervejaNota;
import Classes.Ingrediente;
import javax.swing.JTextPane;

public class TelaAdmPostagem extends JFrame {

	private JPanel contentPane;
	private List<Ingrediente> listaingrediente;
	private JScrollPane scroll;
	private JTable Ingredientes;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdmPostagem frame = new TelaAdmPostagem(null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAdmPostagem(Administrador u, Cerveja c) {
		setTitle("Avaliar Cerveja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 209, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(10, 11, 173, 156);
		contentPane.add(lblFoto);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel lblNome = new JLabel("");
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(218, 30, 318, 20);
		lblNome.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblNome.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblNome);

		JLabel lblEstilo = new JLabel("");
		lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstilo.setBounds(218, 65, 318, 20);
		lblEstilo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblEstilo.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblEstilo);

		JLabel lblTeor = new JLabel("");
		lblTeor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeor.setBounds(218, 100, 123, 20);
		lblTeor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblTeor.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblTeor);

		JLabel lblPais = new JLabel("");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPais.setBounds(218, 135, 318, 20);
		lblPais.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPais.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblPais);

		JLabel lblProdutor = new JLabel("");
		lblProdutor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProdutor.setBounds(10, 178, 244, 20);
		lblProdutor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblProdutor.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblProdutor);

		JLabel lblAmargor = new JLabel("");
		lblAmargor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmargor.setBounds(299, 178, 237, 20);
		lblAmargor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblAmargor.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblAmargor);

		JLabel lblEmail = new JLabel("");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(15, 252, 318, 20);
		lblEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblEmail.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblEmail);

		JLabel lblColoracao = new JLabel("");
		lblColoracao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblColoracao.setBounds(10, 214, 244, 20);
		lblColoracao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblColoracao.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblColoracao);

		JLabel lblContato = new JLabel((String) null);
		lblContato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContato.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblContato.setBackground(Color.LIGHT_GRAY);
		lblContato.setBounds(299, 214, 237, 20);
		contentPane.add(lblContato);
		
		Ingredientes = new JTable();
		Ingredientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "INGREDIENTE" }) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int lin, int col) {
				return false;
			}
		});
		Ingredientes.setBounds(70, 312, 145, -57);
		contentPane.add(Ingredientes);
		scroll = new JScrollPane(Ingredientes);
		scroll.setBounds(15, 340, 252, 102);
		scroll.setBackground(new Color(135, 206, 250));
		contentPane.add(scroll);
		
		JButton btnReprovar = new JButton("Reprovar");
		btnReprovar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CervejaBo cpbo = new CervejaBo();
				try {
//					c.setStatus("REJEITADA");
//					cpbo.SalvarOuAlterar(c);
					cpbo.excluirCerveja(c);
					JOptionPane.showMessageDialog(null, "Cerveja rejeitada com sucesso!");
					dispose();
					new TelaAdmConsulta(u);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro rejeitando cerveja");
					e1.printStackTrace();
				}
			}
		});
		btnReprovar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReprovar.setBounds(272, 384, 101, 23);
		contentPane.add(btnReprovar);
		
		JButton btnAprovar = new JButton("Aprovar");
		btnAprovar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				validaCadastroCerveja(u, c);
			}
		});
		btnAprovar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAprovar.setBounds(427, 384, 101, 23);
		contentPane.add(btnAprovar);
		
		JTextPane txtDescricao = new JTextPane();
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDescricao.setEditable(false);
		txtDescricao.setBounds(10, 283, 526, 51);
		contentPane.add(txtDescricao);
		carregaPostagem(c,lblFoto, lblNome, lblEstilo, lblTeor,
			lblPais, lblProdutor, lblAmargor, lblEmail, txtDescricao,
			lblColoracao, lblContato);
		this.setVisible(true);

	}

	private void carregaPostagem(Cerveja c, JLabel lblFoto, JLabel lblNome, JLabel lblEstilo, JLabel lblTeor,
			JLabel lblPais, JLabel lblProdutor, JLabel lblAmargor, JLabel lblEmail, JTextPane txtDescricao,
			JLabel lblColoracao, JLabel lblContato) {
		try {
			pesquisarIngrediente(c);
			lblNome.setText("NOME: " + c.getNomeCerveja().toUpperCase());
			lblEstilo.setText("ESTILO: " + c.getEstilo().getEstilo().toUpperCase());

			lblTeor.setText("TEOR: " + Double.toString(c.getTeorAlcoolico()).toUpperCase());
			lblPais.setText("PAÍS: " + c.getPais().getNomePais().toUpperCase());
			lblAmargor.setText("AMARGOR: " + c.getAmargor().getAmargor().toUpperCase());
			lblColoracao.setText("COLORAÇÃO: " + c.getColoracao().getColoracao().toUpperCase());
			lblProdutor.setText("PRODUTOR: " + c.getUsuario().getNome().toUpperCase());
			File path = new File(c.getPath());
			JFileChooser arquivo = new JFileChooser();
			arquivo.setSelectedFile(path);
			ImageIcon imagem = new ImageIcon(arquivo.getSelectedFile().getPath());
			lblFoto.setIcon(new ImageIcon(
					imagem.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT)));
			lblContato.setText("TELEFONE: " + c.getUsuario().getTelefone());
			lblEmail.setText("EMAIL: " + c.getUsuario().getEmail().toUpperCase());
			txtDescricao.setText("Descrição: " + c.getDescricaoCerveja());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	private DefaultTableModel getModelo() {
		String[][] linhas = new String[][] { { "INGREDIENTE 1" } };
		String[] colunas = new String[] { "INGREDIENTE" };
		new Color(135, 206, 250);
		return new DefaultTableModel(linhas, colunas);
	}

	private void validaCadastroCerveja(Administrador u, Cerveja c) {
		try {
			CervejaBo cbo = new CervejaBo();
			c.setStatus("APROVADA");
			cbo.SalvarOuAlterar(c);
			CervejaNota cervejanota = new CervejaNota();
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root",
						"vini030902");
			cervejanota.setNusuario(0);
			cervejanota.setTotal(0.0);
			cervejanota.setNota(0.0);
			cervejanota.setMedia(0.0);
			cervejanota.setId(c.getId());
			cervejanota.setC(c);
			CervejaNotaBo cnbo = new CervejaNotaBo();
			cnbo.SalvarOuAlterar(cervejanota);
			
			String cSql = "insert into tb_usuario_nota (cerveja_id, cervejanota_id, usuario_id) "
					+ "values (?, ?,?);";
			PreparedStatement pstnt = con.prepareStatement(cSql);
			pstnt.setInt(1, c.getId());
			pstnt.setInt(2, cervejanota.getId());
			pstnt.setInt(3, c.getUsuario().getId());
			pstnt.close();
			con.close();
			JOptionPane.showMessageDialog(null, "Cerveja " + c.getNomeCerveja() + " cadastrada!");
			dispose();
			new TelaAdmConsulta(u);
			} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro aprovando cerveja: " +e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	private void pesquisarIngrediente(Cerveja c) {
		// Carregar o model na JTable
		DefaultTableModel modelo = (DefaultTableModel) this.Ingredientes.getModel();
		modelo.setRowCount(0);
		Ingredientes.setModel(modelo);
		Connection con;		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root", "vini030902");
			String cSql = "select * from tb_cerveja_ingredientes where cerveja_id = (?)";

			PreparedStatement pstnt = con.prepareStatement(cSql);
			pstnt.setInt(1, c.getId());

			ResultSet rs = pstnt.executeQuery();
			IngredienteBo cbo = new IngredienteBo();
			List<Ingrediente> list = new ArrayList<>();
			while (rs.next()) {
				Integer id_i = rs.getInt(2);
				List<Ingrediente> lcervejateste = cbo.ConsultarID(id_i);
				for (Ingrediente cerveja : lcervejateste) {
					list.add(cerveja);
				}

			}
			for (Ingrediente cer : list) {
				modelo.addRow(
						new Object[] { cer.getIngrediente()});
			}
			pstnt.execute();
			pstnt.close();
			rs.close();
			con.close();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "ERRO: " + exc.getMessage());
		}
	}
}
