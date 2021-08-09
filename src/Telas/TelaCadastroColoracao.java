package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Bo.ColoracaoBo;
import Classes.Coloracao;
import Classes.Usuario;

public class TelaCadastroColoracao extends JFrame {

	private JPanel contentPane;
	private JTextField txtColoracao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroColoracao frame = new TelaCadastroColoracao(null,null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroColoracao(Usuario u, JComboBox<Coloracao> combo) {
		setBackground(new Color(0, 255, 255));
		setTitle("ANUNCIAR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblColoracao = new JLabel("Coloracao:");
		lblColoracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblColoracao.setBounds(82, 135, 73, 17);
		contentPane.add(lblColoracao);

		txtColoracao = new JTextField();
		txtColoracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtColoracao.setBounds(150, 133, 174, 20);
		contentPane.add(txtColoracao);
		txtColoracao.setColumns(40);

		JButton btnCadastrarColoracao = new JButton("Cadastrar Coloracao");
		btnCadastrarColoracao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastraColoracao(combo);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnCadastrarColoracao.setBackground(Color.LIGHT_GRAY);
				btnCadastrarColoracao.getFocusCycleRootAncestor();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCadastrarColoracao.setBackground(Color.WHITE);
			}

		});
		btnCadastrarColoracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarColoracao.setBounds(206, 306, 174, 25);
		contentPane.add(btnCadastrarColoracao);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroCerveja(u);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(55, 308, 100, 23);
		contentPane.add(btnVoltar);
		this.setVisible(true);

	}

	private void cadastraColoracao(JComboBox<Coloracao> combo) {
		Coloracao p = new Coloracao();
		p.setColoracao(txtColoracao.getText().toUpperCase());
		if ("".equals(txtColoracao.getText())) {
			JOptionPane.showMessageDialog(null, "Favor preencher o nome do país");
		} else {
			ColoracaoBo pbo = new ColoracaoBo();
			try {
				boolean valida = pbo.AutenticaCadastro(p);
				if (valida == true) {
					pbo.SalvarOuAlterar(p);
					combo.addItem(p);
					JOptionPane.showMessageDialog(null, "Coloracao cadastrado com sucesso!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Coloracao ja cadastrado!");
				}
			} catch (Exception e1) {
				System.out.println("Erro ao gravar coloracao: " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
}
