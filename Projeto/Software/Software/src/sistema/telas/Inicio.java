package sistema.telas;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sistema.Navegador;

public class Inicio extends JPanel {
     
    JLabel lblTitle, lblDev, lblEmail, lblAuthor, lblVersion;

    public Inicio(){
        criarComponentes();
        criarEventos();
        // Abrir 03
        Navegador.habilitaMenu();
    }

    private void criarComponentes(){
        setLayout(null);
       
        lblTitle = new JLabel("Escolha uma opção no menu superior", JLabel.CENTER);
        lblAuthor = new JLabel("Autor: Eduardo Sayans", JLabel.LEFT);
        lblDev = new JLabel("Desenvolvido por DevFML", JLabel.LEFT);
        lblEmail = new JLabel("devfastml@outlook.com", JLabel.LEFT);
        lblVersion = new JLabel("Versão: 1.3.4", JLabel.LEFT);
        
        lblTitle.setBounds(20, 100, 660, 40);
        lblDev.setBounds(20, 440, 660, 40);
        lblAuthor.setBounds(20, 460, 660, 40);
        lblEmail.setBounds(20, 480, 660, 40);
        lblVersion.setBounds(20, 500, 660, 40);

        add(lblTitle);
        add(lblDev);
        add(lblAuthor);
        add(lblEmail);
        add(lblVersion);

        setVisible(true);
    }

    private void criarEventos() {
        
    }
}