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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sqlite.Conexao;
import sistema.Navegador;
import sistema.entidades.Funcionario;

public class FuncionariosConsultar extends JPanel {

	JLabel lblTitulo, lblFuncionario;
	JTextField txtFuncionario;
	JButton btnPesquisar, btnEditar, btnExcluir;
	DefaultListModel<Funcionario> listaFuncionariosModelo =  new DefaultListModel();
	//JList<Funcionario> listaFuncionarios;
	ImageIcon imgPesquisar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\search03.png");
	ImageIcon imgDeletar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgEditar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png"); 

	public FuncionariosConsultar(){
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	public void criarComponentes() {
		setLayout(null);

		lblTitulo = new JLabel("Consulta de Funcion�rio", JLabel.CENTER);
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.PLAIN, 20));
		lblFuncionario = new JLabel("Nome do Funcion�rio", JLabel.LEFT);
		txtFuncionario = new JTextField();
		btnPesquisar = new JButton("Pesquisar Funcion�rio", imgPesquisar);
		btnEditar = new JButton("Editar Funcion�rio", imgEditar);
		//botaoEditar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir = new JButton("Excluir Funcion�rio", imgDeletar);
		//botaoExcluir.setEnabled(false);
		btnExcluir.setEnabled(true);
		listaFuncionariosModelo = new DefaultListModel();
		/*
		listaFuncionarios = new JList();
		listaFuncionarios.setModel(listaFuncionariosModelo);
		listaFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		*/

		lblTitulo.setBounds(20, 20, 660, 40);
		lblFuncionario.setBounds(150, 120, 400, 20);
		txtFuncionario.setBounds(150, 140, 400, 40);
		btnPesquisar.setBounds(560, 140, 200, 40);
		//listaFuncionarios.setBounds(150, 200, 400, 240);
		btnEditar.setBounds(560, 360, 200, 40);
		btnExcluir.setBounds(560, 400, 200, 40);

		add(lblTitulo);
		add(lblFuncionario);
		add(txtFuncionario);
		//add(listaFuncionarios);
		add(btnPesquisar);
		add(btnEditar);
		add(btnExcluir);

		setVisible(true);
	}

	public void criarEventos() {

		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtFuncionario.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Preencha o campo", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
				sqlPesquisarFuncionarios(txtFuncionario.getText());
			}
		});

		// ERRO
		btnEditar.addActionListener(new ActionListener() {
			String funcionarioAtual = txtFuncionario.getText();
			@Override
			public void actionPerformed(ActionEvent e) {
				Navegador.funcionariosEditar(funcionarioAtual);
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validando campo e nome
				if(txtFuncionario.getText().isEmpty() || txtFuncionario.getText().length() <= 3) {
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				sqlDeletarFuncionario();
			}
		});

		/*
		listaFuncionarios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	Funcionario listFuncionario = new Funcionario();
            	listFuncionario = listaFuncionarios.getSelectedValue();
               listaFuncionarios.addNotify();
            }
        });
		*/
	}

	private void sqlPesquisarFuncionarios(String nome) {

		// Validando campo
		if(txtFuncionario.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor inserir o Funcion�rio correto");
			return;
		}

		// Conex�o
		//Connection conexao;
		Conexao conexao = new Conexao();
		// Instru��o SQL
		PreparedStatement preparedStatement;
		// Resultados
		ResultSet resultado = null;

		try {
			// Criando instru��o SQL
			String sqlSelect = "SELECT id, nome, sobrenome, dataNascimento, email, cargo, salario FROM T_FUNCIONARIOS WHERE nome = ?;";
			// Conex�o - Driver
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlSelect);
			preparedStatement.setString(1, nome);
			System.out.println("Sucesso");
			resultado = preparedStatement.executeQuery();

			if (resultado.next()) {
				// Next == true "Encontoru os dados"
				JOptionPane.showMessageDialog(null, "Consulta realizado com sucesso", "Consulta", JOptionPane.INFORMATION_MESSAGE);
				do {
					JOptionPane.showMessageDialog(null, "Funcion�rios" + "\n"
							+ "Id: " + resultado.getString("id") + "\n"
							+ "Nome: " + resultado.getString("nome") + "\n" 
							+ "Sobrenome: " + resultado.getString("sobrenome") + "\n"
							+ "Data de Nascimento: " + resultado.getString("dataNascimento") + "\n"
							+ "Email: " + resultado.getString("email") + "\n"
							+ "Cargo: " + resultado.getString("cargo") + "\n"
							+ "Sal�rio: " + resultado.getString("salario"));
				} while(resultado.next());
			} else {
				// Next == false
				JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado","Mensagem", JOptionPane.INFORMATION_MESSAGE);
				Navegador.inicio();
			}

			Navegador.inicio();
			resultado.close();
			conexao.desconectar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar funcion�rios");
			Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			try {
				resultado.close();
				conexao.desconectar();
				System.out.println("Tudo com Sucesso");
			} catch (SQLException ez) {
				ez.printStackTrace(); 
			}
		}
	}

	private void sqlDeletarFuncionario() {
		String nomeFuncionario = txtFuncionario.getText();
		String pergunta = "Deseja realmente excluir o Funcion�rio " + nomeFuncionario + "?";
		int confirmacao = JOptionPane.showConfirmDialog(null, pergunta,"Excluir", JOptionPane.YES_NO_OPTION);

		if(confirmacao == JOptionPane.YES_OPTION) {
			// Conex�o
			//Connection conexao;
			Conexao conexao = new Conexao();
			// Instru��o SQL
			Statement statement;
			// Resultados
			int resultado;

			try {

				// Conectando - Driver
				conexao.conectar();
				// Instru��o SQL
				statement = conexao.criarStatement();
				String sqlDelete = "DELETE FROM T_FUNCIONARIOS WHERE nome=?;";
				PreparedStatement preparedStatement = conexao.criarPreparedStatement(sqlDelete);
				preparedStatement.setString(1, nomeFuncionario);
				resultado = preparedStatement.executeUpdate();
				if (resultado == 1) {
					String  message = String.format("Funcion�rio: %s\nDeletado com sucesso", nomeFuncionario);
					JOptionPane.showMessageDialog(null, message, "Excluir",JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado");
					Navegador.inicio();
				}

				Navegador.inicio();
				conexao.desconectar();

			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir Funcion�rio");
				Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Navegador.inicio();
		return;
	}
}