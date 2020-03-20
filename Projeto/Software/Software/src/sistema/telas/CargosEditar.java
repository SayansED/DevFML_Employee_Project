package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sqlite.Conexao;

public class CargosEditar extends JPanel {

	Cargo cargoAtual = new Cargo();
	JLabel lblTitle, lblCargo;
	JTextField txtCargo;
	JButton btnSave;
	ImageIcon imgIconSobrescrever = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\overwrite01.png");

	public CargosEditar(Cargo cargo){
		cargoAtual = cargo;
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	private void criarComponentes() {
		setLayout(null);

		lblTitle = new JLabel("Editar de Cargo", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));      
		lblCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		txtCargo = new JTextField();
		btnSave = new JButton("Salvar", imgIconSobrescrever);


		lblTitle.setBounds(20, 20, 660, 40);
		lblCargo.setBounds(150, 120, 400, 20);
		txtCargo.setBounds(150, 140, 400, 40);
		btnSave.setBounds(250, 380, 150, 40); 

		add(lblTitle);
		add(lblCargo);
		add(txtCargo);
		add(btnSave);

		setVisible(true);
	}

	private void criarEventos() {
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validando nome e campo
				if(txtCargo.getText().isEmpty() || txtCargo.getText().length() <= 3) {
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				sqlAtualizarCargo();         
			}
		});
	}

	private void sqlAtualizarCargo() {  
		// conexão
		Conexao conexao = new Conexao();
		// instrucao SQL
		Statement statement;
		PreparedStatement preparedStatement;
		// resultados
		int resultado;

		try {
			// conectando ao banco de dados
			conexao.conectar();
			// criando a instrução SQL
			String srt = txtCargo.getText();
			String alterando = JOptionPane.showInputDialog("Digite o novo nome para ser inserido");
			String sqlUpdate = "UPDATE T_CARGOS SET nome = ? WHERE nome = ?;"; // Codigo de atualização
			preparedStatement = conexao.criarPreparedStatement(sqlUpdate);
			preparedStatement.setString(1, alterando);
			preparedStatement.setString(2, srt);
			resultado = preparedStatement.executeUpdate(); // Executando o UPDATE
			if(resultado==0) {
				JOptionPane.showMessageDialog(null, "Cargo não encontrado: " + srt, "Mensagem", JOptionPane.INFORMATION_MESSAGE);;
				return;
			}
			JOptionPane.showMessageDialog(null, "Cargo alterado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
			Navegador.inicio();
			conexao.desconectar();

		} catch (SQLException ew) {
			ew.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}
}
