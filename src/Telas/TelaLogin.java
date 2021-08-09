package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Bo.AdministradorBo;
import Bo.UsuarioBo;
import Classes.Administrador;
import Classes.Usuario;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("ENTRAR");
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel(" Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setBounds(155, 106, 46, 18);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(194, 106, 193, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("  Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(145, 175, 56, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(194, 173, 193, 20);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnLogar = new JButton("Juntar-se a mesa");
		btnLogar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				efetuaLogin();
			}			
		
		});
		btnLogar.setBounds(209, 278, 138, 23);
		contentPane.add(btnLogar);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(44, 339, 89, 23);
		contentPane.add(btnNewButton);
		this.setVisible(true);

	}

	private void efetuaLogin() {
		
		AdministradorBo abo = new AdministradorBo();
		try {
			List<Administrador> admlist = abo.ValidaçãoSenha(txtLogin.getText(), txtSenha.getText());
			if (admlist.size() != 0) {
				Administrador adm = admlist.get(0);
				new TelaAdmMenu(adm);
				dispose();
				JOptionPane.showMessageDialog(null, "Login administrador efetuado");
			} else {
				UsuarioBo ubo = new UsuarioBo();
				Usuario u = new Usuario();
				u.setLogin(txtLogin.getText());
				u.setSenha(txtSenha.getText());
				Boolean autentica = ubo.Autenticar(u);
				if (autentica == true) {
					
					List<Usuario> lusuario = ubo.Consultar(u.getSenha(), u.getLogin());
					for (Usuario usuario2 : lusuario) {
						u = usuario2;
					}
					JOptionPane.showMessageDialog(null, "Login efetuado, seja bem vindo " + u.getNome());
					dispose();
					new TelaMenu(u);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos");
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
			e1.printStackTrace();
		}
	}

}
