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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.CargoController;
import br.edu.ifpr.zoologicio.model.Cargo;
import br.edu.ifpr.zoologicio.model.Funcionario;

public class CargoPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public CargoPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarLogin();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarLogin() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Cargos");
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
        String[] gerenciamentos = { "Cadastro", "Editar", "Remover", "Selecionar", "Listar" };

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

        JLabel labelTitulo = new JLabel("Cadastro de Cargo");
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
        JLabel labelNome = new JLabel("Nome do Cargo:");
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

        // Campo Salário
        JPanel panelSalario = new JPanel();
        panelSalario.setLayout(new BoxLayout(panelSalario, BoxLayout.Y_AXIS));
        panelSalario.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelSalario = new JLabel("Salário (R$):");
        labelSalario.setFont(new Font("Serif", Font.BOLD, 18));
        labelSalario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSalario.add(labelSalario);
        JTextField campoSalario = new JTextField();
        campoSalario.setPreferredSize(new Dimension(500, 40));
        campoSalario.setMaximumSize(new Dimension(500, 40));
        campoSalario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSalario.add(campoSalario);
        painelCampos.add(panelSalario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Carga Horária
        JPanel panelCargaHoraria = new JPanel();
        panelCargaHoraria.setLayout(new BoxLayout(panelCargaHoraria, BoxLayout.Y_AXIS));
        panelCargaHoraria.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCargaHoraria = new JLabel("Carga Horária (horas/semana):");
        labelCargaHoraria.setFont(new Font("Serif", Font.BOLD, 18));
        labelCargaHoraria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargaHoraria.add(labelCargaHoraria);
        JTextField campoCargaHoraria = new JTextField();
        campoCargaHoraria.setPreferredSize(new Dimension(500, 40));
        campoCargaHoraria.setMaximumSize(new Dimension(500, 40));
        campoCargaHoraria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargaHoraria.add(campoCargaHoraria);
        painelCampos.add(panelCargaHoraria);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Senha
        JPanel panelSenha = new JPanel();
        panelSenha.setLayout(new BoxLayout(panelSenha, BoxLayout.Y_AXIS));
        panelSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelSenha = new JLabel("Senha do Cargo:");
        labelSenha.setFont(new Font("Serif", Font.BOLD, 18));
        labelSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSenha.add(labelSenha);
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(500, 40));
        campoSenha.setMaximumSize(new Dimension(500, 40));
        campoSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSenha.add(campoSenha);
        painelCampos.add(panelSenha);
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
                Cargo cargo = new Cargo();
                cargo.setNome(campoNome.getText().trim());
                cargo.setSalario(campoSalario.getText().trim());
                cargo.setCargaHoraria(campoCargaHoraria.getText().trim());
                cargo.setSenha(new String(campoSenha.getPassword()));

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
                    cargo.setFuncionarios(funcionariosList);
                }

                CargoController controller = new CargoController();
                controller.cadastrarCargo(cargo);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Cargo cadastrado com sucesso!",
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
    public void Editar(Cargo cargo) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Cargo");
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
        JLabel labelNome = new JLabel("Nome do Cargo:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(cargo.getNome());
        campoNome.setPreferredSize(new Dimension(500, 40));
        campoNome.setMaximumSize(new Dimension(500, 40));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Salário
        JPanel panelSalario = new JPanel();
        panelSalario.setLayout(new BoxLayout(panelSalario, BoxLayout.Y_AXIS));
        panelSalario.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelSalario = new JLabel("Salário (R$):");
        labelSalario.setFont(new Font("Serif", Font.BOLD, 18));
        labelSalario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSalario.add(labelSalario);
        JTextField campoSalario = new JTextField(cargo.getSalario());
        campoSalario.setPreferredSize(new Dimension(500, 40));
        campoSalario.setMaximumSize(new Dimension(500, 40));
        campoSalario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSalario.add(campoSalario);
        painelCampos.add(panelSalario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Carga Horária
        JPanel panelCargaHoraria = new JPanel();
        panelCargaHoraria.setLayout(new BoxLayout(panelCargaHoraria, BoxLayout.Y_AXIS));
        panelCargaHoraria.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCargaHoraria = new JLabel("Carga Horária (horas/semana):");
        labelCargaHoraria.setFont(new Font("Serif", Font.BOLD, 18));
        labelCargaHoraria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargaHoraria.add(labelCargaHoraria);
        JTextField campoCargaHoraria = new JTextField(cargo.getCargaHoraria());
        campoCargaHoraria.setPreferredSize(new Dimension(500, 40));
        campoCargaHoraria.setMaximumSize(new Dimension(500, 40));
        campoCargaHoraria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCargaHoraria.add(campoCargaHoraria);
        painelCampos.add(panelCargaHoraria);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Senha
        JPanel panelSenha = new JPanel();
        panelSenha.setLayout(new BoxLayout(panelSenha, BoxLayout.Y_AXIS));
        panelSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelSenha = new JLabel("Senha do Cargo:");
        labelSenha.setFont(new Font("Serif", Font.BOLD, 18));
        labelSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSenha.add(labelSenha);
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setText(cargo.getSenha()); // Note: segurança - em produção usar campo vazio
        campoSenha.setPreferredSize(new Dimension(500, 40));
        campoSenha.setMaximumSize(new Dimension(500, 40));
        campoSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSenha.add(campoSenha);
        painelCampos.add(panelSenha);
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
        if (cargo.getFuncionarios() != null) {
            for (Funcionario f : cargo.getFuncionarios()) {
                if (f != null && f.getId() != null) {
                    if (funcionariosStr.length() > 0)
                        funcionariosStr.append(",");
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
            cargo.setNome(campoNome.getText().trim());
            cargo.setSalario(campoSalario.getText().trim());
            cargo.setCargaHoraria(campoCargaHoraria.getText().trim());

            // Atualizar senha apenas se foi alterada
            String novaSenha = new String(campoSenha.getPassword());
            if (!novaSenha.isEmpty()) {
                cargo.setSenha(novaSenha);
            }

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
                cargo.setFuncionarios(funcionariosList);
            } else {
                cargo.setFuncionarios(new ArrayList<>());
            }

            try {
                CargoController controller = new CargoController();
                controller.editarCargo(cargo);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Cargo atualizado com sucesso!",
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
        criarTelaBuscaID("Editar Cargo", "Digite o ID do Cargo para Editar:", "Buscar",
                (id) -> {
                    CargoController controller = new CargoController();
                    Cargo cargo = controller.selecionarCargo(id);
                    if (cargo != null) {
                        Editar(cargo);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhum cargo encontrado com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Cargo", "Digite o ID do Cargo para Remover:", "Remover",
                (id) -> {
                    CargoController controller = new CargoController();
                    Cargo cargo = controller.selecionarCargo(id);
                    if (cargo != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                MainFrame.getJanela(),
                                "Tem certeza que deseja remover o cargo: " + cargo.getNome() + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deletarCargo(id);
                                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                        "Cargo removido com sucesso!",
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
                                "Nenhum cargo encontrado com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Cargo", "Digite o ID do Cargo para Visualizar:", "Visualizar",
                (id) -> {
                    CargoController controller = new CargoController();
                    Cargo cargo = controller.selecionarCargo(id);
                    if (cargo != null) {
                        Selecionar(cargo);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhum cargo encontrado com ID: " + id,
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
    public void Selecionar(Cargo cargo) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (cargo == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Cargo não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações do Cargo");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        // Grid com linhas básicas + linha para funcionários
        int rowCount = 5; // ID, Nome, Salário, Carga Horária, Senha
        String funcionariosInfo = converterFuncionariosParaString(cargo);

        if (!funcionariosInfo.isEmpty())
            rowCount++;

        JPanel painelDados = new JPanel(new GridLayout(rowCount, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(cargo.getId()));
        JTextField campoNome = criarCampoReadOnly(cargo.getNome());
        JTextField campoSalario = criarCampoReadOnly(cargo.getSalario());
        JTextField campoCargaHoraria = criarCampoReadOnly(cargo.getCargaHoraria());
        JTextField campoSenha = criarCampoReadOnly("******"); // Não mostrar senha real

        // Labels e campos básicos
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("Salário:"));
        painelDados.add(campoSalario);
        painelDados.add(new JLabel("Carga Horária:"));
        painelDados.add(campoCargaHoraria);
        painelDados.add(new JLabel("Senha:"));
        painelDados.add(campoSenha);

        // Funcionários
        if (!funcionariosInfo.isEmpty()) {
            painelDados.add(new JLabel("Funcionários:"));
            painelDados.add(criarCampoReadOnly(funcionariosInfo));
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

    // MÉTODO AUXILIAR PARA CONVERTER ARRAY DE FUNCIONÁRIOS
    private String converterFuncionariosParaString(Cargo cargo) {
        if (cargo.getFuncionarios() == null || cargo.getFuncionarios().isEmpty()) {
            return "Nenhum funcionário";
        }

        StringBuilder sb = new StringBuilder();
        for (Funcionario f : cargo.getFuncionarios()) {
            if (f != null) {
                if (sb.length() > 0)
                    sb.append(", ");
                sb.append(f.getId());
                if (f.getNome() != null) {
                    sb.append(" (").append(f.getNome()).append(")");
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

        JLabel labelTitulo = new JLabel("Listar Todos os Cargos");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            CargoController controller = new CargoController();
            java.util.List<Cargo> cargos = controller.listarCargos();

            if (cargos == null || cargos.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhum cargo cadastrado no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = { "ID", "Nome", "Salário", "Carga Horária", "Qtd Funcionários" };
                Object[][] dados = new Object[cargos.size()][5];

                for (int i = 0; i < cargos.size(); i++) {
                    Cargo cargo = cargos.get(i);
                    dados[i][0] = cargo.getId();
                    dados[i][1] = cargo.getNome() != null ? cargo.getNome() : "";
                    dados[i][2] = cargo.getSalario() != null ? cargo.getSalario() : "";
                    dados[i][3] = cargo.getCargaHoraria() != null ? cargo.getCargaHoraria() : "";
                    dados[i][4] = cargo.getFuncionarios() != null ? cargo.getFuncionarios().size() : 0;
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de cargos encontrados: " + cargos.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar cargos: " + ex.getMessage());
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