package br.edu.ifpr.view.gui;

import javax.swing.*;

import br.edu.ifpr.view.gui.panels.AgendaAnimalPanel;
import br.edu.ifpr.view.gui.panels.AgendaFuncionarioPanel;
import br.edu.ifpr.view.gui.panels.AlimentoPanel;
import br.edu.ifpr.view.gui.panels.AnimalPanel;
import br.edu.ifpr.view.gui.panels.AreaPanel;
import br.edu.ifpr.view.gui.panels.CargoPanel;
import br.edu.ifpr.view.gui.panels.CompraPanel;
import br.edu.ifpr.view.gui.panels.FornecedorPanel;
import br.edu.ifpr.view.gui.panels.FuncionarioPanel;
import br.edu.ifpr.view.gui.panels.HabitatPanel;
import br.edu.ifpr.view.gui.panels.LoginPanel;
import br.edu.ifpr.view.gui.panels.RotinaAlimentarPanel;
import br.edu.ifpr.view.gui.panels.VeterinarioPanel;

import java.awt.*;

public class MainFrame {
    private static JFrame janela;
    private static JPanel painelPrincipal;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        MainFrame.iniciar();
        LoginPanel.iniciarLogin();
    }


    // MOSTRAR TELA PRINCIPAL
    // ____________________________________________________________
    public static void mostrarTelaInicial() {
        painelPrincipal.removeAll();
        cardLayout = new CardLayout();
        painelPrincipal.setLayout(cardLayout);

        // Painel da tela inicial
        JPanel telaInicialPanel = criarTelaInicialPanel();
        painelPrincipal.add(telaInicialPanel, "TELA_INICIAL");
        cardLayout.show(painelPrincipal, "TELA_INICIAL");
    }

    private static JPanel criarTelaInicialPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Label topo com texto
        JLabel label = new JLabel("Bem-vindo ao Sistema do nosso Zoológico!");
        label.setFont(new Font("Serif", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        JPanel painelLabel = new JPanel(new BorderLayout());
        painelLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0));
        painelLabel.add(label, BorderLayout.CENTER);
        panel.add(painelLabel, BorderLayout.NORTH);

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

        panel.add(centro, BorderLayout.CENTER);

        return panel;
    }

    // MOSTRAR MENU PRINCIPAL
    // _____________________________________________________________
    public static void mostrarMenuPrincipal() {
        painelPrincipal.removeAll();
        cardLayout = new CardLayout();
        painelPrincipal.setLayout(cardLayout);

        // Painel do menu principal
        JPanel menuPanel = criarMenuPrincipalPanel();
        painelPrincipal.add(menuPanel, "MENU_PRINCIPAL");
        cardLayout.show(painelPrincipal, "MENU_PRINCIPAL");
    }

    private static JPanel criarMenuPrincipalPanel() {
        JPanel panel = new JPanel(new BorderLayout());

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
        painelTitulo.add(Box.createRigidArea(new Dimension(0, 15)));
        painelTitulo.add(labelSubtitulo);

        panel.add(painelTitulo, BorderLayout.NORTH);

        // Painel central com os botões de gerenciamento
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(7, 2, 20, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // Criar botões de gerenciamento
        String[] gerenciamentos = {
                "Animais", "Agenda de Animais", "Funcionários", "Agenda de Funcionários",
                "Cargos", "Áreas", "Habitats", "Vendas", "Rotinas Alimentares",
                "Fornecedores", "Alimentos", "Veterinarios", "Sair do Sistema"
        };

        for (String gerenciamento : gerenciamentos) {
            JButton botao = criarBotaoGerenciamento(gerenciamento);
            painelBotoes.add(botao);
        }

        panel.add(painelBotoes, BorderLayout.CENTER);

        // Painel inferior com botão de voltar
        JPanel painelInferior = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Início");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setBackground(Color.BLACK);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(e -> mostrarTelaInicial());
        painelInferior.add(btnVoltar);
        panel.add(painelInferior, BorderLayout.SOUTH);

        return panel;
    }

    // CRIAR BOTÕES DE GERENCIAMENTO
    // _________________________________________________________________
    private static JButton criarBotaoGerenciamento(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(200, 80));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);

        // Ação correspondente a cada botão
        botao.addActionListener(e -> {
            switch (texto) {
                case "Agenda de Animais":
                    mostrarPanel(new AgendaAnimalPanel());
                    break;
                case "Animais":
                    mostrarPanel(new AnimalPanel());
                    break;
                case "Funcionários":
                    mostrarPanel(new FuncionarioPanel());
                    break;
                case "Agenda de Funcionários":
                    mostrarPanel(new AgendaFuncionarioPanel());
                    break;
                case "Cargos":
                    mostrarPanel(new CargoPanel());
                    break;
                case "Áreas":
                    mostrarPanel(new AreaPanel());
                    break;
                case "Habitats":
                    mostrarPanel(new HabitatPanel());
                    break;
                case "Vendas":
                    mostrarPanel(new CompraPanel());
                    break;
                case "Rotinas Alimentares":
                    mostrarPanel(new RotinaAlimentarPanel());
                    break;
                case "Alimentos":
                    mostrarPanel(new AlimentoPanel());
                    break;
                case "Fornecedores":
                    mostrarPanel(new FornecedorPanel());
                    break;
                case "Veterinários":
                    mostrarPanel(new VeterinarioPanel());
                    break;
                case "Sair do Sistema":
                    int confirm = JOptionPane.showConfirmDialog(janela,
                            "Deseja realmente sair do sistema?",
                            "Confirmar Saída",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(janela, "Abrindo: " + texto);
            }

        });

        return botao;
    }

    // MÉTODO PARA MOSTRAR UM PAINEL
    // _________________________________________________________________

    public static void mostrarPanel(JPanel panel) {
        painelPrincipal.removeAll();
        cardLayout = new CardLayout();
        painelPrincipal.setLayout(cardLayout);

        painelPrincipal.add(panel, "PAINEL_CRUD");
        cardLayout.show(painelPrincipal, "PAINEL_CRUD");

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // MÉTODO PARA VOLTAR AO MENU PRINCIPAL (será chamado pelos painéis)
    // _________________________________________________________________
    public static void iniciar() {
        if (janela != null)
            return;

        janela = new JFrame("Sistema do Zoológico");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1200, 800);
        janela.setLocationRelativeTo(null);

        janela.setLayout(new BorderLayout());

        painelPrincipal = new JPanel(new BorderLayout());
        janela.add(painelPrincipal, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    // Volta ao menu principal (que deve ser outro JPanel)
    public static void voltarAoMenuPrincipal() {
        iniciar();
        painelPrincipal.removeAll();

        JPanel menu = new JPanel();
        menu.add(new JLabel("Menu Principal"));

        painelPrincipal.add(menu, BorderLayout.CENTER);

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // Retorna a referência da janela caso algum panel precise usar
    public static JFrame getJanela() {
        iniciar();
        return janela;
    }

}