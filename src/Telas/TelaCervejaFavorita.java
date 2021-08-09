package Telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Bo.CervejaBo;
import Bo.PaisBo;
import Classes.Cerveja;
import Classes.Pais;
import Classes.Usuario;

public class TelaCervejaFavorita extends JFrame {

	private JPanel contentPane;
	private JTable tblCervejas;
	private JScrollPane scroll;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCervejaFavorita frame = new TelaCervejaFavorita(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCervejaFavorita(Usuario u) {
		setTitle("EXPLORAR CERVEJAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(135, 206, 250));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tblCervejas = new JTable();
		tblCervejas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCervejas
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "NOME", "PAÍS", "ESTILO", "TEOR" }) {
					private static final long serialVersionUID = 1L;
					@Override
					public boolean isCellEditable(int lin, int col) {
						return false;
					}
				});
		tblCervejas.setBackground(new Color(135, 206, 250));
		tblCervejas.setBounds(40, 121, 191, 112);
		contentPane.add(tblCervejas);
		scroll = new JScrollPane(tblCervejas);
		scroll.setBounds(10, 11, 554, 339);
		scroll.setBackground(new Color(135, 206, 250));
		contentPane.add(scroll);

		pesquisarGrupo(u.getId());

		JButton btnPostagem = new JButton("Ver Publica\u00E7\u00E3o");
		btnPostagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrePublicacao(u);
			}
		});
		btnPostagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPostagem.setBounds(386, 361, 125, 23);
		contentPane.add(btnPostagem);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMenu(u);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(56, 361, 89, 23);
		contentPane.add(btnVoltar);

		JButton btnExcluir = new JButton("Remover dos favoritos");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluiFavorito(u);
							
			}

			private void excluiFavorito(Usuario u) {
				int ctz = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja remover esta cerveja do seus favoritos?");
				if (ctz == 0) {
				int linha = tblCervejas.getSelectedRow();
				String nome = (String) tblCervejas.getValueAt(linha, 1);
				Cerveja c = new Cerveja();
				
				try {
		
					CervejaBo cbo = new CervejaBo();
					List<Cerveja> cpostagem = cbo.ConsultarPubli(nome);
					for (Cerveja cerveja : cpostagem) {
						c = cerveja;
					}
					Connection con;
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root",
							"vini030902");

					System.out.println("Conectou tela favoritas");
					String cSql = "delete from tb_usuario_cerveja where usuario_id = (?) and cerveja_id = (?)";
					PreparedStatement pstnt = con.prepareStatement(cSql);
					pstnt.setInt(1, u.getId());
					pstnt.setInt(2, c.getId());
					pstnt.execute();
					JOptionPane.showMessageDialog(null, "Cerveja removida dos favoritos");
					new TelaCervejaFavorita(u);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExcluir.setBounds(176, 361, 175, 23);
		contentPane.add(btnExcluir);

		this.setVisible(true);

	}

	private DefaultTableModel getModelo() {
		String[][] linhas = new String[][] { {"CERVEJA 1", "PAIS 1", "ESTILO 1", "TEOR% 1" } };
		String[] colunas = new String[] { "NOME", "PAIS", "ESTILO", "TEOR" };
		new Color(135, 206, 250);
		return new DefaultTableModel(linhas, colunas);
	}

	private void pesquisarGrupo(Integer id) {
		// Carregar o model na JTable
		DefaultTableModel modelo = (DefaultTableModel) this.tblCervejas.getModel();
		
		modelo.setRowCount(0);
		Connection con = null;
		List<Cerveja> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador", "root", "vini030902");
			System.out.println("Conectou tela favoritas");
			String cSql = "select * from tb_usuario_cerveja where usuario_id = (?)";

			PreparedStatement pstnt = con.prepareStatement(cSql);
			pstnt.setInt(1, id);

			ResultSet rs = pstnt.executeQuery();
			CervejaBo cbo = new CervejaBo();
			while (rs.next()) {
				Integer id_c = rs.getInt(2);
				List<Cerveja> lcervejateste = cbo.ConsultarFavoritas(id_c);
				for (Cerveja cerveja : lcervejateste) {
					list.add(cerveja);
				}

			}
			for (Cerveja cer : list) {
				modelo.addRow(
						new Object[] {cer.getPath(),cer.getNomeCerveja(),cer.getEstilo().getEstilo(),cer.getTeorAlcoolico() });
			}
			tblCervejas.setModel(modelo);
			  TableColumn col = tblCervejas.getColumnModel().getColumn(0);
			  //554
			    col.setPreferredWidth(185);
			    col = tblCervejas.getColumnModel().getColumn(1);
			    col.setPreferredWidth(160);
			    col = tblCervejas.getColumnModel().getColumn(2);
			    col.setPreferredWidth(120);
			    col = tblCervejas.getColumnModel().getColumn(3);
			    col.setPreferredWidth(70);
			tblCervejas.getTableHeader().setReorderingAllowed(false);
			tblCervejas.setRowHeight(80);
			tblCervejas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tblCervejas.getColumnModel().getColumn(0).setCellRenderer(new RenderizaImagem());
			pstnt.execute();
			pstnt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}
	}

	private void abrePublicacao(Usuario u) {
		int linha = tblCervejas.getSelectedRow();
		String nome = (String) tblCervejas.getValueAt(linha, 1);
		Cerveja c = new Cerveja();

		CervejaBo cbo = new CervejaBo();
		try {
			List<Cerveja> cpostagem = cbo.ConsultarPubli(nome);
			for (Cerveja time : cpostagem) {
				c = time;
				new TelaPostagem(c, u);
			}
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
	}
	
	public class RenderizaImagem extends DefaultTableCellRenderer{

		public Component getTableCellRendererComponent(JTable table, Object c, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String foto = c.toString();
			ImageIcon imagem = new ImageIcon(new ImageIcon(foto).getImage().getScaledInstance(120, 80, Image.SCALE_DEFAULT));
			
			
			return new JLabel(imagem);
		}
		
	}
	
}
