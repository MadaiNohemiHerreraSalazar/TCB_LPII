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
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.VeterinarioController;
import br.edu.ifpr.zoologicio.model.Veterinario;

public class VeterinarioPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public VeterinarioPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarMenuPrincipal();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarMenuPrincipal() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Veterinários");
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

        JLabel labelTitulo = new JLabel("Cadastro de Veterinário");
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
        JLabel labelNome = new JLabel("Nome do Veterinário:");
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

        // Campo Especialização
        JPanel panelEspecializacao = new JPanel();
        panelEspecializacao.setLayout(new BoxLayout(panelEspecializacao, BoxLayout.Y_AXIS));
        panelEspecializacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEspecializacao = new JLabel("Especialização:");
        labelEspecializacao.setFont(new Font("Serif", Font.BOLD, 18));
        labelEspecializacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecializacao.add(labelEspecializacao);
        JTextField campoEspecializacao = new JTextField();
        campoEspecializacao.setPreferredSize(new Dimension(500, 40));
        campoEspecializacao.setMaximumSize(new Dimension(500, 40));
        campoEspecializacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecializacao.add(campoEspecializacao);
        painelCampos.add(panelEspecializacao);

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
                Veterinario veterinario = new Veterinario();
                veterinario.setNome(campoNome.getText().trim());
                veterinario.setCpf(campoCPF.getText().trim());
                veterinario.setEmail(campoEmail.getText().trim());
                veterinario.setEspecializacao(campoEspecializacao.getText().trim());

                VeterinarioController controller = new VeterinarioController();
                controller.cadastrarVeterinario(veterinario);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Veterinário cadastrado com sucesso!",
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
    public void Editar(Veterinario veterinario) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Veterinário");
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
        JLabel labelNome = new JLabel("Nome do Veterinário:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(veterinario.getNome());
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
        JTextField campoCPF = new JTextField(veterinario.getCpf());
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
        JTextField campoEmail = new JTextField(veterinario.getEmail());
        campoEmail.setPreferredSize(new Dimension(500, 40));
        campoEmail.setMaximumSize(new Dimension(500, 40));
        campoEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEmail.add(campoEmail);
        painelCampos.add(panelEmail);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Especialização
        JPanel panelEspecializacao = new JPanel();
        panelEspecializacao.setLayout(new BoxLayout(panelEspecializacao, BoxLayout.Y_AXIS));
        panelEspecializacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEspecializacao = new JLabel("Especialização:");
        labelEspecializacao.setFont(new Font("Serif", Font.BOLD, 18));
        labelEspecializacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecializacao.add(labelEspecializacao);
        JTextField campoEspecializacao = new JTextField(veterinario.getEspecializacao());
        campoEspecializacao.setPreferredSize(new Dimension(500, 40));
        campoEspecializacao.setMaximumSize(new Dimension(500, 40));
        campoEspecializacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecializacao.add(campoEspecializacao);
        painelCampos.add(panelEspecializacao);

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
            try {
                veterinario.setNome(campoNome.getText().trim());
                veterinario.setCpf(campoCPF.getText().trim());
                veterinario.setEmail(campoEmail.getText().trim());
                veterinario.setEspecializacao(campoEspecializacao.getText().trim());

                VeterinarioController controller = new VeterinarioController();
                controller.editarVeterinario(veterinario);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Veterinário atualizado com sucesso!",
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
        criarTelaBuscaID("Editar Veterinário", "Digite o ID do Veterinário para Editar:", "Buscar",
            (id) -> {
                VeterinarioController controller = new VeterinarioController();
                Veterinario veterinario = controller.selecionarVeterinario(id);
                if (veterinario != null) {
                    Editar(veterinario);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum veterinário encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Veterinário", "Digite o ID do Veterinário para Remover:", "Remover",
            (id) -> {
                VeterinarioController controller = new VeterinarioController();
                Veterinario veterinario = controller.selecionarVeterinario(id);
                if (veterinario != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(
                            MainFrame.getJanela(),
                            "Tem certeza que deseja remover o veterinário: " + veterinario.getNome() + "?",
                            "Confirmar Remoção",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        try {
                            controller.deleteVeterinario(id);
                            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                    "Veterinário removido com sucesso!",
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
                            "Nenhum veterinário encontrado com ID: " + id,
                            "Não Encontrado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Veterinário", "Digite o ID do Veterinário para Visualizar:", "Visualizar",
            (id) -> {
                VeterinarioController controller = new VeterinarioController();
                Veterinario veterinario = controller.selecionarVeterinario(id);
                if (veterinario != null) {
                    Selecionar(veterinario);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Nenhum veterinário encontrado com ID: " + id,
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
    public void Selecionar(Veterinario veterinario) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (veterinario == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Veterinário não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações do Veterinário");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(5, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(veterinario.getId()));
        JTextField campoNome = criarCampoReadOnly(veterinario.getNome());
        JTextField campoCPF = criarCampoReadOnly(veterinario.getCpf());
        JTextField campoEmail = criarCampoReadOnly(veterinario.getEmail());
        JTextField campoEspecializacao = criarCampoReadOnly(veterinario.getEspecializacao());

        // Labels
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("CPF:"));
        painelDados.add(campoCPF);
        painelDados.add(new JLabel("Email:"));
        painelDados.add(campoEmail);
        painelDados.add(new JLabel("Especialização:"));
        painelDados.add(campoEspecializacao);

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

        JLabel labelTitulo = new JLabel("Listar Todos os Veterinários");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            VeterinarioController controller = new VeterinarioController();
            java.util.List<Veterinario> veterinarios = controller.listarVeterinarios();

            if (veterinarios == null || veterinarios.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhum veterinário cadastrado no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = {"ID", "Nome", "CPF", "Email", "Especialização"};
                Object[][] dados = new Object[veterinarios.size()][5];

                for (int i = 0; i < veterinarios.size(); i++) {
                    Veterinario veterinario = veterinarios.get(i);
                    dados[i][0] = veterinario.getId();
                    dados[i][1] = veterinario.getNome() != null ? veterinario.getNome() : "";
                    dados[i][2] = veterinario.getCpf() != null ? veterinario.getCpf() : "";
                    dados[i][3] = veterinario.getEmail() != null ? veterinario.getEmail() : "";
                    dados[i][4] = veterinario.getEspecializacao() != null ? veterinario.getEspecializacao() : "";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(200);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(150);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de veterinários encontrados: " + veterinarios.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar veterinários: " + ex.getMessage());
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