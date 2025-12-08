package br.edu.ifpr.view.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.AlimentoController;
import br.edu.ifpr.zoologicio.model.Alimento;


public class AlimentoPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public AlimentoPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarLogin();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarLogin() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Alimentos");
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

        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        // Painel central com os botões de gerenciamento
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 1, 20, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));

        // Criar botões de gerenciamento
        String[] gerenciamentos = {"Cadastro", "Editar", "Remover", "Selecionar", "Listar"};

        for (String gerenciamento : gerenciamentos) {
            JButton botao = criarBotaoGerenciamento(gerenciamento);
            painelBotoes.add(botao);
        }

        painelPrincipalInterno.add(painelBotoes, BorderLayout.CENTER);

        // Painel inferior com botão de voltar
        JPanel painelInferior = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setBackground(Color.BLACK);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(e -> MainFrame.mostrarLogin());
        painelInferior.add(btnVoltar);
        painelPrincipalInterno.add(painelInferior, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // CRIAR BOTÕES DE GERENCIAMENTO
    private JButton criarBotaoGerenciamento(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(200, 80));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);

        botao.addActionListener(e -> {
            switch (texto.trim()) {
                case "Cadastro":
                    Cadastro();
                    break;
                case "Editar":
                    telaBuscaIDEdicao();
                    break;
                case "Remover":
                    telaBuscaIDRemover_Remover();
                    break;
                case "Selecionar":
                    telaBuscaIDSelecionar();
                    break;
                case "Listar":
                    Listar();
                    break;
                default:
                    JOptionPane.showMessageDialog(MainFrame.getJanela(), "Abrindo: " + texto);
            }
        });

        return botao;
    }

    // ========== MÉTODOS CRUD ==========

    // CADASTRO
    public void Cadastro() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Cadastro de Alimento");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Campo Nome
        JPanel panelNome = new JPanel();
        panelNome.setLayout(new BoxLayout(panelNome, BoxLayout.Y_AXIS));
        panelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelNome = new JLabel("Nome do Alimento:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(500, 40));
        campoNome.setMaximumSize(new Dimension(500, 40));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Validade
        JPanel panelValidade = new JPanel();
        panelValidade.setLayout(new BoxLayout(panelValidade, BoxLayout.Y_AXIS));
        panelValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelValidade = new JLabel("Data de Validade (DD/MM/AAAA):");
        labelValidade.setFont(new Font("Serif", Font.BOLD, 18));
        labelValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelValidade.add(labelValidade);
        JTextField campoValidade = new JTextField();
        campoValidade.setPreferredSize(new Dimension(500, 40));
        campoValidade.setMaximumSize(new Dimension(500, 40));
        campoValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelValidade.add(campoValidade);
        painelCampos.add(panelValidade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Estoque
        JPanel panelEstoque = new JPanel();
        panelEstoque.setLayout(new BoxLayout(panelEstoque, BoxLayout.Y_AXIS));
        panelEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEstoque = new JLabel("Quantidade em Estoque:");
        labelEstoque.setFont(new Font("Serif", Font.BOLD, 18));
        labelEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEstoque.add(labelEstoque);
        JTextField campoEstoque = new JTextField();
        campoEstoque.setPreferredSize(new Dimension(500, 40));
        campoEstoque.setMaximumSize(new Dimension(500, 40));
        campoEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEstoque.add(campoEstoque);
        painelCampos.add(panelEstoque);

        painelPrincipalInterno.add(painelCampos, BorderLayout.CENTER);

        // PANEL DE BOTÕES
        JPanel painelBotoes = new JPanel();
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBackground(Color.BLACK);
        botaoSalvar.setForeground(Color.WHITE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);

        painelBotoes.add(botaoSalvar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(botaoVoltar);

        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        // ACTION
        botaoSalvar.addActionListener(e -> {
            try {
                Alimento alimento = new Alimento();
                alimento.setNome(campoNome.getText().trim());
                alimento.setValidade(campoValidade.getText().trim());
                
                // Converter estoque para inteiro
                if (!campoEstoque.getText().trim().isEmpty()) {
                    alimento.setEstoque(Integer.parseInt(campoEstoque.getText().trim()));
                }

                AlimentoController controller = new AlimentoController();
                controller.cadastrarAlimento(alimento);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Alimento cadastrado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                MainFrame.voltarAoMenuPrincipal();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Estoque deve conter apenas números!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Erro ao salvar: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // EDITAR
    public void Editar(Alimento alimento) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Alimento");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Campo Nome
        JPanel panelNome = new JPanel();
        panelNome.setLayout(new BoxLayout(panelNome, BoxLayout.Y_AXIS));
        panelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelNome = new JLabel("Nome do Alimento:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(alimento.getNome());
        campoNome.setPreferredSize(new Dimension(500, 40));
        campoNome.setMaximumSize(new Dimension(500, 40));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Validade
        JPanel panelValidade = new JPanel();
        panelValidade.setLayout(new BoxLayout(panelValidade, BoxLayout.Y_AXIS));
        panelValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelValidade = new JLabel("Data de Validade (DD/MM/AAAA):");
        labelValidade.setFont(new Font("Serif", Font.BOLD, 18));
        labelValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelValidade.add(labelValidade);
        JTextField campoValidade = new JTextField(alimento.getValidade());
        campoValidade.setPreferredSize(new Dimension(500, 40));
        campoValidade.setMaximumSize(new Dimension(500, 40));
        campoValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelValidade.add(campoValidade);
        painelCampos.add(panelValidade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Estoque
        JPanel panelEstoque = new JPanel();
        panelEstoque.setLayout(new BoxLayout(panelEstoque, BoxLayout.Y_AXIS));
        panelEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEstoque = new JLabel("Quantidade em Estoque:");
        labelEstoque.setFont(new Font("Serif", Font.BOLD, 18));
        labelEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEstoque.add(labelEstoque);
        JTextField campoEstoque = new JTextField(alimento.getEstoque() != null ? 
                                                String.valueOf(alimento.getEstoque()) : "");
        campoEstoque.setPreferredSize(new Dimension(500, 40));
        campoEstoque.setMaximumSize(new Dimension(500, 40));
        campoEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEstoque.add(campoEstoque);
        painelCampos.add(panelEstoque);

        painelPrincipalInterno.add(painelCampos, BorderLayout.CENTER);

        // PANEL DE BOTÕES
        JPanel painelBotoes = new JPanel();
        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.setBackground(Color.BLACK);
        botaoAtualizar.setForeground(Color.WHITE);

        JButton botaoCancelar = new JButton("Voltar");
        botaoCancelar.setBackground(Color.GRAY);
        botaoCancelar.setForeground(Color.WHITE);

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(botaoCancelar);

        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        // ACTION
        botaoAtualizar.addActionListener(e -> {
            alimento.setNome(campoNome.getText().trim());
            alimento.setValidade(campoValidade.getText().trim());
            
            try {
                if (!campoEstoque.getText().trim().isEmpty()) {
                    alimento.setEstoque(Integer.parseInt(campoEstoque.getText().trim()));
                } else {
                    alimento.setEstoque(null);
                }
                
                AlimentoController controller = new AlimentoController();
                controller.editarAlimento(alimento);
                
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Alimento atualizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                MainFrame.voltarAoMenuPrincipal();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Estoque deve conter apenas números!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Erro ao atualizar: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoCancelar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // TELA BUSCA ID PARA EDIÇÃO
    public void telaBuscaIDEdicao() {
        criarTelaBuscaID("Editar Alimento", "Digite o ID do Alimento para Editar:", "Buscar", 
            (id) -> {
                AlimentoController controller = new AlimentoController();
                Alimento alimento = controller.selecionarAlimento(id);
                if (alimento != null) {
                    Editar(alimento);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum alimento encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Alimento", "Digite o ID do Alimento para Remover:", "Remover", 
            (id) -> {
                AlimentoController controller = new AlimentoController();
                Alimento alimento = controller.selecionarAlimento(id);
                if (alimento != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(
                            MainFrame.getJanela(),
                            "Tem certeza que deseja remover o alimento: " + alimento.getNome() + "?",
                            "Confirmar Remoção",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        try {
                            controller.deletarAlimento(id);
                            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                    "Alimento removido com sucesso!",
                                    "Sucesso",
                                    JOptionPane.INFORMATION_MESSAGE);
                            MainFrame.voltarAoMenuPrincipal();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                    "Erro ao remover: " + ex.getMessage(),
                                    "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum alimento encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Alimento", "Digite o ID do Alimento para Visualizar:", "Visualizar", 
            (id) -> {
                AlimentoController controller = new AlimentoController();
                Alimento alimento = controller.selecionarAlimento(id);
                if (alimento != null) {
                    Selecionar(alimento);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum alimento encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // MÉTODO GENÉRICO PARA CRIAR TELA DE BUSCA POR ID
    private void criarTelaBuscaID(String titulo, String labelTexto, String textoBotao, BuscaCallback callback) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel label = new JLabel(labelTexto);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        JButton botaoAcao = new JButton(textoBotao);
        botaoAcao.setFont(new Font("Serif", Font.BOLD, 16));
        botaoAcao.setBackground(Color.BLACK);
        botaoAcao.setForeground(Color.WHITE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);

        painelInput.add(campoID);
        painelInput.add(botaoAcao);
        painelInput.add(botaoVoltar);

        painelCentral.add(label);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipalInterno.add(painelCentral, BorderLayout.CENTER);

        // ACTION
        botaoAcao.addActionListener(e -> {
            String idTexto = campoID.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Por favor, digite um ID válido!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idTexto);
                callback.executar(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Por favor, digite um número válido para o ID!",
                        "Erro de Formato",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal());

        campoID.addActionListener(e -> botaoAcao.doClick());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // INTERFACE PARA CALLBACK
    private interface BuscaCallback {
        void executar(int id);
    }

    // SELECIONAR/VISUALIZAR
    public void Selecionar(Alimento alimento) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (alimento == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Alimento não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações do Alimento");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(4, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(alimento.getId()));
        JTextField campoNome = criarCampoReadOnly(alimento.getNome());
        JTextField campoValidade = criarCampoReadOnly(alimento.getValidade());
        JTextField campoEstoque = criarCampoReadOnly(alimento.getEstoque() != null ? 
                                                    String.valueOf(alimento.getEstoque()) : "Não informado");

        // Labels
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("Validade:"));
        painelDados.add(campoValidade);
        painelDados.add(new JLabel("Estoque:"));
        painelDados.add(campoEstoque);

        painelPrincipalInterno.add(painelDados, BorderLayout.CENTER);

        // BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal());

        painelBotoes.add(botaoVoltar);
        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // MÉTODO AUXILIAR PARA CRIAR CAMPOS READ-ONLY
    private JTextField criarCampoReadOnly(String texto) {
        JTextField campo = new JTextField(texto);
        campo.setFont(new Font("Serif", Font.PLAIN, 14));
        campo.setEditable(false);
        campo.setBackground(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return campo;
    }

    // LISTAR
    public void Listar() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Listar Todos os Alimentos");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            AlimentoController controller = new AlimentoController();
            java.util.List<Alimento> alimentos = controller.listarAlimentos();

            if (alimentos == null || alimentos.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhum alimento cadastrado no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = {"ID", "Nome", "Validade", "Estoque"};
                Object[][] dados = new Object[alimentos.size()][4];

                for (int i = 0; i < alimentos.size(); i++) {
                    Alimento alimento = alimentos.get(i);
                    dados[i][0] = alimento.getId();
                    dados[i][1] = alimento.getNome() != null ? alimento.getNome() : "";
                    dados[i][2] = alimento.getValidade() != null ? alimento.getValidade() : "";
                    dados[i][3] = alimento.getEstoque() != null ? alimento.getEstoque() : "";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(80);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de alimentos encontrados: " + alimentos.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar alimentos: " + ex.getMessage());
            labelErro.setFont(new Font("Serif", Font.BOLD, 16));
            labelErro.setForeground(Color.RED);
            labelErro.setHorizontalAlignment(JLabel.CENTER);
            painelCentral.add(labelErro, BorderLayout.CENTER);
        }

        painelPrincipalInterno.add(painelCentral, BorderLayout.CENTER);

        // BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));

        JButton botaoAtualizar = new JButton("Atualizar Lista");
        botaoAtualizar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoAtualizar.setBackground(Color.BLUE);
        botaoAtualizar.setForeground(Color.WHITE);
        botaoAtualizar.addActionListener(e -> Listar());

        JButton botaoVoltar = new JButton("Voltar ao Menu");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal());

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(botaoVoltar);

        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }
}

