package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Bo.AdministradorBo;
import Classes.Administrador;

public class TelaAdmCadastra extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdmCadastra frame = new TelaAdmCadastra(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAdmCadastra(Administrador adm) {
		setTitle("Cadastrar Administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(135, 206, 250));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(106, 101, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setBounds(145, 98, 271, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(20);

		JLabel lblLogin = new JLabel(" Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setBounds(106, 152, 46, 20);
		contentPane.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogin.setBounds(145, 153, 271, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(20);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(106, 208, 46, 14);
		contentPane.add(lblSenha);

		txtSenha = new JTextField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSenha.setBounds(147, 206, 269, 20);
		contentPane.add(txtSenha);
		txtSenha.setColumns(20);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Administrador adm = new Administrador();
					adm.setNome(txtNome.getText().toUpperCase());
					adm.setLogin(txtLogin.getText().toUpperCase());
					adm.setSenha(txtSenha.getText().toUpperCase());
					AdministradorBo abo = new AdministradorBo();
					abo.SalvarOuAlterar(adm);
					JOptionPane.showMessageDialog(null, "Administrador cadastrado com sucesso");
					new TelaAdmMenu(adm);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro registrando administrador");
					e1.printStackTrace();
				}

			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrar.setBounds(199, 307, 139, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaAdmMenu(adm);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(30, 360, 89, 23);
		contentPane.add(btnVoltar);

		this.setVisible(true);
	}
}
