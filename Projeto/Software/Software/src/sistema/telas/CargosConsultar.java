package sistema.telas;

import java.awt.Color;
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
import sistema.Navegador;
import sistema.entidades.Cargo;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CargosConsultar extends JPanel {

	Cargo cargoAtual;
	JLabel lblTitle, lblCargo;
	JTextField txtCampoCargo;
	JButton btnPesquisar, btnEditar, btnExcluir;
	DefaultListModel<Cargo> listCargosModel = new DefaultListModel();
	JList<Cargo> listCargos;
	ImageIcon imgIconPesquisar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\search03.png");
	ImageIcon imgIconDeletar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgIconEditar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");

	public CargosConsultar(){
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	private void criarComponentes() {
		setLayout(null);

		lblTitle = new JLabel("Consulta de Cargos", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));      
		lblCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		txtCampoCargo = new JTextField();
		btnPesquisar = new JButton("Pesquisar Cargo", imgIconPesquisar);
		btnEditar = new JButton("Editar Cargo", imgIconEditar);
		//botaoEditar.setEnabled(false);
		btnExcluir = new JButton("Excluir Cargo", imgIconDeletar);
		//botaoExcluir.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		/*
		listasCargosModelo = new DefaultListModel();
		listaCargos = new JList();
		listaCargos.setModel(listasCargosModelo);
		listaCargos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		 */


		lblTitle.setBounds(20, 20, 660, 40);
		lblCargo.setBounds(150, 120, 400, 20);
		txtCampoCargo.setBounds(150, 140, 400, 40);
		btnPesquisar.setBounds(560, 140, 200, 40); 
		//listaCargos.setBounds(150, 200, 400, 240);
		btnEditar.setBounds(560, 360, 180, 40); 
		btnExcluir.setBounds(560, 400, 180, 40);
		
		//botaoPesquisar.setBackground(Color.BLUE);
		
		add(lblTitle);
		add(lblCargo);
		add(txtCampoCargo);
		//add(listaCargos);
		add(btnPesquisar);
		add(btnEditar);
		add(btnExcluir);
		
		/*
		ImageIcon imgLogo = new ImageIcon("C:/Users/Eduardo/Desktop/Projeto/Sistema/img/icon1.png");
		label = new JButton("", imgLogo);
		label.setBounds(333, 112, 300, 300);
		add(label);
		*/

		setVisible(true);
	}

	private void criarEventos() {

		// Pesquisar
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtCampoCargo.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Preencha o campo", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
				sqlPesquisarCargos(txtCampoCargo.getText());
			}
		});

		// Editar
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navegador.cargosEditar(cargoAtual);
			}
		});

		// Excluir
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validando campo e nome
				if(txtCampoCargo.getText().isEmpty() || txtCampoCargo.getText().length() <= 3) {
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				sqlDeletarCargo();
			}
		});
		/*
		listaCargos.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cargoAtual = listaCargos.getSelectedValue();
				if(cargoAtual == null){
					botaoEditar.setEnabled(false);
					botaoExcluir.setEnabled(false);
				}else{
					botaoEditar.setEnabled(true);
					botaoExcluir.setEnabled(true);
				}
			}
		});
		 */
	}

	private void sqlPesquisarCargos(String nome) {
		
		// Validando campo
		if(txtCampoCargo.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor inserir o cargo");
			return;
		}
		
		// Conexao BD
		Conexao conexao = new Conexao();
		// Instrucao SQL
		PreparedStatement preparedStatement = null;
		// Resultados
		ResultSet resultado = null;

		try {
			String sqlSelect = "SELECT id,nome FROM T_CARGOS WHERE nome = ?;";
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlSelect);
			preparedStatement.setString(1, nome);
			resultado = preparedStatement.executeQuery();

			if(resultado.next()){
				// Next == true "Encontoru os dados"
				JOptionPane.showMessageDialog(null, "Consulta realizado com sucesso", "Consulta", JOptionPane.INFORMATION_MESSAGE);
				do{
					JOptionPane.showMessageDialog(null, "Cargos" + "\n"
							+ "Id: " + resultado.getString("id") + "\n"
							+ "Nome: " + resultado.getString("nome"));
				}while(resultado.next());
			}else{
				// Next == false
				JOptionPane.showMessageDialog(null, "Cargo não encontrado","Mensagem", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			Navegador.inicio();
			resultado.close();
			conexao.desconectar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar cargos");
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

	private void sqlDeletarCargo() {
		int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Cargo " + txtCampoCargo.getText() + "?", "Excluir", JOptionPane.YES_NO_OPTION);
		if(confirmacao == JOptionPane.YES_OPTION) {
			// conexão
			Conexao conexao = new Conexao();
			// instrucao SQL
			Statement statement = null;
			// resultados
			ResultSet resultados = null;

			String nomeCargo = txtCampoCargo.getText();

			try {
				// Conectando - Driver
				conexao.conectar();
				// Instrução SQL
				statement = conexao.criarStatement();
				String sqlDelete = "DELETE FROM T_CARGOS WHERE nome=?;";
				PreparedStatement preparedStatement = conexao.criarPreparedStatement(sqlDelete);
				preparedStatement.setString(1, nomeCargo);
				int resultado = preparedStatement.executeUpdate();
				if (resultado == 1) {
					String  message = String.format("Cargo: %s\nDeletado com sucesso", nomeCargo);
					JOptionPane.showMessageDialog(null, message, "Excluir",JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cargo não encontrado");
					return;
				}

				Navegador.inicio();
				conexao.desconectar();

			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Cargo.");
				Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
			}
			Navegador.inicio();
			return;
		}
	}
}
