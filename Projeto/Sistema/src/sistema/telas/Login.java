package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import sistema.Navegador;

public class Login extends JPanel {

    JLabel labelUsuario;
    JTextField campoUsuario;
    JLabel labelSenha;
    JPasswordField campoSenha;
    JButton botaoEntrar, botaoSair;
    ImageIcon imgLogin = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Sistema\\img\\login02.png");
    ImageIcon imgExit = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Sistema\\img\\exit01.png");

    public Login(){
        criarComponentes();
        criarEventos();
    }

    private void criarComponentes() {

        // Nenhum tipo de layout
        setLayout(null);

        /* Os componentes est�o sendo inicializados com textos e alinhamentos. O bot�o apenas com seu texto*/
        JLabel labelTitulo = new JLabel("Seja Bem vindo ao Sistema", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 18));

        labelUsuario = new JLabel("Usu�rio", JLabel.LEFT);
        campoUsuario = new JFormattedTextField();
        labelSenha = new JLabel("Senha", JLabel.LEFT);
        campoSenha = new JPasswordField();
        botaoEntrar = new JButton("Login", imgLogin);
        botaoSair = new JButton("Sair", imgExit);

        // Posicionamentos e o tamanho dos componentes na tela
        labelTitulo.setBounds(20, 100, 660, 40);
        labelUsuario.setBounds(250, 220, 200, 20);
        campoUsuario.setBounds(250, 240, 230, 40);
        labelSenha.setBounds(250, 280, 200, 20);
        campoSenha.setBounds(250, 300, 230, 40);
        botaoEntrar.setBounds(250, 350, 200, 40);
        botaoSair.setBounds(250, 400, 200, 40);

        // Adcionando componentes � tela
        add(labelTitulo);
        add(labelUsuario);
        add(campoUsuario);
        add(labelSenha);
        add(campoSenha);
        add(botaoEntrar);
        add(botaoSair);

        // Visibilidade do Frame
        setVisible(true);
    }

    private void criarEventos(){
    	// Login
        botaoEntrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Validando as credenciais
                if(campoUsuario.getText().equals("admin") && new String(campoSenha.getPassword()).equals("admin")) {
                    // Abrir 02
                    Navegador.inicio();
                }
                else if(campoUsuario.getText().isEmpty() || campoSenha.getText().isEmpty()) {
                	JOptionPane.showMessageDialog(null, "Preencha todos os campo", "Valida��o", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Acesso n�o permitido");
                }
            }
        });
        
        // Exit
        botaoSair.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
    }
}