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
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Bo.CervejaBo;
import Bo.CervejaNotaBo;
import Bo.IngredienteBo;
import Classes.Cerveja;
import Classes.CervejaNota;
import Classes.Ingrediente;
import Classes.Usuario;
import javax.swing.JTextPane;

public class TelaPostagem extends JFrame {

	private JPanel contentPane;
	private JTextField txtAvaliacao;
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
					TelaPostagem frame = new TelaPostagem(null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPostagem(Cerveja c, Usuario u) {
		setTitle("EXPLORAR CERVEJAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(72, 209, 204));
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

		JLabel lblNota = new JLabel("");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNota.setBounds(382, 252, 154, 20);
		lblNota.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblNota.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblNota);

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

		JCheckBox checkBoxFav = new JCheckBox("Favoritar Cerveja");
		checkBoxFav.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				favoritaCerveja(c, u);
			}
		});
		checkBoxFav.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBoxFav.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		checkBoxFav.setBounds(277, 355, 138, 23);
		contentPane.add(checkBoxFav);

		JTextPane txtDescricao = new JTextPane();
		txtDescricao.setEnabled(true);
		txtDescricao.setEditable(false);
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescricao.setBounds(71, 283, 465, 61);
		contentPane.add(txtDescricao);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescricao.setBackground(new Color(135, 206, 250));
		txtDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblDescricao.setBounds(10, 283, 60, 14);
		contentPane.add(lblDescricao);

		JLabel lblAvaliacao = new JLabel("Deixe sua nota:");
		lblAvaliacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvaliacao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblAvaliacao.setBounds(277, 406, 138, 23);
		contentPane.add(lblAvaliacao);

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
		scroll.setBounds(15, 353, 252, 89);
		scroll.setBackground(new Color(135, 206, 250));
		contentPane.add(scroll);

		txtAvaliacao = new JTextField();
		txtAvaliacao.setBounds(370, 408, 41, 20);
		contentPane.add(txtAvaliacao);
		txtAvaliacao.setColumns(10);

		JButton btnAvaliar = new JButton("Avaliar");
		btnAvaliar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaliar(c, u, lblNota);
			}
		});
		btnAvaliar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAvaliar.setBounds(412, 406, 89, 23);
		contentPane.add(btnAvaliar);
		
		carregaPostagem(c,lblFoto,lblNome,lblEstilo,lblTeor,lblPais,lblProdutor,lblAmargor,lblEmail,txtDescricao,
		lblColoracao,lblContato,lblNota);
		
		this.setVisible(true);

	}

	private void carregaPostagem(Cerveja c, JLabel lblFoto, JLabel lblNome, JLabel lblEstilo, JLabel lblTeor,
			JLabel lblPais, JLabel lblProdutor, JLabel lblAmargor, JLabel lblEmail, JTextPane txtDescricao,
			JLabel lblColoracao, JLabel lblContato, JLabel lblNota) {
		try {
			pesquisarIngrediente(c);
			lblNome.setText("NOME: " + c.getNomeCerveja().toUpperCase());
			lblEstilo.setText("ESTILO: " + c.getEstilo().getEstilo().toUpperCase());

			lblTeor.setText("TEOR: " + Double.toString(c.getTeorAlcoolico()).toUpperCase());
			lblPais.setText("PAÍS: " + c.getPais().getNomePais().toUpperCase());
			lblAmargor.setText("AMARGOR: " + c.getAmargor().getAmargor().toUpperCase());
			lblColoracao.setText("COLORAÇÃO: " + c.getColoracao().getColoracao().toUpperCase());
			lblProdutor.setText("PRODUTOR: " + c.getUsuario().getNome().toUpperCase());
			txtDescricao.setText(c.getDescricaoCerveja());
			File path = new File(c.getPath());
			JFileChooser arquivo = new JFileChooser();
			arquivo.setSelectedFile(path);
			ImageIcon imagem = new ImageIcon(arquivo.getSelectedFile().getPath());
			lblFoto.setIcon(new ImageIcon(
					imagem.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT)));
			lblContato.setText("TELEFONE: " + c.getUsuario().getTelefone());
			lblEmail.setText("EMAIL: " + c.getUsuario().getEmail().toUpperCase());
			CervejaNotaBo notabo = new CervejaNotaBo();
			List<CervejaNota> lnota = notabo.achaNota(c.getId());
			CervejaNota nota = lnota.get(0);
			if (nota.getMedia() == 0) {
				lblNota.setText("Cerveja não avaliada");
			} else {
				String media = new DecimalFormat("#.##").format(nota.getMedia());
				lblNota.setText("NOTA: " + media);
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	private Double calculamedia(double notatotal, int nusuario) {
		double media = notatotal / nusuario;
		return media;
	}

	private void favoritaCerveja(Cerveja c, Usuario u) {
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root", "vini030902");
			String cSql = "select * from tb_usuario_cerveja where usuario_id = (?) and cerveja_id = (?)";

			PreparedStatement pstnt = con.prepareStatement(cSql);
			pstnt.setInt(1, u.getId());
			pstnt.setInt(2, c.getId());

			ResultSet rs = pstnt.executeQuery();
			int numLinhas = 0;
			while (rs.next()) {
				numLinhas++;
			}
			if (numLinhas == 0) {
				System.out.println("Conectou favorito");

				cSql = "insert into tb_usuario_cerveja (usuario_id, cerveja_id) " + "values (?, ?);";
				pstnt = con.prepareStatement(cSql);
				pstnt.setInt(1, u.getId());
				pstnt.setInt(2, c.getId());
				pstnt.execute();
				JOptionPane.showMessageDialog(null, "Cerveja favoritada");
			} else {
				JOptionPane.showMessageDialog(null, "Voce ja favoritou essa cerveja");
			}

			pstnt.close();
			con.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private DefaultTableModel getModelo() {
		String[][] linhas = new String[][] { { "INGREDIENTE 1" } };
		String[] colunas = new String[] { "INGREDIENTE" };
		new Color(135, 206, 250);
		return new DefaultTableModel(linhas, colunas);
	}

	private void pesquisarIngrediente(Cerveja c) {
		// Carregar o model na JTable
		DefaultTableModel modelo = (DefaultTableModel) this.Ingredientes.getModel();
		modelo.setRowCount(0);
		Ingredientes.setModel(modelo);
		List<Ingrediente> ingrelist = new ArrayList<>();
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
				modelo.addRow(new Object[] { cer.getIngrediente() });
			}
			pstnt.execute();
			pstnt.close();
			rs.close();
			con.close();
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

	private void avaliar(Cerveja c, Usuario u, JLabel lblNota) {
		if (Double.parseDouble(txtAvaliacao.getText()) > 5 || Double.parseDouble(txtAvaliacao.getText()) < 1) {
			JOptionPane.showMessageDialog(null, "A nota minima que pode ser dada é 1 e a máxima é 5!");
		} else {
			try {
				CervejaNotaBo notabo = new CervejaNotaBo();

				List<CervejaNota> listnota = notabo.achaNota(c.getId());
				CervejaNota nota = listnota.get(0);
				Connection con = null;
				con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root", "vini030902");
				String cSql = "select * from tb_usuario_nota where usuario_id = (?) and cervejanota_id = (?)";

				PreparedStatement pstnt = con.prepareStatement(cSql);
				pstnt.setInt(1, u.getId());
				pstnt.setInt(2, nota.getId());

				ResultSet rs = pstnt.executeQuery();
				int numLinhas = 0;
				while (rs.next()) {
					numLinhas++;
				}
				if (numLinhas == 0) {
					System.out.println("Conectou nota");

					nota.setNusuario(nota.getNusuario() + 1);
					nota.setTotal(nota.getTotal() + Double.parseDouble(txtAvaliacao.getText()));
					nota.setMedia(calculamedia(nota.getTotal(), nota.getNusuario()));
					notabo.SalvarOuAlterar(nota);

					cSql = "insert into tb_usuario_nota (usuario_id, cervejanota_id,cerveja_id) " + "values (?, ?, ?);";
					pstnt = con.prepareStatement(cSql);
					pstnt.setInt(1, u.getId());
					pstnt.setInt(2, nota.getId());
					pstnt.setInt(3, c.getId());
					pstnt.execute();
					JOptionPane.showMessageDialog(null, "Nota efetuada");
					String media = new DecimalFormat("#.##").format(nota.getMedia());
					lblNota.setText("NOTA: " + media);
				} else {
					JOptionPane.showMessageDialog(null, "Voce ja deu uma nota para essa cerveja");
				}

				pstnt.close();
				con.close();

			} catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage());
			}
		}
	}
}
