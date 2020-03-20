package sistema.telas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sistema.Navegador;

public class Inicio extends JPanel {
     
    JLabel labelTitulo, labelDev, labelEmail, labelAutor, labelVersao;

    public Inicio(){
        criarComponentes();
        criarEventos();
        // Abrir 03
        Navegador.habilitaMenu();
    }

    private void criarComponentes(){

        setLayout(null);
        /*
        labelTitulo = new JLabel("Escolha uma opção no menu superior"
        		+ "\nSoftware Desenvolvido por: DevFML"
        		+ "\nAutor: Eduardo Sayans"
        		+ "\ndevfastml@outlook.com"
        		+ "\nVersão: 1.3", JLabel.CENTER);
        */
        labelTitulo = new JLabel("Escolha uma opção no menu superior", JLabel.CENTER);
        labelAutor = new JLabel("Autor: Eduardo Sayans", JLabel.LEFT);
        labelDev = new JLabel("Desenvolvido por DevFML", JLabel.LEFT);
        labelEmail = new JLabel("devfastml@outlook.com", JLabel.LEFT);
        labelVersao = new JLabel("Versão: 1.3", JLabel.LEFT);
        
        labelTitulo.setBounds(20, 100, 660, 40);
        labelDev.setBounds(20, 440, 660, 40);
        labelAutor.setBounds(20, 460, 660, 40);
        labelEmail.setBounds(20, 480, 660, 40);
        labelVersao.setBounds(20, 500, 660, 40);

        add(labelTitulo);
        add(labelDev);
        add(labelAutor);
        add(labelEmail);
        add(labelVersao);

        setVisible(true);
    }

    private void criarEventos() {
        
    }
}