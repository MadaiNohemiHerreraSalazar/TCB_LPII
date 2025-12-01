package br.edu.ifpr.view.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.ifpr.zoologicio.controller.AgendaFuncionarioController;
import br.edu.ifpr.zoologicio.model.AgendaFuncionario;
import br.edu.ifpr.zoologicio.model.Funcionario;

public class AgendaFuncionarioPanel extends JPanel {

    private JPanel painelPrincipalInterno; // Para gerenciar sub-painel dentro do panel principal

    public AgendaFuncionarioPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarMenuPrincipal();
    }

    // MOSTRAR MENU PRINCIPAL
    // ________________________________________________________
    private void mostrarMenuPrincipal() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel(" Gerenciamento da Agenda de Funcionários");
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
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal()); // Depois você pode trocar para voltar ao MainFrame
        painelInferior.add(btnVoltar);
        painelPrincipalInterno.add(painelInferior, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // CRIAR BOTÕES DE GERENCIAMENTO
    // _________________________________________________________________
    private JButton criarBotaoGerenciamento(String texto) {

        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(200, 80));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);

        // Ação correspondente a cada botão
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
                    JOptionPane.showMessageDialog(this, "Abrindo: " + texto);
            }
        });

        return botao;
    }

    // CRUD
    // _________________________________________________________________

    public void Cadastro() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Cadastro de Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS
        JPanel panelAtividade = new JPanel();
        panelAtividade.setLayout(new BoxLayout(panelAtividade, BoxLayout.Y_AXIS));
        panelAtividade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAtividade = new JLabel("Atividade:");
        labelAtividade.setFont(new Font("Serif", Font.BOLD, 18));
        labelAtividade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAtividade.add(labelAtividade);
        JTextField campoAtividade = new JTextField();
        campoAtividade.setPreferredSize(new Dimension(500, 50));
        campoAtividade.setMaximumSize(new Dimension(500, 50));
        campoAtividade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAtividade.add(campoAtividade);
        painelCampos.add(panelAtividade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelFuncionario = new JPanel();
        panelFuncionario.setLayout(new BoxLayout(panelFuncionario, BoxLayout.Y_AXIS));
        panelFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelFuncionarioId = new JLabel("ID Funcionario:");
        labelFuncionarioId.setFont(new Font("Serif", Font.BOLD, 18));
        labelFuncionarioId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionario.add(labelFuncionarioId);
        JTextField campoFuncionarioId = new JTextField();
        campoFuncionarioId.setPreferredSize(new Dimension(500, 25));
        campoFuncionarioId.setMaximumSize(new Dimension(500, 25));
        campoFuncionarioId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionario.add(campoFuncionarioId);
        painelCampos.add(panelFuncionario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

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
        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    public void Editar(AgendaFuncionario agenda) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS
        JPanel panelAtividade = new JPanel();
        panelAtividade.setLayout(new BoxLayout(panelAtividade, BoxLayout.Y_AXIS));
        panelAtividade.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelAtividade = new JLabel("Atividade:");
        labelAtividade.setFont(new Font("Serif", Font.BOLD, 18));
        labelAtividade.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoAtividade = new JTextField(agenda.getAtividade());
        campoAtividade.setPreferredSize(new Dimension(500, 50));
        campoAtividade.setMaximumSize(new Dimension(500, 50));
        campoAtividade.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelAtividade.add(labelAtividade);
        panelAtividade.add(campoAtividade);
        painelCampos.add(panelAtividade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelFuncionario = new JPanel();
        panelFuncionario.setLayout(new BoxLayout(panelFuncionario, BoxLayout.Y_AXIS));
        panelFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelFuncionarioId = new JLabel("ID Funcionario:"); // CORRIGIDO nome da variável
        labelFuncionarioId.setFont(new Font("Serif", Font.BOLD, 18));
        labelFuncionarioId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoFuncionarioId = new JTextField(
                agenda.getFuncionario() != null && agenda.getFuncionario().getId() != null
                        ? String.valueOf(agenda.getFuncionario().getId())
                        : "");
        campoFuncionarioId.setPreferredSize(new Dimension(500, 25));
        campoFuncionarioId.setMaximumSize(new Dimension(500, 25));
        campoFuncionarioId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelFuncionario.add(labelFuncionarioId);
        panelFuncionario.add(campoFuncionarioId);
        painelCampos.add(panelFuncionario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // ADICIONAR PAINEL DE CAMPOS AO PAINEL PRINCIPAL
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
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agenda.setAtividade(campoAtividade.getText());

                // Atualizar Funcionario
                try {
                    String funcionarioIdTexto = campoFuncionarioId.getText().trim();
                    if (!funcionarioIdTexto.isEmpty()) {
                        if (agenda.getFuncionario() == null)
                            agenda.setFuncionario(new Funcionario());
                        agenda.getFuncionario().setId(Integer.parseInt(funcionarioIdTexto));
                    } else {
                        agenda.setFuncionario(null);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO: usar this
                            "ID do Funcionario inválido! Digite apenas números.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    AgendaFuncionarioController.editarAgendaFuncionario(agenda);
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO: usar this
                            "Registro atualizado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    mostrarMenuPrincipal(); // VOLTAR AO MENU APÓS SALVAR
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO: usar this
                            "Erro ao atualizar no banco de dados!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoCancelar.addActionListener(e -> mostrarMenuPrincipal());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // telaBuscaIDEdicao - REMOVER "static"
    public void telaBuscaIDEdicao() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Busca da Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelId = new JLabel("Coloque o ID da Agenda Escolhida:"); // CORRIGIDO texto
        labelId.setFont(new Font("Serif", Font.BOLD, 20));
        labelId.setForeground(Color.BLACK);
        labelId.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        // BOTÕES
        JButton botaoVerificar = new JButton("Verificar");
        botaoVerificar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVerificar.setBackground(Color.BLACK);
        botaoVerificar.setForeground(Color.WHITE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);

        painelInput.add(campoID);
        painelInput.add(botaoVerificar);
        painelInput.add(botaoVoltar);

        painelCentral.add(labelId);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipalInterno.add(painelCentral, BorderLayout.CENTER);

        // ACTION
        botaoVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = campoID.getText().trim();

                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                            "Por favor, digite um ID válido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);

                    AgendaFuncionarioController controller = new AgendaFuncionarioController();
                    AgendaFuncionario agenda = controller.selecionarAgendaFuncionario(id);

                    if (agenda != null) {
                        JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                                "Agenda carregada com sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                        Editar(agenda); // Abre a tela com os dados preenchidos
                    } else {
                        JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                                "Nenhuma agenda encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                            "Por favor, digite um número válido para o ID!",
                            "Erro de Formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        campoID.addActionListener(e -> botaoVerificar.doClick());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // telaBuscaIDRemover_Remover - REMOVER "static"
    public void telaBuscaIDRemover_Remover() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Remover Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelId = new JLabel("Coloque o ID da Agenda a ser Removida:");
        labelId.setFont(new Font("Serif", Font.BOLD, 20));
        labelId.setForeground(Color.BLACK);
        labelId.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        // BOTÕES
        JButton botaoRemover = new JButton("Remover");
        botaoRemover.setFont(new Font("Serif", Font.BOLD, 16));
        botaoRemover.setBackground(Color.RED);
        botaoRemover.setForeground(Color.WHITE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);

        painelInput.add(campoID);
        painelInput.add(botaoRemover);
        painelInput.add(botaoVoltar);

        painelCentral.add(labelId);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipalInterno.add(painelCentral, BorderLayout.CENTER);

        // ACTION
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = campoID.getText().trim();

                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                            "Por favor, digite um ID válido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);

                    AgendaFuncionarioController controller = new AgendaFuncionarioController();
                    AgendaFuncionario agenda = controller.selecionarAgendaFuncionario(id);

                    if (agenda != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                AgendaFuncionarioPanel.this, // CORRIGIDO
                                "Tem certeza que deseja remover a agenda com ID: " + id + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deletarAgendaFuncionario(id);
                                JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                                        "Agenda removida com sucesso!",
                                        "Sucesso",
                                        JOptionPane.INFORMATION_MESSAGE);
                                mostrarMenuPrincipal(); // VOLTAR AO MENU APÓS REMOVER
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                                        "Erro ao remover a agenda: " + ex.getMessage(),
                                        "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                                "Nenhuma agenda encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                            "Por favor, digite um número válido para o ID!",
                            "Erro de Formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        campoID.addActionListener(e -> botaoRemover.doClick());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // Selecionar - REMOVER "static" e usar painelPrincipalInterno
    public void Selecionar(AgendaFuncionario agenda) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (agenda == null) {
            JOptionPane.showMessageDialog(this, // CORRIGIDO
                    "Agenda não encontrada!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            mostrarMenuPrincipal();
            return;
        }

        // Título
        JLabel labelTitulo = new JLabel("Informações da Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(3, 2, 10, 10)); // CORRIGIDO: 3 linhas (ID, Atividade,
                                                                       // Funcionario)
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Criar campos de texto não editáveis para mostrar os dados
        JTextField campoID = new JTextField(String.valueOf(agenda.getId()));
        campoID.setFont(new Font("Serif", Font.PLAIN, 14));
        campoID.setEditable(false);
        campoID.setBackground(Color.WHITE);
        campoID.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField campoAtividade = new JTextField(agenda.getAtividade());
        campoAtividade.setFont(new Font("Serif", Font.PLAIN, 14));
        campoAtividade.setEditable(false);
        campoAtividade.setBackground(Color.WHITE);
        campoAtividade.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Para objetos complexos, mostrar toString() a informação relevante
        String funcionarioInfo = agenda.getFuncionario() != null ? agenda.getFuncionario().toString() : "Não informado";
        JTextField campoFuncionario = new JTextField(funcionarioInfo);
        campoFuncionario.setFont(new Font("Serif", Font.PLAIN, 14));
        campoFuncionario.setEditable(false);
        campoFuncionario.setBackground(Color.WHITE);
        campoFuncionario.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Criar labels
        JLabel labelID = new JLabel("ID:");
        labelID.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelAtividade = new JLabel("Atividade:");
        labelAtividade.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelFuncionario = new JLabel("Funcionario:");
        labelFuncionario.setFont(new Font("Serif", Font.BOLD, 14));

        // Agregar etiquetas e campos ao panel
        painelDados.add(labelID);
        painelDados.add(campoID);
        painelDados.add(labelAtividade);
        painelDados.add(campoAtividade);
        painelDados.add(labelFuncionario);
        painelDados.add(campoFuncionario);

        painelPrincipalInterno.add(painelDados, BorderLayout.CENTER);

        // BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        painelBotoes.add(botaoVoltar);
        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // telaBuscaIDSelecionar - REMOVER "static"
    public void telaBuscaIDSelecionar() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Visualizar Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelId = new JLabel("Coloque o ID da Agenda a ser Visualizada:");
        labelId.setFont(new Font("Serif", Font.BOLD, 20));
        labelId.setForeground(Color.BLACK);
        labelId.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        // BOTÕES
        JButton botaoVisualizar = new JButton("Visualizar"); // CORRIGIDO nome da variável
        botaoVisualizar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVisualizar.setBackground(Color.BLUE);
        botaoVisualizar.setForeground(Color.WHITE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);

        painelInput.add(campoID);
        painelInput.add(botaoVisualizar);
        painelInput.add(botaoVoltar);

        painelCentral.add(labelId);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipalInterno.add(painelCentral, BorderLayout.CENTER);

        // ACTION
        botaoVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = campoID.getText().trim();

                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                            "Por favor, digite um ID válido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);

                    AgendaFuncionarioController controller = new AgendaFuncionarioController();
                    AgendaFuncionario agenda = controller.selecionarAgendaFuncionario(id);

                    if (agenda != null) {
                        Selecionar(agenda); // Chama ao metodo selecionar
                    } else {
                        JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                                "Nenhuma agenda encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AgendaFuncionarioPanel.this, // CORRIGIDO
                            "Por favor, digite um número válido para o ID!",
                            "Erro de Formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        campoID.addActionListener(e -> botaoVisualizar.doClick());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // Listar - REMOVER "static"
    public void Listar() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Listar Todas as Agendas");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        // Panel central com a tabela
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            AgendaFuncionarioController controller = new AgendaFuncionarioController();
            java.util.List<AgendaFuncionario> agendas = controller.listarAgendaFuncionarios();

            if (agendas == null || agendas.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhuma agenda cadastrada no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                // CORRIGIDO: Colunas da tabela - ajustadas para AgendaFuncionario
                String[] colunas = { "ID", "Atividade", "Funcionario" }; // CORRIGIDO: só 3 colunas relevantes
                Object[][] dados = new Object[agendas.size()][3];

                for (int i = 0; i < agendas.size(); i++) {
                    AgendaFuncionario agenda = agendas.get(i);
                    dados[i][0] = agenda.getId();
                    dados[i][1] = agenda.getAtividade() != null ? agenda.getAtividade() : "";
                    dados[i][2] = agenda.getFuncionario() != null ? agenda.getFuncionario().toString()
                            : "Não informado";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                // Configurar largura das colunas
                tabela.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
                tabela.getColumnModel().getColumn(1).setPreferredWidth(200); // Atividade
                tabela.getColumnModel().getColumn(2).setPreferredWidth(250); // Funcionario

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                // Etiqueta com quantidade de registros
                JLabel labelContador = new JLabel("Total de agendas encontradas: " + agendas.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar agendas: " + ex.getMessage());
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
        botaoAtualizar.addActionListener(e -> Listar()); // Chama o mesmo método

        JButton botaoVoltar = new JButton("Voltar ao Menu");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(botaoVoltar);

        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

}
