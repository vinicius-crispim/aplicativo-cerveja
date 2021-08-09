package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Administrador;

public class TelaAdmMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdmMenu frame = new TelaAdmMenu(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAdmMenu(Administrador u) {
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnExplorar = new JButton("Avaliar Cervejas");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaAdmConsulta(u);
			}
		});
		btnExplorar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExplorar.setBounds(182, 94, 173, 23);
		contentPane.add(btnExplorar);

		JButton btnCadastrar = new JButton("Cadastrar Administrador");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaAdmCadastra(u);
			}
		});

		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrar.setBounds(182, 180, 173, 23);
		contentPane.add(btnCadastrar);

		JButton btnFrigobar = new JButton("Sair");
		btnFrigobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		btnFrigobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFrigobar.setBounds(182, 260, 173, 23);
		contentPane.add(btnFrigobar);

		this.setVisible(true);
	}

}
