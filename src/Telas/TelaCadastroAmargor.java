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

import Bo.AmargorBo;
import Classes.Amargor;
import Classes.Usuario;

public class TelaCadastroAmargor extends JFrame {
	private JPanel contentPane;
	private JTextField txtAmargor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroAmargor frame = new TelaCadastroAmargor(null,null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroAmargor(Usuario u,JComboBox<Amargor> combo) {
		setBackground(new Color(0, 255, 255));
		setTitle("ANUNCIAR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAmargor = new JLabel("Amargor:");
		lblAmargor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmargor.setBounds(92, 136, 58, 17);
		contentPane.add(lblAmargor);

		txtAmargor = new JTextField();
		txtAmargor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAmargor.setBounds(150, 132, 240, 25);
		contentPane.add(txtAmargor);
		txtAmargor.setColumns(40);

		JButton btnCadastrarPais = new JButton("Cadastrar Amargor");
		btnCadastrarPais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastraAmargor(combo);
			}

		});
		btnCadastrarPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarPais.setBounds(228, 310, 158, 25);
		contentPane.add(btnCadastrarPais);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroCerveja(u);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(65, 311, 115, 25);
		contentPane.add(btnVoltar);
		this.setVisible(true);

	}

	private void cadastraAmargor(JComboBox<Amargor> combo) {
		String nome = txtAmargor.getText();
		if ("".equals(txtAmargor.getText())) {
			JOptionPane.showMessageDialog(null, "Favor preecher o amargor");
		} else {
			Amargor p = new Amargor();
			p.setAmargor(nome.toUpperCase());

			AmargorBo pbo = new AmargorBo();
			try {
				boolean valida = pbo.AutenticaCadastro(p);
				if (valida == true) {
					pbo.SalvarOuAlterar(p);
					combo.addItem(p);
					JOptionPane.showMessageDialog(null, "Amargor cadastrado com sucesso!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Amargor ja cadastrado!");
				}
			} catch (Exception e1) {
				System.out.println("Erro ao gravar amargor: " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
}
