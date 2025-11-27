package br.edu.ifpr.view.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private static JFrame janela;
    private static JPanel painelPrincipal;

    public static void main(String[] args) {
        criarJanelaPrincipal();
        mostrarTelaInicial();
    }

    // CRIAR JANELA PRINCIPAL COM TITULO
    // ___________________________________________________________

    private static void criarJanelaPrincipal() {

        janela = new JFrame("Sistema do Zoológico");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1200, 800);
        janela.setLocationRelativeTo(null);

        janela.setLayout(new BorderLayout());

        // Painel principal que será trocado
        painelPrincipal = new JPanel(new BorderLayout());
        janela.add(painelPrincipal, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    // MOSTRAR TELA PRINCIPAL
    // ____________________________________________________________

    private static void mostrarTelaInicial() {
        // Limpar painel principal
        painelPrincipal.removeAll();

        // Label topo com texto
        JLabel label = new JLabel("Bem-vindo ao Sistema do nosso Zoológico!");
        label.setFont(new Font("Serif", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);

        // Painel para o label topo com espaçamento
        JPanel painelLabel = new JPanel(new BorderLayout());
        painelLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0));
        painelLabel.add(label, BorderLayout.CENTER);
        painelPrincipal.add(painelLabel, BorderLayout.NORTH);

        // Carregar e redimensionar e centralizar imagem
        ImageIcon imagemOriginal = new ImageIcon(MainFrame.class.getResource("/imagens/spar.jpg"));
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imagemRedimensionada);
        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelImagem.setVerticalAlignment(SwingConstants.CENTER);

        // Botão para entrar no sistema
        JButton btnEntrar = new JButton("Entrar no Sistema");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 24));
        btnEntrar.setPreferredSize(new Dimension(300, 60));
        btnEntrar.setBackground(Color.BLACK);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);

        // Ação do botão para ir ao menu principal
        btnEntrar.addActionListener(e -> mostrarMenuPrincipal());

        // Painel central com BoxLayout para organizar verticalmente
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        // Adicionar a imagem
        centro.add(labelImagem);

        // Adicionar espaço entre a imagem e o botão
        centro.add(Box.createRigidArea(new Dimension(0, 40)));

        // Adicionar o botão
        centro.add(btnEntrar);

        // Centralizar os componentes no painel
        labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(centro, BorderLayout.CENTER);

        // Atualizar a janela
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // MOSTRAR MENU PRINCIPAL
    // _____________________________________________________________

    private static void mostrarMenuPrincipal() {
        // Limpar painel principal
        painelPrincipal.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Menu Principal - Sistema Zoológico");
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
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // Criar botões de gerenciamento
        String[] gerenciamentos = {"Animais", "Agenda de Animais", "Funcionários", "Agenda de Funcionários",
                "Cargos", "Áreas", "Habitats", "Vendas", "Fornecedores", "Alimentos", "Veterinarios", "Sair do Sistema"
        };

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
        btnVoltar.addActionListener(e -> mostrarTelaInicial());
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
            case "Gerenciamento de Animais":
            //     mostrarGerenciamentoAnimais();
            //     break;
            // case "Gerenciamento de Funcionários":
            //     mostrarGerenciamentoFuncionarios();
            //     break;
            // case "Gerenciamento de Habitats":
            //     mostrarGerenciamentoHabitats();
            //     break;
            // ... otros casos
            default:
                JOptionPane.showMessageDialog(janela, "Abrindo: " + texto);
        }
    });
    
    return botao;
    }
}