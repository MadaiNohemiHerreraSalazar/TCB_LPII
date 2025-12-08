package br.edu.ifpr.view.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

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

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.FornecedorController;
import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.Fornecedor;

public class FornecedorPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public FornecedorPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarLogin();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarLogin() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Fornecedores");
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

        JLabel labelTitulo = new JLabel("Cadastro de Fornecedor");
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
        JLabel labelNome = new JLabel("Nome do Fornecedor:");
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

        // Campo CPF
        JPanel panelCPF = new JPanel();
        panelCPF.setLayout(new BoxLayout(panelCPF, BoxLayout.Y_AXIS));
        panelCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCPF = new JLabel("CPF:");
        labelCPF.setFont(new Font("Serif", Font.BOLD, 18));
        labelCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCPF.add(labelCPF);
        JTextField campoCPF = new JTextField();
        campoCPF.setPreferredSize(new Dimension(500, 40));
        campoCPF.setMaximumSize(new Dimension(500, 40));
        campoCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCPF.add(campoCPF);
        painelCampos.add(panelCPF);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Telefone
        JPanel panelTelefone = new JPanel();
        panelTelefone.setLayout(new BoxLayout(panelTelefone, BoxLayout.Y_AXIS));
        panelTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(new Font("Serif", Font.BOLD, 18));
        labelTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTelefone.add(labelTelefone);
        JTextField campoTelefone = new JTextField();
        campoTelefone.setPreferredSize(new Dimension(500, 40));
        campoTelefone.setMaximumSize(new Dimension(500, 40));
        campoTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTelefone.add(campoTelefone);
        painelCampos.add(panelTelefone);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Email
        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(new BoxLayout(panelEmail, BoxLayout.Y_AXIS));
        panelEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Serif", Font.BOLD, 18));
        labelEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEmail.add(labelEmail);
        JTextField campoEmail = new JTextField();
        campoEmail.setPreferredSize(new Dimension(500, 40));
        campoEmail.setMaximumSize(new Dimension(500, 40));
        campoEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEmail.add(campoEmail);
        painelCampos.add(panelEmail);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Alimentos (separados por vírgula)
        JPanel panelAlimentos = new JPanel();
        panelAlimentos.setLayout(new BoxLayout(panelAlimentos, BoxLayout.Y_AXIS));
        panelAlimentos.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAlimentos = new JLabel("IDs dos Alimentos (separados por vírgula):");
        labelAlimentos.setFont(new Font("Serif", Font.BOLD, 18));
        labelAlimentos.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAlimentos.add(labelAlimentos);
        JTextField campoAlimentos = new JTextField();
        campoAlimentos.setPreferredSize(new Dimension(500, 40));
        campoAlimentos.setMaximumSize(new Dimension(500, 40));
        campoAlimentos.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAlimentos.add(campoAlimentos);
        painelCampos.add(panelAlimentos);

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
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(campoNome.getText().trim());
                fornecedor.setCpf(campoCPF.getText().trim());
                fornecedor.setTelefone(campoTelefone.getText().trim());
                fornecedor.setEmail(campoEmail.getText().trim());

                // Validar email básico
                String email = campoEmail.getText().trim();
                if (!email.isEmpty() && !email.contains("@")) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Email inválido! Deve conter '@'",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Processar IDs dos Alimentos
                String alimentosStr = campoAlimentos.getText().trim();
                if (!alimentosStr.isEmpty()) {
                    ArrayList<Alimento> alimentosList = new ArrayList<>();
                    String[] ids = alimentosStr.split(",");
                    for (String idStr : ids) {
                        try {
                            Integer id = Integer.parseInt(idStr.trim());
                            Alimento a = new Alimento();
                            a.setId(id);
                            alimentosList.add(a);
                        } catch (NumberFormatException ex) {
                            // Ignorar IDs inválidos
                        }
                    }
                    fornecedor.setAlimentos(alimentosList);
                }

                FornecedorController controller = new FornecedorController();
                controller.cadastrarFornecedor(fornecedor);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Fornecedor cadastrado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                MainFrame.voltarAoMenuPrincipal();

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
    public void Editar(Fornecedor fornecedor) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Fornecedor");
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
        JLabel labelNome = new JLabel("Nome do Fornecedor:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(fornecedor.getNome());
        campoNome.setPreferredSize(new Dimension(500, 40));
        campoNome.setMaximumSize(new Dimension(500, 40));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo CPF
        JPanel panelCPF = new JPanel();
        panelCPF.setLayout(new BoxLayout(panelCPF, BoxLayout.Y_AXIS));
        panelCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCPF = new JLabel("CPF:");
        labelCPF.setFont(new Font("Serif", Font.BOLD, 18));
        labelCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCPF.add(labelCPF);
        JTextField campoCPF = new JTextField(fornecedor.getCpf());
        campoCPF.setPreferredSize(new Dimension(500, 40));
        campoCPF.setMaximumSize(new Dimension(500, 40));
        campoCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCPF.add(campoCPF);
        painelCampos.add(panelCPF);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Telefone
        JPanel panelTelefone = new JPanel();
        panelTelefone.setLayout(new BoxLayout(panelTelefone, BoxLayout.Y_AXIS));
        panelTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(new Font("Serif", Font.BOLD, 18));
        labelTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTelefone.add(labelTelefone);
        JTextField campoTelefone = new JTextField(fornecedor.getTelefone());
        campoTelefone.setPreferredSize(new Dimension(500, 40));
        campoTelefone.setMaximumSize(new Dimension(500, 40));
        campoTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTelefone.add(campoTelefone);
        painelCampos.add(panelTelefone);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Email
        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(new BoxLayout(panelEmail, BoxLayout.Y_AXIS));
        panelEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Serif", Font.BOLD, 18));
        labelEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEmail.add(labelEmail);
        JTextField campoEmail = new JTextField(fornecedor.getEmail());
        campoEmail.setPreferredSize(new Dimension(500, 40));
        campoEmail.setMaximumSize(new Dimension(500, 40));
        campoEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEmail.add(campoEmail);
        painelCampos.add(panelEmail);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Alimentos
        JPanel panelAlimentos = new JPanel();
        panelAlimentos.setLayout(new BoxLayout(panelAlimentos, BoxLayout.Y_AXIS));
        panelAlimentos.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAlimentos = new JLabel("IDs dos Alimentos (separados por vírgula):");
        labelAlimentos.setFont(new Font("Serif", Font.BOLD, 18));
        labelAlimentos.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAlimentos.add(labelAlimentos);
        
        // Converter ArrayList de Alimentos para string de IDs
        StringBuilder alimentosStr = new StringBuilder();
        if (fornecedor.getAlimentos() != null) {
            for (Alimento a : fornecedor.getAlimentos()) {
                if (a != null && a.getId() != null) {
                    if (alimentosStr.length() > 0) alimentosStr.append(",");
                    alimentosStr.append(a.getId());
                }
            }
        }
        
        JTextField campoAlimentos = new JTextField(alimentosStr.toString());
        campoAlimentos.setPreferredSize(new Dimension(500, 40));
        campoAlimentos.setMaximumSize(new Dimension(500, 40));
        campoAlimentos.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAlimentos.add(campoAlimentos);
        painelCampos.add(panelAlimentos);

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
            fornecedor.setNome(campoNome.getText().trim());
            fornecedor.setCpf(campoCPF.getText().trim());
            fornecedor.setTelefone(campoTelefone.getText().trim());
            fornecedor.setEmail(campoEmail.getText().trim());

            // Validar email básico
            String email = campoEmail.getText().trim();
            if (!email.isEmpty() && !email.contains("@")) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Email inválido! Deve conter '@'",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Processar IDs dos Alimentos
            String alimentosTexto = campoAlimentos.getText().trim();
            if (!alimentosTexto.isEmpty()) {
                ArrayList<Alimento> alimentosList = new ArrayList<>();
                String[] ids = alimentosTexto.split(",");
                for (String idStr : ids) {
                    try {
                        Integer id = Integer.parseInt(idStr.trim());
                        Alimento a = new Alimento();
                        a.setId(id);
                        alimentosList.add(a);
                    } catch (NumberFormatException ex) {
                        // Ignorar IDs inválidos
                    }
                }
                fornecedor.setAlimentos(alimentosList);
            } else {
                fornecedor.setAlimentos(new ArrayList<>());
            }

            try {
                FornecedorController controller = new FornecedorController();
                controller.editarFornecedor(fornecedor);
                
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Fornecedor atualizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                MainFrame.voltarAoMenuPrincipal();
                
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
        criarTelaBuscaID("Editar Fornecedor", "Digite o ID do Fornecedor para Editar:", "Buscar", 
            (id) -> {
                FornecedorController controller = new FornecedorController();
                Fornecedor fornecedor = controller.selecionarFornecedor(id);
                if (fornecedor != null) {
                    Editar(fornecedor);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum fornecedor encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Fornecedor", "Digite o ID do Fornecedor para Remover:", "Remover", 
            (id) -> {
                FornecedorController controller = new FornecedorController();
                Fornecedor fornecedor = controller.selecionarFornecedor(id);
                if (fornecedor != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(
                            MainFrame.getJanela(),
                            "Tem certeza que deseja remover o fornecedor: " + fornecedor.getNome() + "?",
                            "Confirmar Remoção",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        try {
                            controller.deletarFornecedor(id);
                            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                    "Fornecedor removido com sucesso!",
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
                            "Nenhum fornecedor encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Fornecedor", "Digite o ID do Fornecedor para Visualizar:", "Visualizar", 
            (id) -> {
                FornecedorController controller = new FornecedorController();
                Fornecedor fornecedor = controller.selecionarFornecedor(id);
                if (fornecedor != null) {
                    Selecionar(fornecedor);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum fornecedor encontrado com ID: " + id,
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
    public void Selecionar(Fornecedor fornecedor) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (fornecedor == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Fornecedor não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações do Fornecedor");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        // Grid com linhas básicas + linha para alimentos
        int rowCount = 5; // ID, Nome, CPF, Telefone, Email
        String alimentosInfo = converterAlimentosParaString(fornecedor);
        
        if (!alimentosInfo.isEmpty()) rowCount++;

        JPanel painelDados = new JPanel(new GridLayout(rowCount, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(fornecedor.getId()));
        JTextField campoNome = criarCampoReadOnly(fornecedor.getNome());
        JTextField campoCPF = criarCampoReadOnly(fornecedor.getCpf());
        JTextField campoTelefone = criarCampoReadOnly(fornecedor.getTelefone());
        JTextField campoEmail = criarCampoReadOnly(fornecedor.getEmail());

        // Labels e campos básicos
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("CPF:"));
        painelDados.add(campoCPF);
        painelDados.add(new JLabel("Telefone:"));
        painelDados.add(campoTelefone);
        painelDados.add(new JLabel("Email:"));
        painelDados.add(campoEmail);

        // Alimentos
        if (!alimentosInfo.isEmpty()) {
            painelDados.add(new JLabel("Alimentos:"));
            painelDados.add(criarCampoReadOnly(alimentosInfo));
        }

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

    // MÉTODO AUXILIAR PARA CONVERTER ARRAY DE ALIMENTOS
    private String converterAlimentosParaString(Fornecedor fornecedor) {
        if (fornecedor.getAlimentos() == null || fornecedor.getAlimentos().isEmpty()) {
            return "Nenhum alimento";
        }
        
        StringBuilder sb = new StringBuilder();
        for (Alimento a : fornecedor.getAlimentos()) {
            if (a != null) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(a.getId());
                if (a.getNome() != null) {
                    sb.append(" (").append(a.getNome()).append(")");
                }
            }
        }
        return sb.toString();
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

        JLabel labelTitulo = new JLabel("Listar Todos os Fornecedores");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            FornecedorController controller = new FornecedorController();
            java.util.List<Fornecedor> fornecedores = controller.listarFornecedores();

            if (fornecedores == null || fornecedores.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhum fornecedor cadastrado no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = {"ID", "Nome", "CPF", "Telefone", "Email", "Qtd Alimentos"};
                Object[][] dados = new Object[fornecedores.size()][6];

                for (int i = 0; i < fornecedores.size(); i++) {
                    Fornecedor fornecedor = fornecedores.get(i);
                    dados[i][0] = fornecedor.getId();
                    dados[i][1] = fornecedor.getNome() != null ? fornecedor.getNome() : "";
                    dados[i][2] = fornecedor.getCpf() != null ? fornecedor.getCpf() : "";
                    dados[i][3] = fornecedor.getTelefone() != null ? fornecedor.getTelefone() : "";
                    dados[i][4] = fornecedor.getEmail() != null ? fornecedor.getEmail() : "";
                    dados[i][5] = fornecedor.getAlimentos() != null ? fornecedor.getAlimentos().size() : 0;
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(180);
                tabela.getColumnModel().getColumn(5).setPreferredWidth(100);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de fornecedores encontrados: " + fornecedores.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar fornecedores: " + ex.getMessage());
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
