package br.edu.ifpr.view.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
import br.edu.ifpr.zoologicio.controller.FuncionarioController;
import br.edu.ifpr.zoologicio.model.AgendaFuncionario;
import br.edu.ifpr.zoologicio.model.Area;
import br.edu.ifpr.zoologicio.model.Cargo;
import br.edu.ifpr.zoologicio.model.Funcionario;

public class FuncionarioPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public FuncionarioPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarLogin();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarLogin() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Funcionários");
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

        JLabel labelTitulo = new JLabel("Cadastro de Funcionário");
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
        JLabel labelNome = new JLabel("Nome do Funcionário:");
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

        // Campo ID Agenda Funcionário
        JPanel panelAgenda = new JPanel();
        panelAgenda.setLayout(new BoxLayout(panelAgenda, BoxLayout.Y_AXIS));
        panelAgenda.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAgenda = new JLabel("ID da Agenda do Funcionário:");
        labelAgenda.setFont(new Font("Serif", Font.BOLD, 18));
        labelAgenda.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgenda.add(labelAgenda);
        JTextField campoAgenda = new JTextField();
        campoAgenda.setPreferredSize(new Dimension(500, 40));
        campoAgenda.setMaximumSize(new Dimension(500, 40));
        campoAgenda.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgenda.add(campoAgenda);
        painelCampos.add(panelAgenda);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Cargo
        JPanel panelCargo = new JPanel();
        panelCargo.setLayout(new BoxLayout(panelCargo, BoxLayout.Y_AXIS));
        panelCargo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCargo = new JLabel("ID do Cargo:");
        labelCargo.setFont(new Font("Serif", Font.BOLD, 18));
        labelCargo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargo.add(labelCargo);
        JTextField campoCargo = new JTextField();
        campoCargo.setPreferredSize(new Dimension(500, 40));
        campoCargo.setMaximumSize(new Dimension(500, 40));
        campoCargo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargo.add(campoCargo);
        painelCampos.add(panelCargo);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Área
        JPanel panelArea = new JPanel();
        panelArea.setLayout(new BoxLayout(panelArea, BoxLayout.Y_AXIS));
        panelArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelArea = new JLabel("ID da Área:");
        labelArea.setFont(new Font("Serif", Font.BOLD, 18));
        labelArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelArea.add(labelArea);
        JTextField campoArea = new JTextField();
        campoArea.setPreferredSize(new Dimension(500, 40));
        campoArea.setMaximumSize(new Dimension(500, 40));
        campoArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelArea.add(campoArea);
        painelCampos.add(panelArea);

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
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(campoNome.getText().trim());
                funcionario.setCpf(campoCPF.getText().trim());
                funcionario.setEmail(campoEmail.getText().trim());
                funcionario.setTelefone(campoTelefone.getText().trim());

                // Validar email básico
                String email = campoEmail.getText().trim();
                if (!email.isEmpty() && !email.contains("@")) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Email inválido! Deve conter '@'",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Agenda Funcionário
                String agendaIdStr = campoAgenda.getText().trim();
                if (!agendaIdStr.isEmpty()) {
                    try {
                        Integer agendaId = Integer.parseInt(agendaIdStr);
                        AgendaFuncionario agenda = new AgendaFuncionario();
                        agenda.setId(agendaId);
                        funcionario.setAgendaFuncionario(agenda);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "ID da Agenda deve ser um número!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Cargo
                String cargoIdStr = campoCargo.getText().trim();
                if (!cargoIdStr.isEmpty()) {
                    try {
                        Integer cargoId = Integer.parseInt(cargoIdStr);
                        Cargo cargo = new Cargo();
                        cargo.setId(cargoId);
                        funcionario.setCargo(cargo);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "ID do Cargo deve ser um número!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Área
                String areaIdStr = campoArea.getText().trim();
                if (!areaIdStr.isEmpty()) {
                    try {
                        Integer areaId = Integer.parseInt(areaIdStr);
                        Area area = new Area();
                        area.setId(areaId);
                        funcionario.setArea(area);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "ID da Área deve ser um número!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                FuncionarioController controller = new FuncionarioController();
                controller.cadastrarFuncionario(funcionario);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Funcionário cadastrado com sucesso!",
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
    public void Editar(Funcionario funcionario) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Funcionário");
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
        JLabel labelNome = new JLabel("Nome do Funcionário:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(funcionario.getNome());
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
        JTextField campoCPF = new JTextField(funcionario.getCpf());
        campoCPF.setPreferredSize(new Dimension(500, 40));
        campoCPF.setMaximumSize(new Dimension(500, 40));
        campoCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCPF.add(campoCPF);
        painelCampos.add(panelCPF);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Email
        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(new BoxLayout(panelEmail, BoxLayout.Y_AXIS));
        panelEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Serif", Font.BOLD, 18));
        labelEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEmail.add(labelEmail);
        JTextField campoEmail = new JTextField(funcionario.getEmail());
        campoEmail.setPreferredSize(new Dimension(500, 40));
        campoEmail.setMaximumSize(new Dimension(500, 40));
        campoEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEmail.add(campoEmail);
        painelCampos.add(panelEmail);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Telefone
        JPanel panelTelefone = new JPanel();
        panelTelefone.setLayout(new BoxLayout(panelTelefone, BoxLayout.Y_AXIS));
        panelTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(new Font("Serif", Font.BOLD, 18));
        labelTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTelefone.add(labelTelefone);
        JTextField campoTelefone = new JTextField(funcionario.getTelefone());
        campoTelefone.setPreferredSize(new Dimension(500, 40));
        campoTelefone.setMaximumSize(new Dimension(500, 40));
        campoTelefone.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTelefone.add(campoTelefone);
        painelCampos.add(panelTelefone);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Agenda Funcionário
        JPanel panelAgenda = new JPanel();
        panelAgenda.setLayout(new BoxLayout(panelAgenda, BoxLayout.Y_AXIS));
        panelAgenda.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAgenda = new JLabel("ID da Agenda do Funcionário:");
        labelAgenda.setFont(new Font("Serif", Font.BOLD, 18));
        labelAgenda.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgenda.add(labelAgenda);
        JTextField campoAgenda = new JTextField(
            funcionario.getAgendaFuncionario() != null && funcionario.getAgendaFuncionario().getId() != null ?
            String.valueOf(funcionario.getAgendaFuncionario().getId()) : ""
        );
        campoAgenda.setPreferredSize(new Dimension(500, 40));
        campoAgenda.setMaximumSize(new Dimension(500, 40));
        campoAgenda.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgenda.add(campoAgenda);
        painelCampos.add(panelAgenda);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Cargo
        JPanel panelCargo = new JPanel();
        panelCargo.setLayout(new BoxLayout(panelCargo, BoxLayout.Y_AXIS));
        panelCargo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCargo = new JLabel("ID do Cargo:");
        labelCargo.setFont(new Font("Serif", Font.BOLD, 18));
        labelCargo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargo.add(labelCargo);
        JTextField campoCargo = new JTextField(
            funcionario.getCargo() != null && funcionario.getCargo().getId() != null ?
            String.valueOf(funcionario.getCargo().getId()) : ""
        );
        campoCargo.setPreferredSize(new Dimension(500, 40));
        campoCargo.setMaximumSize(new Dimension(500, 40));
        campoCargo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargo.add(campoCargo);
        painelCampos.add(panelCargo);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Área
        JPanel panelArea = new JPanel();
        panelArea.setLayout(new BoxLayout(panelArea, BoxLayout.Y_AXIS));
        panelArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelArea = new JLabel("ID da Área:");
        labelArea.setFont(new Font("Serif", Font.BOLD, 18));
        labelArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelArea.add(labelArea);
        JTextField campoArea = new JTextField(
            funcionario.getArea() != null && funcionario.getArea().getId() != null ?
            String.valueOf(funcionario.getArea().getId()) : ""
        );
        campoArea.setPreferredSize(new Dimension(500, 40));
        campoArea.setMaximumSize(new Dimension(500, 40));
        campoArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelArea.add(campoArea);
        painelCampos.add(panelArea);

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
            funcionario.setNome(campoNome.getText().trim());
            funcionario.setCpf(campoCPF.getText().trim());
            funcionario.setEmail(campoEmail.getText().trim());
            funcionario.setTelefone(campoTelefone.getText().trim());

            // Validar email básico
            String email = campoEmail.getText().trim();
            if (!email.isEmpty() && !email.contains("@")) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Email inválido! Deve conter '@'",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Agenda Funcionário
            String agendaIdStr = campoAgenda.getText().trim();
            if (!agendaIdStr.isEmpty()) {
                try {
                    Integer agendaId = Integer.parseInt(agendaIdStr);
                    if (funcionario.getAgendaFuncionario() == null) {
                        funcionario.setAgendaFuncionario(new AgendaFuncionario());
                    }
                    funcionario.getAgendaFuncionario().setId(agendaId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "ID da Agenda deve ser um número!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                funcionario.setAgendaFuncionario(null);
            }

            // Cargo
            String cargoIdStr = campoCargo.getText().trim();
            if (!cargoIdStr.isEmpty()) {
                try {
                    Integer cargoId = Integer.parseInt(cargoIdStr);
                    if (funcionario.getCargo() == null) {
                        funcionario.setCargo(new Cargo());
                    }
                    funcionario.getCargo().setId(cargoId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "ID do Cargo deve ser um número!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                funcionario.setCargo(null);
            }

            // Área
            String areaIdStr = campoArea.getText().trim();
            if (!areaIdStr.isEmpty()) {
                try {
                    Integer areaId = Integer.parseInt(areaIdStr);
                    if (funcionario.getArea() == null) {
                        funcionario.setArea(new Area());
                    }
                    funcionario.getArea().setId(areaId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "ID da Área deve ser um número!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                funcionario.setArea(null);
            }

            try {
                FuncionarioController controller = new FuncionarioController();
                controller.editarFuncionario(funcionario);
                
                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Funcionário atualizado com sucesso!",
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
        criarTelaBuscaID("Editar Funcionário", "Digite o ID do Funcionário para Editar:", "Buscar", 
            (id) -> {
                FuncionarioController controller = new FuncionarioController();
                Funcionario funcionario = controller.selecionarFuncionario(id);
                if (funcionario != null) {
                    Editar(funcionario);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum funcionário encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Funcionário", "Digite o ID do Funcionário para Remover:", "Remover", 
            (id) -> {
                FuncionarioController controller = new FuncionarioController();
                Funcionario funcionario = controller.selecionarFuncionario(id);
                if (funcionario != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(
                            MainFrame.getJanela(),
                            "Tem certeza que deseja remover o funcionário: " + funcionario.getNome() + "?",
                            "Confirmar Remoção",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        try {
                            controller.deletarFuncionario(id);
                            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                    "Funcionário removido com sucesso!",
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
                            "Nenhum funcionário encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Funcionário", "Digite o ID do Funcionário para Visualizar:", "Visualizar", 
            (id) -> {
                FuncionarioController controller = new FuncionarioController();
                Funcionario funcionario = controller.selecionarFuncionario(id);
                if (funcionario != null) {
                    Selecionar(funcionario);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum funcionário encontrado com ID: " + id,
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
    public void Selecionar(Funcionario funcionario) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (funcionario == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Funcionário não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações do Funcionário");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        // Grid com 7 linhas (ID, Nome, CPF, Email, Telefone, Agenda, Cargo, Área)
        int rowCount = 8;

        JPanel painelDados = new JPanel(new GridLayout(rowCount, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(funcionario.getId()));
        JTextField campoNome = criarCampoReadOnly(funcionario.getNome());
        JTextField campoCPF = criarCampoReadOnly(funcionario.getCpf());
        JTextField campoEmail = criarCampoReadOnly(funcionario.getEmail());
        JTextField campoTelefone = criarCampoReadOnly(funcionario.getTelefone());
        
        // Campos para objetos relacionados
        String agendaInfo = funcionario.getAgendaFuncionario() != null ? 
                          "ID: " + funcionario.getAgendaFuncionario().getId() : "Não atribuída";
        JTextField campoAgenda = criarCampoReadOnly(agendaInfo);
        
        String cargoInfo = funcionario.getCargo() != null ? 
                         "ID: " + funcionario.getCargo().getId() + 
                         (funcionario.getCargo().getNome() != null ? 
                          " (" + funcionario.getCargo().getNome() + ")" : "") : "Não atribuído";
        JTextField campoCargo = criarCampoReadOnly(cargoInfo);
        
        String areaInfo = funcionario.getArea() != null ? 
                        "ID: " + funcionario.getArea().getId() + 
                        (funcionario.getArea().getNome() != null ? 
                         " (" + funcionario.getArea().getNome() + ")" : "") : "Não atribuída";
        JTextField campoArea = criarCampoReadOnly(areaInfo);

        // Labels e campos
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("CPF:"));
        painelDados.add(campoCPF);
        painelDados.add(new JLabel("Email:"));
        painelDados.add(campoEmail);
        painelDados.add(new JLabel("Telefone:"));
        painelDados.add(campoTelefone);
        painelDados.add(new JLabel("Agenda:"));
        painelDados.add(campoAgenda);
        painelDados.add(new JLabel("Cargo:"));
        painelDados.add(campoCargo);
        painelDados.add(new JLabel("Área:"));
        painelDados.add(campoArea);

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

        JLabel labelTitulo = new JLabel("Listar Todos os Funcionários");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            FuncionarioController controller = new FuncionarioController();
            java.util.List<Funcionario> funcionarios = controller.listarFuncionarios();

            if (funcionarios == null || funcionarios.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhum funcionário cadastrado no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = {"ID", "Nome", "CPF", "Email", "Telefone", "Cargo", "Área"};
                Object[][] dados = new Object[funcionarios.size()][7];

                for (int i = 0; i < funcionarios.size(); i++) {
                    Funcionario funcionario = funcionarios.get(i);
                    dados[i][0] = funcionario.getId();
                    dados[i][1] = funcionario.getNome() != null ? funcionario.getNome() : "";
                    dados[i][2] = funcionario.getCpf() != null ? funcionario.getCpf() : "";
                    dados[i][3] = funcionario.getEmail() != null ? funcionario.getEmail() : "";
                    dados[i][4] = funcionario.getTelefone() != null ? funcionario.getTelefone() : "";
                    dados[i][5] = funcionario.getCargo() != null && funcionario.getCargo().getNome() != null ? 
                                 funcionario.getCargo().getNome() : "Sem cargo";
                    dados[i][6] = funcionario.getArea() != null && funcionario.getArea().getNome() != null ? 
                                 funcionario.getArea().getNome() : "Sem área";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(180);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(5).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(6).setPreferredWidth(120);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de funcionários encontrados: " + funcionarios.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar funcionários: " + ex.getMessage());
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