package sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.imageio.ImageIO;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import sistema.telas.*;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;

/**
 * Esse Software foi desenvolvido por <b>DevFML</b>.
 * @author Eduardo Sayans
 * 
 * Vers�o do Software: V1.3
 * @version 1.3
 * 
 * Para entrar em contato com o desenvolvedor pode-se usar o  e-mail: 
 * @link <a>devfastml@outlook.com</a>
 * Especificar no assunto de e-mail o motivo do contato.
 * 
 * �ltima atualiza��o: 23/02/2020
 * 
 * @since ProjetoV1.2 - Projeto concluido "Funcionalmente na V1.2", todas valida��es temporarias concluidas e funcionando.
 * 
 * Classe principal do Software, usada para executar o software
 */
public class Sistema{

	/* Definimos uma vari�vel do tipo JPanel, que armazenar� a tela atual do nosso sistema. 
	Ser� do tipo est�tica, pois vamos utiliz�-la em v�rios momentos. */
	public static JPanel tela;
	/* Definimos uma vari�vel do tipo JFrame, que ser� a janela da nossa aplica��o. 
	Ser� do tipo est�tica, pois vamos utiliz�-la em v�rios momentos */
	public static JFrame frame;


	public static void main(String[] args){
		criarComponentes();
		
		String caminhoIcone = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Sistema\\img\\system03.png";
		Image iconeSistema = Toolkit.getDefaultToolkit().getImage(caminhoIcone);
		frame.setIconImage(iconeSistema);
	}
	/** M�todo para cria��o dos componentes de tela
	 * @return JPanel - Painel da tela */
	private static void criarComponentes(){

		/* Instanciamos o objeto JFrame e definimos algumas configura��es */
		frame = new JFrame("Sistema");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		Conexao conexao = new Conexao();
		conexao.conectar();
		CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
		criarBancoDeDados.createTableFuncionarios();
		criarBancoDeDados.createTableCargos();
		conexao.desconectar();

		// Abrir 01
		/** M�todo para cria��o do menu*/
		Navegador.login();
	}
	/*
	// Imagem de fundo
	BuffredImage imgFundo;
	
	@Override
	protected void PainComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgFundo.getScaledInstance(500, 500, 0), 0, 0, null);
	}
	
	String caminho = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Sistema\\img\\testeFundo.png";
    frame.getContentPane().add(null, caminho);
    
    try {
		imgFundo = ImageIO.read("C:\\Users\\Eduardo\\Desktop\\Projeto\\Sistema\\img\\testeFundo.png");
	} catch (IOException ex){
		Logger.getLogger(Sistema.getName()).Log(Level.SEVERE, null, ex);
	}
    */
}