package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import Controller.ControllerCargo;
import Controller.ControllerFuncionario;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;

public class CargosInserir extends JPanel {

	JLabel labelTitulo, labelNomeCargo, labelIdCargo;
	JTextField campoNomeCargo, campoIdCargo;
	JButton botaoGravar;
	ImageIcon imgSalvar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\save01.png");

	public CargosInserir() {
		criarComponentes();
		criarEventos();
	}

	private void criarComponentes() {

		setLayout(null);

		labelTitulo = new JLabel("Cadastro de Cargo", JLabel.CENTER);
		labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
		labelNomeCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		campoNomeCargo = new JTextField();
		labelIdCargo = new JLabel("Id do cargo", JLabel.LEFT);
		campoIdCargo = new JTextField();
		botaoGravar = new JButton("Adcionar", imgSalvar);
		campoIdCargo.setEnabled(false);
		campoIdCargo.setText("ID gerado automaticamente pelo banco de dados");

		labelTitulo.setBounds(20, 20, 660, 40);
		labelNomeCargo.setBounds(150, 120, 400, 20);
		campoNomeCargo.setBounds(150, 140, 400, 40);
		labelIdCargo.setBounds(150, 180, 400, 20);
		campoIdCargo.setBounds(150, 200, 400, 40);
		botaoGravar.setBounds(250, 380, 150, 40);

		//campoIdCargo.enable(false);;

		add(labelTitulo);
		add(labelNomeCargo);
		add(campoNomeCargo);
		add(campoIdCargo);
		add(labelIdCargo);
		add(botaoGravar);

		setVisible(true);
	}

	public void criarEventos() {
		botaoGravar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Cargo novoCargo = new Cargo();
				novoCargo.setCargoNome(campoNomeCargo.getText());
				// Validando campo
				if (campoNomeCargo.getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Preencha todos os campo", "Validação", JOptionPane.WARNING_MESSAGE);
				sqlInserirCargo(novoCargo);
			}
		});
	}

	private void sqlInserirCargo(Cargo novoCargo) {
		Conexao conexao = new Conexao();
		PreparedStatement preparedStatement = null;
		CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
		Cargo cargo = new Cargo();
		
		String nomeCargo = campoNomeCargo.getText();
		cargo.setCargoNome(nomeCargo);

		// Validando nome
		if(campoNomeCargo.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor inserir o nome completo");
			return;
		}

		// Versão nova - Modelo DAO
		ControllerCargo controllerCargo = new ControllerCargo();
		controllerCargo.inserirCargoController(cargo);

		/*
		try {
			// SQLite	
			String nomeCargo = campoNomeCargo.getText();
			cargo.setCargoNome(nomeCargo);
			String idCargo = campoIdCargo.getText();
			cargo.setCargoId(idCargo);

			conexao.conectar();
			String sqlInsert = "INSERT INTO T_CARGOS "
					+ "(nome) "
					+ "VALUES(?);";
			preparedStatement = conexao.criarPreparedStatement(sqlInsert);
			preparedStatement.setString(1, cargo.getCargoNome());

			int resultado = preparedStatement.executeUpdate();
			if (resultado == 1) {
				String  message = String.format("Cargo: %s\nCadastrado com sucesso", nomeCargo);
				JOptionPane.showMessageDialog(null, message, "Cadastro",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Cargo não inserido");
				return;
			}

			Navegador.inicio();
			conexao.desconectar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar", "ERRO", JOptionPane.ERROR_MESSAGE);
			Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
		} 
		 */
	}
}
