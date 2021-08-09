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

import Bo.PaisBo;
import Classes.Pais;
import Classes.Usuario;

public class TelaCadastroPais extends JFrame {

	private JPanel contentPane;
	private JTextField txtPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPais frame = new TelaCadastroPais(null,null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroPais(Usuario u, JComboBox<Pais> combo) {
		setBackground(new Color(0, 255, 255));
		setTitle("ANUNCIAR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPais.setBounds(122, 136, 46, 14);
		contentPane.add(lblPais);

		txtPais = new JTextField();
		txtPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPais.setBounds(150, 133, 158, 20);
		contentPane.add(txtPais);
		txtPais.setColumns(40);

		JButton btnCadastrarPais = new JButton("Cadastrar Pais");
		btnCadastrarPais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastraPais(combo);
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
		btnCadastrarPais.setBounds(216, 309, 146, 25);
		contentPane.add(btnCadastrarPais);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroCerveja(u);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(79, 309, 89, 26);
		contentPane.add(btnVoltar);
		this.setVisible(true);

	}

	private void cadastraPais(JComboBox<Pais> combo) {
		String nome = txtPais.getText();
		Pais p = new Pais();
		p.setNomePais(nome.toUpperCase());
		if ("".equals(nome)) {
			JOptionPane.showMessageDialog(null, "Favor preencher o nome do país");
		} else {
			PaisBo pbo = new PaisBo();
			try {
				boolean valida = pbo.AutenticaCadastro(p);
				if (valida == true) {
					pbo.SalvarOuAlterar(p);
					combo.addItem(p);
					JOptionPane.showMessageDialog(null, "Pais cadastrado com sucesso!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Pais ja cadastrado!");
				}
			} catch (Exception e1) {
				System.out.println("Erro ao gravar pais: " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
}
