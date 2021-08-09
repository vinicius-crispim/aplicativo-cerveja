package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Bo.IngredienteBo;
import Classes.Ingrediente;

public class TelaCadastroIngrediente extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngrediente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroIngrediente frame = new TelaCadastroIngrediente();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroIngrediente() {
		setTitle("ANUNCIAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(135, 206, 250));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ingrediente:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(108, 142, 70, 18);
		contentPane.add(lblNewLabel);

		txtIngrediente = new JTextField();
		txtIngrediente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIngrediente.setBounds(177, 141, 174, 20);
		contentPane.add(txtIngrediente);
		txtIngrediente.setColumns(25);

		JButton btnCadastrarIngrediente = new JButton("Cadastrar Ingrediente");
		btnCadastrarIngrediente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastraIngrediente();
			}

			private void cadastraIngrediente() {
				if ("".equals(txtIngrediente.getText())) {
					JOptionPane.showMessageDialog(null, "Favor preencher o nome do ingrediente");
				} else {
					Ingrediente i = new Ingrediente();
					i.setIngrediente(txtIngrediente.getText().toUpperCase());
					try {
						IngredienteBo ibo = new IngredienteBo();
						boolean valida = ibo.AutenticaCadastro(i);
						if (valida == true) {

							ibo.SalvarOuAlterar(i);
						
							JOptionPane.showMessageDialog(null, "Ingrediente cadastrado com sucesso!!");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Erro: ingrediente ja cadastrado");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
						e1.printStackTrace();

					}

				}
			}
		});
		btnCadastrarIngrediente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrarIngrediente.setBounds(149, 309, 168, 23);
		contentPane.add(btnCadastrarIngrediente);
		this.setVisible(true);
	}
}
