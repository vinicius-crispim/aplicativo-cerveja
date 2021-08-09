package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.FileSystemAlreadyExistsException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Bo.UsuarioBo;
import Classes.Usuario;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JTextField txtEmail;
	private JLabel lblLogin;
	private JTextField txtLogin;

	private JLabel lblCPF;
	private JTextField txtTelefone;
	private JTextField txtCPF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setFont(new Font("Arial", Font.ITALIC, 14));
		setTitle("TORNE-SE UM CERVEJEIRO");
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setBackground(new Color(192, 192, 192));
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuario.setBounds(129, 40, 74, 20);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUsuario.setBounds(177, 40, 180, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(25);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(135, 120, 41, 14);
		contentPane.add(lblSenha);

		txtSenha = new JTextField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSenha.setColumns(25);
		txtSenha.setBounds(177, 120, 180, 20);
		contentPane.add(txtSenha);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(140, 240, 36, 14);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setBounds(177, 240, 244, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(35);

		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIdade.setBounds(140, 280, 41, 14);
		contentPane.add(lblIdade);

		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setBounds(140, 80, 41, 20);
		contentPane.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogin.setBounds(177, 80, 180, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(25);

		JLabel lblTelefone = new JLabel(" Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(118, 160, 85, 18);
		contentPane.add(lblTelefone);

		try {
			MaskFormatter f = new MaskFormatter("(##) #####-####");
			txtTelefone = new JFormattedTextField(f);
			txtTelefone.setBounds(177, 160, 180, 20);
			contentPane.add(txtTelefone);
			txtTelefone.setColumns(15);
		} catch (Exception r) {
			JOptionPane.showMessageDialog(null, r.getMessage());
		}

		try {

			MaskFormatter f = new MaskFormatter("###.###.###-##");

			txtCPF = new JFormattedTextField(f);
			txtCPF.setBounds(177, 198, 180, 20);
			contentPane.add(txtCPF);
			txtCPF.setColumns(14);
		} catch (Exception r) {
			JOptionPane.showMessageDialog(null, r.getMessage());
		}

		lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCPF.setBounds(149, 200, 27, 14);
		contentPane.add(lblCPF);

		JComboBox<Integer> comboIdade = new JComboBox<Integer>();
		comboIdade.setForeground(Color.GRAY);
		comboIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboIdade.setMaximumRowCount(5);
		comboIdade.setBounds(177, 277, 51, 22);
		contentPane.add(comboIdade);

		for (int n = 18; n <= 100; n++) {
			comboIdade.addItem(n);
		}

		JButton btnCadastrarConta = new JButton("Cadastrar");
		btnCadastrarConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean validaemail = VerificaEmail(txtEmail.getText());
				if(validaemail == true) {
					cadastraUsuario(comboIdade);
				}else {
					JOptionPane.showMessageDialog(null, "É necessário colocar um @ no email");
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnCadastrarConta.getFocusCycleRootAncestor();
			}
		});

		btnCadastrarConta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarConta.setBounds(211, 320, 113, 23);
		contentPane.add(btnCadastrarConta);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaInicial();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(10, 321, 99, 23);
		contentPane.add(btnVoltar);
		this.setVisible(true);
	}

	boolean VerificaEmail (String email){
		int contagemarroba=0;
		String arroba = "@";
		char arrobachar = arroba.charAt(0);
		for (int i=0;i<email.length();i++) {
			if(arrobachar == email.charAt(i)) {
				contagemarroba++;
			}
		}
		if(contagemarroba > 1 || contagemarroba ==0 ) {
			return false;
		}else {
			return true;
		}
		
	}

	private void cadastraUsuario(JComboBox<Integer> comboIdade) {
		Usuario u = new Usuario();
		u.setNome(txtUsuario.getText());
		u.setLogin(txtLogin.getText());
		u.setSenha(txtSenha.getText());
		u.setEmail(txtEmail.getText());
		u.setTelefone(txtTelefone.getText());
		u.setCPF(txtCPF.getText());
		u.setIdade(Integer.parseInt(comboIdade.getSelectedItem().toString()));

		try {

			if ("".equals(u.getCPF()) || "".equals(u.getEmail()) || "".equals(u.getLogin()) || "".equals(u.getNome())
					|| "".equals(u.getSenha()) || "".equals(u.getTelefone())) {
				JOptionPane.showMessageDialog(null, "Favor preencher todos os campos");
			} else {

				UsuarioBo ubo = new UsuarioBo();
				String valida = ubo.AutenticaCadastro(u);
				if (valida.equals("APROVADO")) {
					ubo.SalvarOuAlterar(u);
					JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!!");
					dispose();
					new TelaInicial();
				} else {
					JOptionPane.showMessageDialog(null, "Usuario ja existente! " + valida + " ja esta sendo utilizada");
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuario!!" + e1.getMessage());
			e1.printStackTrace();
		}
	}
}
