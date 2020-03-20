package sistema;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sistema.telas.CargosConsultar;
import sistema.telas.CargosEditar;
import sistema.telas.CargosInserir;
import sistema.telas.FuncionariosConsultar;
import sistema.telas.FuncionariosEditar;
import sistema.telas.FuncionariosInserir;
import sistema.telas.Inicio;
import sistema.telas.Login;
import sistema.telas.RelatoriosCargos;
import sistema.telas.RelatoriosSalarios;

public class Navegador {
    
    // Menu
    private static boolean menuConstruido;
    private static boolean menuHabilitado;
    private static JMenuBar menuBar;
    private static JMenu menuArquivo, menuFuncionarios, menuCargos, menuRelatorios;
    private static JMenuItem miInicio, miSair, miFuncionariosConsultar, miFuncionariosCadastrar, miCargosConsultar;
    private static JMenuItem miCargosCadastrar, miRelatoriosCargos, miRelatoriosSalarios;
    
    public static void login(){
        Sistema.JPtela = new Login();
        Sistema.JFframe.setTitle("Funcionários Company - V1.3.4");
        Navegador.atualizarTela();
    }
    
    public static void inicio(){
        Sistema.JPtela = new Inicio();
        Sistema.JFframe.setTitle("Funcionários Company - V1.3.4");
        Navegador.atualizarTela();
    }
    
    public static void funcionariosCadastrar(){
        Sistema.JPtela = new FuncionariosInserir();   
        Sistema.JFframe.setTitle("Funcionários Company - Cadastrar funcionários - V1.3.4");     
        Navegador.atualizarTela();
    }
    
    public static void funcionariosConsultar(){
        Sistema.JPtela = new FuncionariosConsultar();
        Sistema.JFframe.setTitle("Funcionários Company - Consultar funcionários - V1.3.4");     
        Navegador.atualizarTela();
    }
    
    public static void funcionariosEditar(String funcionarioAtual){
        Sistema.JPtela = new FuncionariosEditar(funcionarioAtual);  
        Sistema.JFframe.setTitle("Funcionários Company - Editar funcionários - V1.3.4");           
        Navegador.atualizarTela();
    }
    
    public static void cargosCadastrar(){
        Sistema.JPtela = new CargosInserir();
        Sistema.JFframe.setTitle("Funcionários Company - Cadastrar cargos - V1.3.4");
        Navegador.atualizarTela();
    }
    
    public static void cargosConsultar(){
        Sistema.JPtela = new CargosConsultar();  
        Sistema.JFframe.setTitle("Funcionários Company - Consultar cargos - V1.3.4");      
        Navegador.atualizarTela();
    }
    
    public static void cargosEditar(Cargo cargoAtual){
        Sistema.JPtela = new CargosEditar(cargoAtual);      
        Sistema.JFframe.setTitle("Funcionários Company - Editar cargos - V1.3.4");  
        Navegador.atualizarTela();
    }
    
    public static void relatoriosCargos(){
    	/*
        Sistema.tela = new RelatoriosCargos();
        Sistema.frame.setTitle("Funcionários Company - Relatórios");    
        Navegador.atualizarTela();
        */
    	JOptionPane.showMessageDialog(null, "EM MANUTENÇÃO", "SORRY", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void relatoriosSalarios(){
    	/*
        Sistema.tela = new RelatoriosSalarios();
        Sistema.frame.setTitle("Funcionários Company - Relatórios");    
        Navegador.atualizarTela();
        */
    	JOptionPane.showMessageDialog(null, "EM MANUTENÇÃO", "SORRY", JOptionPane.ERROR_MESSAGE);
    }
    
    // Método que remove a tela atual e adiciona a próxima tela
    private static void atualizarTela(){
        Sistema.JFframe.getContentPane().removeAll();
        Sistema.JPtela.setVisible(true);
        Sistema.JFframe.add(Sistema.JPtela);
        
        Sistema.JFframe.setVisible(true);
    }
    
    private static void construirMenu(){
        if(!menuConstruido){
            menuConstruido = true;
            
            menuBar = new JMenuBar();
            
            // menu Arquivo
            menuArquivo = new JMenu("Arquivo");
            menuBar.add(menuArquivo);
            miInicio = new JMenuItem("Inicio");
            menuArquivo.add(miInicio);
            miSair = new JMenuItem("Sair");
            menuArquivo.add(miSair);
            
            // menu Funcionários
            menuFuncionarios = new JMenu("Funcionários");
            menuBar.add(menuFuncionarios);
            miFuncionariosCadastrar = new JMenuItem("Cadastrar");
            menuFuncionarios.add(miFuncionariosCadastrar);
            miFuncionariosConsultar = new JMenuItem("Consultar");
            menuFuncionarios.add(miFuncionariosConsultar);
            
            // menu Cargos
            menuCargos = new JMenu("Cargos");
            menuBar.add(menuCargos);
            miCargosCadastrar = new JMenuItem("Cadastrar");
            menuCargos.add(miCargosCadastrar);
            miCargosConsultar = new JMenuItem("Consultar");
            menuCargos.add(miCargosConsultar);
            
            // menu Relatórios
            menuRelatorios = new JMenu("Relatórios");
            menuBar.add(menuRelatorios);
            miRelatoriosCargos = new JMenuItem("Funcionários por cargos");
            menuRelatorios.add(miRelatoriosCargos);
            miRelatoriosSalarios = new JMenuItem("Salários dos funcionários");
            menuRelatorios.add(miRelatoriosSalarios);
            
            criarEventosMenu();
            
        }
    }
    
    public static void habilitaMenu(){
        if(!menuConstruido) construirMenu();
        if(!menuHabilitado){
            menuHabilitado = true;
            Sistema.JFframe.setJMenuBar(menuBar);
        }
    }
    
    public static void desabilitaMenu(){
        if(menuHabilitado){
            menuHabilitado = false;
            Sistema.JFframe.setJMenuBar(null);
        }        
    }

    private static void criarEventosMenu() {
    	// Inicio
    	 miInicio.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Navegador.inicio();
             }
         });
    	
    	// Exit
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Funcionario
        miFuncionariosCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionariosCadastrar();
            }
        });
        miFuncionariosConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionariosConsultar();
            }
        });
        
        // Cargos
        miCargosCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargosCadastrar();
            }
        });
        miCargosConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargosConsultar();
            }
        });
        
        miRelatoriosCargos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosCargos();
            }
        });
        
        miRelatoriosSalarios.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosSalarios();
            }
        });
    }
}
