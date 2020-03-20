package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import sistema.Navegador;
import sistema.entidades.Funcionario;
import sistema.telas.FuncionariosInserir;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;

public class DAO {
	
	Funcionario funcionario = new Funcionario();
	// conexão
	Conexao conexao = new Conexao();
	CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
	// instrucao SQL
	PreparedStatement preparedStatement = null;
	// resultados
	ResultSet resultado = null;
	
	public boolean inserirFuncionario(Funcionario pFuncionario) {
		funcionario = new Funcionario();
		conexao.conectar();
		String sqlInsert = "INSERT INTO T_FUNCIONARIOS "
				+ "(nome, "
				+ "sobrenome, "
				+ "dataNascimento, "
				+ "email, "
				+ "cargo, "
				+ "salario) "
				+ "VALUES(?,?,?,?,?,?);";
		try {
			System.out.println("DAO");
			preparedStatement = conexao.criarPreparedStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, pFuncionario.getFuncionarioNome());
			preparedStatement.setString(2, pFuncionario.getFuncionarioSobrenome());
			preparedStatement.setString(3, pFuncionario.getFuncionarioDataNascimento());
			preparedStatement.setString(4, pFuncionario.getFuncionarioEmail());
			preparedStatement.setString(5, pFuncionario.getFuncionarioCargo());
			preparedStatement.setString(6, pFuncionario.getFuncionarioSalario());
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar.");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Navegador.inicio();
		conexao.desconectar();
		return true;
	}
}

