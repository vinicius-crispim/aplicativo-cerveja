package Telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Usuario;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaMenu extends JFrame {

	
	private JPanel contentPane;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu frame = new TelaMenu(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param u 
	 */
	public TelaMenu(Usuario u) {
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExplorar = new JButton("Explorar Cervejas");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaPesquisa(u);
			}
		});
		btnExplorar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExplorar.setBounds(196, 68, 146, 23);
		contentPane.add(btnExplorar);
		
		JButton btnCadastrar = new JButton("Cadastrar Cerveja");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroCerveja(u);
				
			}
		});
		
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrar.setBounds(196, 130, 146, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnFrigobar = new JButton("Meu Frigobar");
		btnFrigobar.setToolTipText("Clique aqui para consultar suas cervejas favoritas");
		btnFrigobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCervejaFavorita(u);
				dispose();
			}
		});
		btnFrigobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFrigobar.setBounds(196, 193, 146, 23);
		contentPane.add(btnFrigobar);
		
		JButton btnGeladas = new JButton("Minhas Geladas");
		btnGeladas.setToolTipText("Clique aqui para consultar as cervejas que voc\u00EA produziu");
		btnGeladas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCervejaPublicadas(u);
				dispose();
			}
		});
		
		btnGeladas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGeladas.setBounds(196, 258, 146, 23);
		contentPane.add(btnGeladas);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial();
				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSair.setBounds(196, 322, 145, 23);
		contentPane.add(btnSair);
		
		this.setVisible(true);
	}
}
