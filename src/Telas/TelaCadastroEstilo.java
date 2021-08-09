package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Bo.EstiloCervejaBo;
import Classes.Coloracao;
import Classes.EstiloCerveja;
import Classes.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroEstilo extends JFrame {

	private JPanel contentPane;
	private JTextField txtPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEstilo frame = new TelaCadastroEstilo(null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroEstilo(Usuario u, JComboBox<EstiloCerveja> estilocombo) {
		setBackground(new Color(0, 255, 255));
		setTitle("ANUNCIAR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPais = new JLabel("Estilo:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPais.setBounds(114, 136, 46, 14);
		contentPane.add(lblPais);

		txtPais = new JTextField();
		txtPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPais.setBounds(150, 133, 158, 20);
		contentPane.add(txtPais);
		txtPais.setColumns(40);

		JButton btnCadastrarPais = new JButton("Cadastrar Estilo");
		btnCadastrarPais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastraEstilo(estilocombo);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnCadastrarPais.setBackground(Color.LIGHT_GRAY);
				btnCadastrarPais.getFocusCycleRootAncestor();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCadastrarPais.setBackground(Color.WHITE);
			}

		});
		btnCadastrarPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarPais.setBounds(226, 308, 146, 25);
		contentPane.add(btnCadastrarPais);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroCerveja(u);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(61, 314, 89, 20);
		contentPane.add(btnVoltar);
		
		this.setVisible(true);
	}

	private void cadastraEstilo(JComboBox<EstiloCerveja> estilocombo) {
		EstiloCerveja p = new EstiloCerveja();
		p.setEstilo(txtPais.getText().toUpperCase());
		if ("".equals(txtPais.getText())) {
			JOptionPane.showMessageDialog(null, "Favor preencher o nome do estilo");
		} else {
			EstiloCervejaBo pbo = new EstiloCervejaBo();
			try {
				boolean valida = pbo.AutenticaCadastro(p);
				if (valida == true) {
					pbo.SalvarOuAlterar(p);
					estilocombo.addItem(p);
					JOptionPane.showMessageDialog(null, "Estilo cadastrado com sucesso!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Estilo ja cadastrado!");
				}
			} catch (Exception e1) {
				System.out.println("Erro ao gravar estilo: " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
}
