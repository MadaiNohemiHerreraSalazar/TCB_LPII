package br.edu.ifpr.view.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Component;
import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;

public class AreaPanel {

    private static JFrame janela;
    private static JPanel painelPrincipal;

    public static void main(String[] args) {
        criarJanelaPrincipal();
        mostrarMenuPrincipal();

    }

    // CRIAR JANELA PRINCIPAL COM TITULO
    // ___________________________________________________________

    private static void criarJanelaPrincipal() {

        janela = new JFrame("Sistema do Zoológico - Gerenciamento de Áreas");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1200, 800);
        janela.setLocationRelativeTo(null);

        janela.setLayout(new BorderLayout());

        // Painel principal que será trocado
        painelPrincipal = new JPanel(new BorderLayout());
        janela.add(painelPrincipal, BorderLayout.CENTER);

        janela.setVisible(true);
    }
    
// MOSTRAR TELA DE VERIFICAÇÃO DE PERMISSO
    //__________________________________________________________

   /*   private static void mostrarTelaVerificacao() {
    // Limpar painel principal
    painelPrincipal.removeAll();
    painelPrincipal.setLayout(new BorderLayout());

    // Título - CENTRO
    JLabel labelTitulo = new JLabel("Validação de Entrada");
    labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
    labelTitulo.setHorizontalAlignment(JLabel.CENTER);
    
    JPanel painelTitulo = new JPanel(new BorderLayout());
    painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
    painelTitulo.add(labelTitulo, BorderLayout.CENTER);
    painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

    // Painel CENTRAL com texto e caixinha
    JPanel painelCentral = new JPanel();
    painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
    painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

    // Texto "Coloque a senha"
    JLabel labelSenha = new JLabel("Coloque a senha:");
    labelSenha.setFont(new Font("Serif", Font.BOLD, 20));
    labelSenha.setForeground(Color.BLACK);
    labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Espaço
    painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

    // Painel para caixinha e botão
    JPanel painelInput = new JPanel();
    painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

    JPasswordField campoSenha = new JPasswordField(15);
    campoSenha.setFont(new Font("Serif", Font.PLAIN, 18));

    JButton botaoVerificar = new JButton("Verificar");
    botaoVerificar.setFont(new Font("Serif", Font.BOLD, 16));
    botaoVerificar.setBackground(Color.BLACK);
    botaoVerificar.setForeground(Color.WHITE);

    painelInput.add(campoSenha);
    painelInput.add(botaoVerificar);

    // Adicionar ao painel central
    painelCentral.add(labelSenha);
    painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
    painelCentral.add(painelInput);

    painelPrincipal.add(painelCentral, BorderLayout.CENTER);

    // Atualizar e ActionListeners (mesmo código)
    painelPrincipal.revalidate();
    painelPrincipal.repaint();

}*/


  // MOSTRAR MENU PRINCIPAL
    // ________________________________________________________

    private static void mostrarMenuPrincipal() {
        // Limpar painel principal
        painelPrincipal.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel(" Gerenciamento de Áreas");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        // Texto abaixo do título
        JLabel labelSubtitulo = new JLabel("Selecione uma das opções de gerenciamento abaixo:");
        labelSubtitulo.setFont(new Font("Serif", Font.BOLD, 20));
        labelSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        labelSubtitulo.setForeground(Color.BLACK);

        // Painel para o título e subtítulo
        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new BoxLayout(painelTitulo, BoxLayout.Y_AXIS));
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        painelTitulo.add(labelTitulo);
        painelTitulo.add(Box.createRigidArea(new Dimension(0, 15))); // Espaço entre título e subtítulo
        painelTitulo.add(labelSubtitulo);

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Painel central com os botões de gerenciamento
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(7, 2, 20, 20)); // 4 linhas, 2 colunas, espaço 20px
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));

        // Criar botões de gerenciamento
        String[] gerenciamentos = { " Cadastro ", "Editar ", " Remover ", " Selecionar ", " Listar " };

        for (String gerenciamento : gerenciamentos) {
            JButton botao = criarBotaoGerenciamento(gerenciamento);
            painelBotoes.add(botao);
        }

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        // Painel inferior com botão de voltar
        JPanel painelInferior = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Início");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setBackground(Color.BLACK);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        painelInferior.add(btnVoltar);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        // Atualizar a janela
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // CRAIR BOTÕES DE GERENCIAMENTO
    // _________________________________________________________________

    private static JButton criarBotaoGerenciamento(String texto) {

        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(200, 80));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);

        // Asignar la acción correspondiente a cada botón
        botao.addActionListener(e -> {
            switch (texto) {
                case "Cadastro":
                    Cadastro();
                    break;
                case "Editar":
                    Editar();
                    break;
                case "Remover":
                    Remover();
                    break;
                case "Selecionar":
                    Selecionar();
                case "Listar":
                    Listar();
                default:
                    JOptionPane.showMessageDialog(janela, "Abrindo: " + texto);
            }
        });

        return botao;
    }

    public static void Cadastro() {

    }

    public static void Editar() {

    }

    public static void Remover() {

    }

    public static void Selecionar() {

    }

    public static void Listar() {

    }

}