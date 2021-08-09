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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Bo.AdministradorBo;
import Bo.CervejaBo;
import Classes.Administrador;
import Classes.Cerveja;
import Telas.TelaCervejaFavorita.RenderizaImagem;

import javax.swing.ListSelectionModel;

public class TelaAdmConsulta extends JFrame {

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
					TelaAdmConsulta frame = new TelaAdmConsulta(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAdmConsulta(Administrador u) {
		setTitle("EXPLORAR CERVEJAS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(135, 206, 250));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		AdministradorBo cpbo = new AdministradorBo();
		List<Cerveja> listc = new ArrayList<>();
		try {
			listc = cpbo.ConsultarPendentes();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro buscando cervejas pendentes " + e1.getMessage());
			e1.printStackTrace();
		}

		tblCervejas = new JTable();
		tblCervejas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCervejas.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "IMAGEM", "NOME", "ESTILO", "PAÍS" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int lin, int col) {
				return false;
			}
		});
		tblCervejas.getTableHeader().setReorderingAllowed(false);
		tblCervejas.setBackground(new Color(135, 206, 250));
		tblCervejas.setBounds(40, 121, 191, 112);
		contentPane.add(tblCervejas);
		scroll = new JScrollPane(tblCervejas);
		scroll.setBounds(10, 11, 554, 354);
		scroll.setBackground(new Color(135, 206, 250));
		contentPane.add(scroll);

		pesquisarGrupo(listc);

		JButton btnPostagem = new JButton("Ver Publica\u00E7\u00E3o");
		btnPostagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrePublicacao(u);
				dispose();
			}
		});
		btnPostagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPostagem.setBounds(195, 376, 125, 23);
		contentPane.add(btnPostagem);

		this.setVisible(true);

	}

	private DefaultTableModel getModelo() {
		String[][] linhas = new String[][] { { "IMAGEM 1", "CERVEJA 1", "PAIS 1", "ESTILO 1" } };
		String[] colunas = new String[] { "IMAGEM ", "NOME", "PAIS", "ESTILO" };
		new Color(135, 206, 250);
		return new DefaultTableModel(linhas, colunas);
	}

	private void pesquisarGrupo(List<Cerveja> clista) {
		DefaultTableModel modelo = (DefaultTableModel) this.tblCervejas.getModel();
		modelo.setRowCount(0);
		tblCervejas.setModel(modelo);

		try {

			for (Cerveja cer : clista) {
				modelo.addRow(new Object[] { cer.getPath(), cer.getNomeCerveja(), cer.getEstilo(),
						cer.getPais() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro consultando: " + e.getMessage());
		}
		TableColumn col = tblCervejas.getColumnModel().getColumn(0);
		col.setPreferredWidth(175);
		col = tblCervejas.getColumnModel().getColumn(1);
		col.setPreferredWidth(160);
		col = tblCervejas.getColumnModel().getColumn(2);
		col.setPreferredWidth(120);
		col = tblCervejas.getColumnModel().getColumn(3);
		col.setPreferredWidth(95);
		tblCervejas.getTableHeader().setReorderingAllowed(false);
		tblCervejas.setRowHeight(80);
		tblCervejas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblCervejas.getColumnModel().getColumn(0).setCellRenderer(new RenderizaImagem());
	}
	
	public class RenderizaImagem extends DefaultTableCellRenderer{

		public Component getTableCellRendererComponent(JTable table, Object c, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String foto = c.toString();
			ImageIcon imagem = new ImageIcon(new ImageIcon(foto).getImage().getScaledInstance(120, 80, Image.SCALE_DEFAULT));
			
			
			return new JLabel(imagem);
		}
		
	}

	private void abrePublicacao(Administrador u) {
		int linha = tblCervejas.getSelectedRow();
		String nome =(String)  tblCervejas.getValueAt(linha, 1);
		Cerveja c = new Cerveja();
		CervejaBo cbo = new CervejaBo();
		try {
			List<Cerveja> cpostagem = cbo.ConsultarPostagemADM(nome);
			for (Cerveja cerveja : cpostagem) {
				c= cerveja;
				new TelaAdmPostagem(u, c);
			}

		} catch (Exception e4) {
			JOptionPane.showMessageDialog(null, "Erro abrindo postagem: " + e4.getMessage());
			e4.printStackTrace();
		}
	}
}
