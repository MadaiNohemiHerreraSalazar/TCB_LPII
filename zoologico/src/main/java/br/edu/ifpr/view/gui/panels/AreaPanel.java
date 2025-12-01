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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.AreaController;
import br.edu.ifpr.zoologicio.model.Area;
import br.edu.ifpr.zoologicio.model.Funcionario;
import br.edu.ifpr.zoologicio.model.Habitat;

public class AreaPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public AreaPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarMenuPrincipal();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarMenuPrincipal() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Áreas");
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
        btnVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal());
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

        JLabel labelTitulo = new JLabel("Cadastro de Área");
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
        JLabel labelNome = new JLabel("Nome da Área:");
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

        // Campo Descrição
        JPanel panelDescricao = new JPanel();
        panelDescricao.setLayout(new BoxLayout(panelDescricao, BoxLayout.Y_AXIS));
        panelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setFont(new Font("Serif", Font.BOLD, 18));
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(labelDescricao);
        JTextArea campoDescricao = new JTextArea(3, 40);
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setPreferredSize(new Dimension(500, 80));
        scrollDescricao.setMaximumSize(new Dimension(500, 80));
        scrollDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(scrollDescricao);
        painelCampos.add(panelDescricao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Funcionários (separados por vírgula)
        JPanel panelFuncionarios = new JPanel();
        panelFuncionarios.setLayout(new BoxLayout(panelFuncionarios, BoxLayout.Y_AXIS));
        panelFuncionarios.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelFuncionarios = new JLabel("IDs dos Funcionários (separados por vírgula):");
        labelFuncionarios.setFont(new Font("Serif", Font.BOLD, 18));
        labelFuncionarios.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionarios.add(labelFuncionarios);
        JTextField campoFuncionarios = new JTextField();
        campoFuncionarios.setPreferredSize(new Dimension(500, 40));
        campoFuncionarios.setMaximumSize(new Dimension(500, 40));
        campoFuncionarios.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionarios.add(campoFuncionarios);
        painelCampos.add(panelFuncionarios);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Habitats (separados por vírgula)
        JPanel panelHabitats = new JPanel();
        panelHabitats.setLayout(new BoxLayout(panelHabitats, BoxLayout.Y_AXIS));
        panelHabitats.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHabitats = new JLabel("IDs dos Habitats (separados por vírgula):");
        labelHabitats.setFont(new Font("Serif", Font.BOLD, 18));
        labelHabitats.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitats.add(labelHabitats);
        JTextField campoHabitats = new JTextField();
        campoHabitats.setPreferredSize(new Dimension(500, 40));
        campoHabitats.setMaximumSize(new Dimension(500, 40));
        campoHabitats.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitats.add(campoHabitats);
        painelCampos.add(panelHabitats);

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
                Area area = new Area();
                area.setNome(campoNome.getText().trim());
                area.setDescricao(campoDescricao.getText().trim());

                // Processar IDs dos Funcionários
                String funcionariosStr = campoFuncionarios.getText().trim();
                if (!funcionariosStr.isEmpty()) {
                    ArrayList<Funcionario> funcionariosList = new ArrayList<>();
                    String[] ids = funcionariosStr.split(",");
                    for (String idStr : ids) {
                        try {
                            Integer id = Integer.parseInt(idStr.trim());
                            Funcionario f = new Funcionario();
                            f.setId(id);
                            funcionariosList.add(f);
                        } catch (NumberFormatException ex) {
                            // Ignorar IDs inválidos
                        }
                    }
                    area.setFuncionarios(funcionariosList);
                }

                // Processar IDs dos Habitats
                String habitatsStr = campoHabitats.getText().trim();
                if (!habitatsStr.isEmpty()) {
                    ArrayList<Habitat> habitatsList = new ArrayList<>();
                    String[] ids = habitatsStr.split(",");
                    for (String idStr : ids) {
                        try {
                            Integer id = Integer.parseInt(idStr.trim());
                            Habitat h = new Habitat();
                            h.setId(id);
                            habitatsList.add(h);
                        } catch (NumberFormatException ex) {
                            // Ignorar IDs inválidos
                        }
                    }
                    area.setHabitats(habitatsList);
                }

                AreaController controller = new AreaController();
                controller.cadastrarArea(area);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Área cadastrada com sucesso!",
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
    public void Editar(Area area) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Área");
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
        JLabel labelNome = new JLabel("Nome da Área:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(area.getNome());
        campoNome.setPreferredSize(new Dimension(500, 40));
        campoNome.setMaximumSize(new Dimension(500, 40));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Descrição
        JPanel panelDescricao = new JPanel();
        panelDescricao.setLayout(new BoxLayout(panelDescricao, BoxLayout.Y_AXIS));
        panelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setFont(new Font("Serif", Font.BOLD, 18));
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(labelDescricao);
        JTextArea campoDescricao = new JTextArea(area.getDescricao(), 3, 40);
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setPreferredSize(new Dimension(500, 80));
        scrollDescricao.setMaximumSize(new Dimension(500, 80));
        scrollDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(scrollDescricao);
        painelCampos.add(panelDescricao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Funcionários
        JPanel panelFuncionarios = new JPanel();
        panelFuncionarios.setLayout(new BoxLayout(panelFuncionarios, BoxLayout.Y_AXIS));
        panelFuncionarios.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelFuncionarios = new JLabel("IDs dos Funcionários (separados por vírgula):");
        labelFuncionarios.setFont(new Font("Serif", Font.BOLD, 18));
        labelFuncionarios.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionarios.add(labelFuncionarios);
        
        // Converter ArrayList de Funcionários para string de IDs
        StringBuilder funcionariosStr = new StringBuilder();
        if (area.getFuncionarios() != null) {
            for (Funcionario f : area.getFuncionarios()) {
                if (f != null && f.getId() != null) {
                    if (funcionariosStr.length() > 0) funcionariosStr.append(",");
                    funcionariosStr.append(f.getId());
                }
            }
        }
        
        JTextField campoFuncionarios = new JTextField(funcionariosStr.toString());
        campoFuncionarios.setPreferredSize(new Dimension(500, 40));
        campoFuncionarios.setMaximumSize(new Dimension(500, 40));
        campoFuncionarios.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionarios.add(campoFuncionarios);
        painelCampos.add(panelFuncionarios);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Habitats
        JPanel panelHabitats = new JPanel();
        panelHabitats.setLayout(new BoxLayout(panelHabitats, BoxLayout.Y_AXIS));
        panelHabitats.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHabitats = new JLabel("IDs dos Habitats (separados por vírgula):");
        labelHabitats.setFont(new Font("Serif", Font.BOLD, 18));
        labelHabitats.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitats.add(labelHabitats);
        
        // Converter ArrayList de Habitats para string de IDs
        StringBuilder habitatsStr = new StringBuilder();
        if (area.getHabitats() != null) {
            for (Habitat h : area.getHabitats()) {
                if (h != null && h.getId() != null) {
                    if (habitatsStr.length() > 0) habitatsStr.append(",");
                    habitatsStr.append(h.getId());
                }
            }
        }
        
        JTextField campoHabitats = new JTextField(habitatsStr.toString());
        campoHabitats.setPreferredSize(new Dimension(500, 40));
        campoHabitats.setMaximumSize(new Dimension(500, 40));
        campoHabitats.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitats.add(campoHabitats);
        painelCampos.add(panelHabitats);

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
            area.setNome(campoNome.getText().trim());
            area.setDescricao(campoDescricao.getText().trim());

            // Processar IDs dos Funcionários
            String funcionariosTexto = campoFuncionarios.getText().trim();
            if (!funcionariosTexto.isEmpty()) {
                ArrayList<Funcionario> funcionariosList = new ArrayList<>();
                String[] ids = funcionariosTexto.split(",");
                for (String idStr : ids) {
                    try {
                        Integer id = Integer.parseInt(idStr.trim());
                        Funcionario f = new Funcionario();
                        f.setId(id);
                        funcionariosList.add(f);
                    } catch (NumberFormatException ex) {
                        // Ignorar IDs inválidos
                    }
                }
                area.setFuncionarios(funcionariosList);
            } else {
                area.setFuncionarios(new ArrayList<>());
            }

            // Processar IDs dos Habitats
            String habitatsTexto = campoHabitats.getText().trim();
            if (!habitatsTexto.isEmpty()) {
                ArrayList<Habitat> habitatsList = new ArrayList<>();
                String[] ids = habitatsTexto.split(",");
                for (String idStr : ids) {
                    try {
                        Integer id = Integer.parseInt(idStr.trim());
                        Habitat h = new Habitat();
                        h.setId(id);
                        habitatsList.add(h);
                    } catch (NumberFormatException ex) {
                        // Ignorar IDs inválidos
                    }
                }
                area.setHabitats(habitatsList);
            } else {
                area.setHabitats(new ArrayList<>());
            }

            try {
                AreaController controller = new AreaController();
                controller.editarArea(area);
                
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Área atualizada com sucesso!",
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
        criarTelaBuscaID("Editar Área", "Digite o ID da Área para Editar:", "Buscar", 
            (id) -> {
                AreaController controller = new AreaController();
                Area area = controller.selecionarArea(id);
                if (area != null) {
                    Editar(area);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhuma área encontrada com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Área", "Digite o ID da Área para Remover:", "Remover", 
            (id) -> {
                AreaController controller = new AreaController();
                Area area = controller.selecionarArea(id);
                if (area != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(
                            MainFrame.getJanela(),
                            "Tem certeza que deseja remover a área: " + area.getNome() + "?",
                            "Confirmar Remoção",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        try {
                            controller.deleteArea(id);
                            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                    "Área removida com sucesso!",
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
                            "Nenhuma área encontrada com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Área", "Digite o ID da Área para Visualizar:", "Visualizar", 
            (id) -> {
                AreaController controller = new AreaController();
                Area area = controller.selecionarArea(id);
                if (area != null) {
                    Selecionar(area);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhuma área encontrada com ID: " + id,
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
    public void Selecionar(Area area) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (area == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Área não encontrada!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações da Área");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        // Grid com 4 linhas básicas + linhas para funcionários e habitats
        int rowCount = 4;
        String funcionariosInfo = converterFuncionariosParaString(area);
        String habitatsInfo = converterHabitatsParaString(area);
        
        if (!funcionariosInfo.isEmpty()) rowCount++;
        if (!habitatsInfo.isEmpty()) rowCount++;

        JPanel painelDados = new JPanel(new GridLayout(rowCount, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(area.getId()));
        JTextField campoNome = criarCampoReadOnly(area.getNome());
        JTextField campoDescricao = criarCampoReadOnly(area.getDescricao());

        // Labels e campos básicos
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("Descrição:"));
        painelDados.add(campoDescricao);

        // Funcionários
        if (!funcionariosInfo.isEmpty()) {
            painelDados.add(new JLabel("Funcionários:"));
            painelDados.add(criarCampoReadOnly(funcionariosInfo));
        }

        // Habitats
        if (!habitatsInfo.isEmpty()) {
            painelDados.add(new JLabel("Habitats:"));
            painelDados.add(criarCampoReadOnly(habitatsInfo));
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

    // MÉTODOS AUXILIARES PARA CONVERTER ARRAYS
    private String converterFuncionariosParaString(Area area) {
        if (area.getFuncionarios() == null || area.getFuncionarios().isEmpty()) {
            return "Nenhum funcionário";
        }
        
        StringBuilder sb = new StringBuilder();
        for (Funcionario f : area.getFuncionarios()) {
            if (f != null) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(f.getId());
                if (f.getNome() != null) {
                    sb.append(" (").append(f.getNome()).append(")");
                }
            }
        }
        return sb.toString();
    }

    private String converterHabitatsParaString(Area area) {
        if (area.getHabitats() == null || area.getHabitats().isEmpty()) {
            return "Nenhum habitat";
        }
        
        StringBuilder sb = new StringBuilder();
        for (Habitat h : area.getHabitats()) {
            if (h != null) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(h.getId());
                if (h.getNome() != null) {
                    sb.append(" (").append(h.getNome()).append(")");
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

        JLabel labelTitulo = new JLabel("Listar Todas as Áreas");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            AreaController controller = new AreaController();
            java.util.List<Area> areas = controller.listarAreas();

            if (areas == null || areas.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhuma área cadastrada no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = {"ID", "Nome", "Descrição", "Qtd Funcionários", "Qtd Habitats"};
                Object[][] dados = new Object[areas.size()][5];

                for (int i = 0; i < areas.size(); i++) {
                    Area area = areas.get(i);
                    dados[i][0] = area.getId();
                    dados[i][1] = area.getNome() != null ? area.getNome() : "";
                    dados[i][2] = area.getDescricao() != null ? 
                                 (area.getDescricao().length() > 30 ? 
                                  area.getDescricao().substring(0, 27) + "..." : 
                                  area.getDescricao()) : "";
                    dados[i][3] = area.getFuncionarios() != null ? area.getFuncionarios().size() : 0;
                    dados[i][4] = area.getHabitats() != null ? area.getHabitats().size() : 0;
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(250);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(100);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de áreas encontradas: " + areas.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar áreas: " + ex.getMessage());
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

