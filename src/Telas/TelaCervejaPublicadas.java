package Telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Bo.CervejaNotaBo;
import Classes.Cerveja;
import Classes.CervejaNota;
import Classes.Usuario;

public class TelaCervejaPublicadas extends JFrame {

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
					TelaCervejaPublicadas frame = new TelaCervejaPublicadas(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCervejaPublicadas(Usuario u) {
		setTitle("EXPLORAR CERVEJAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(135, 206, 250));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tblCervejas = new JTable();
		tblCervejas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCervejas.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "IMAGEM", "NOME", "NOTA", "ESTILO" }) {
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
		scroll.setBounds(10, 11, 554, 344);
		scroll.setBackground(new Color(135, 206, 250));
		contentPane.add(scroll);

		pesquisarCerveja(u.getId());

		JButton btnPostagem = new JButton("Ver Publica\u00E7\u00E3o");
		btnPostagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrePublicacao(u);

			}
		});
		btnPostagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPostagem.setBounds(397, 366, 125, 23);
		contentPane.add(btnPostagem);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMenu(u);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(59, 366, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tblCervejas.getSelectedRow();
				String nome = (String) tblCervejas.getValueAt(linha, 1);
				CervejaBo cbo = new CervejaBo();
				try {
				Cerveja c = cbo.ConsultarPubli(nome).get(0);
				cbo.excluirCerveja(c);
				JOptionPane.showMessageDialog(null, "Cerveja excluida com sucesso");
				dispose();
				new TelaCervejaPublicadas(u);
				}catch(Exception e4) {
					JOptionPane.showMessageDialog(null, "Erro excluindo cerveja");
					e4.getStackTrace();
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExcluir.setBounds(232, 366, 89, 23);
		contentPane.add(btnExcluir);

		this.setVisible(true);

	}

	private DefaultTableModel getModelo() {
		String[][] linhas = new String[][] { { "CERVEJA 1", "PAIS 1", "ESTILO 1", "TEOR% 1" } };
		String[] colunas = new String[] { "NOME", "PAIS", "ESTILO", "TEOR" };
		new Color(135, 206, 250);
		return new DefaultTableModel(linhas, colunas);
	}

	private void pesquisarCerveja(Integer id) {
		// Carregar o model na JTable
		DefaultTableModel modelo = (DefaultTableModel) this.tblCervejas.getModel();
		modelo.setRowCount(0);
		tblCervejas.setModel(modelo);		
		CervejaBo cbo = new CervejaBo();
		List<Cerveja> clista = new ArrayList<Cerveja>();
		try {
			clista = cbo.ConsultarCervejasUsuario(id);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar cervejas: " + e1.getMessage());
			e1.printStackTrace();
		}
		try {
			CervejaNotaBo notabo = new CervejaNotaBo();
			List<CervejaNota> lnota = new ArrayList<>();
			for (Cerveja cer : clista) {
				lnota = notabo.achaNota(cer.getId());
				CervejaNota nota = lnota.get(0);
				modelo.addRow(
						new Object[] { cer.getPath(),cer.getNomeCerveja(),nota.getMedia(),cer.getEstilo().getEstilo()});
			}
			TableColumn col = tblCervejas.getColumnModel().getColumn(0);
			col.setPreferredWidth(170);
			col = tblCervejas.getColumnModel().getColumn(1);
			col.setPreferredWidth(160);
			col = tblCervejas.getColumnModel().getColumn(2);
			col.setPreferredWidth(110);
			col = tblCervejas.getColumnModel().getColumn(3);
			col.setPreferredWidth(95);
			tblCervejas.getTableHeader().setReorderingAllowed(false);
			tblCervejas.setRowHeight(80);
			tblCervejas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tblCervejas.getColumnModel().getColumn(0).setCellRenderer(new RenderizaImagem());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro consultando: " + e.getMessage());
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
				new TelaPostagem(c,u);
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
