package sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.imageio.ImageIO;

import java.awt.BorderLayout;
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
// I am not responsible for any damage caused by installing the software on your hardware..
/**
 * Esse Software foi desenvolvido por <b>DevFML</b>.
 * @author Eduardo Sayans
 * 
 * Vers�o do Software: V1.3.4
 * @version 1.3.4
 * 
 * 
 * Para entrar em contato com o desenvolvedor pode-se usar o e-mail: 
 * devfastml@outlook.com
 * Especificar no assunto de e-mail o motivo do contato.
 * 
 * �ltima atualiza��o: 12/03/2020
 * 
 * @since ProjetoV1.3.4 - Refactoring code and variables according to ViewHolder nomenclatures.
 */
public class Sistema{

	/* Definimos uma vari�vel do tipo JPanel, que armazenar� a tela atual do nosso sistema. 
	Ser� do tipo est�tica, pois vamos utiliz�-la em v�rios momentos. */
	public static JPanel JPtela;
	/* Definimos uma vari�vel do tipo JFrame, que ser� a janela da nossa aplica��o. 
	Ser� do tipo est�tica, pois vamos utiliz�-la em v�rios momentos */
	public static JFrame JFframe;


	public static void main(String[] args){
		criarComponentes();

		String strIconWay = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\system03.png";
		Image iconSystem = Toolkit.getDefaultToolkit().getImage(strIconWay);
		JFframe.setIconImage(iconSystem);

	}
	/** 
	 *Cria��o dos componentes de tela
	 */
	private static void criarComponentes(){

		/* Instanciamos o objeto JFrame e definimos algumas configura��es */
		JFframe = new JFrame("Sistema");
		JFframe.setSize(800, 600);
		JFframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFframe.setLocationRelativeTo(null);


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