package br.edu.ifpr.view.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
//import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
//import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.RotinaAlimentarController;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;

public class RotinaAlimentarPanel extends JPanel {

    private JPanel painelPrincipalInterno; // Para gerenciar sub-painel dentro do panel principal

    public RotinaAlimentarPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarLogin();
    }

    // MOSTRAR MENU PRINCIPAL
    // ________________________________________________________
    private void mostrarLogin() {
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
        btnVoltar.addActionListener(e -> mostrarLogin()); // Depois você pode trocar para voltar ao MainFrame
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

        JLabel labelTitulo = new JLabel("Cadastro de Rotina Alimentar");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS
        JPanel panelData = new JPanel();
        panelData.setLayout(new BoxLayout(panelData, BoxLayout.Y_AXIS));
        panelData.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelData = new JLabel("Data:");
        labelData.setFont(new Font("Serif", Font.BOLD, 18));
        labelData.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelData.add(labelData);
        JTextField campoData = new JTextField();
        campoData.setPreferredSize(new Dimension(500, 50));
        campoData.setMaximumSize(new Dimension(500, 50));
        campoData.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelData.add(campoData);
        painelCampos.add(panelData);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelHora = new JPanel();
        panelHora.setLayout(new BoxLayout(panelHora, BoxLayout.Y_AXIS));
        panelHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHora = new JLabel("Hora:");
        labelHora.setFont(new Font("Serif", Font.BOLD, 18));
        labelHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHora.add(labelHora);
        JTextField campoHora = new JTextField();
        campoHora.setPreferredSize(new Dimension(500, 50));
        campoHora.setMaximumSize(new Dimension(500, 50));
        campoHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHora.add(campoHora);
        painelCampos.add(panelHora);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelQtdAlimento = new JPanel();
        panelQtdAlimento.setLayout(new BoxLayout(panelQtdAlimento, BoxLayout.Y_AXIS));
        panelQtdAlimento.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelQtdAlimento = new JLabel("Quantidade alimento:");
        labelQtdAlimento.setFont(new Font("Serif", Font.BOLD, 18));
        labelQtdAlimento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQtdAlimento.add(labelQtdAlimento);
        JTextField campoQtdAlimento = new JTextField();
        campoQtdAlimento.setPreferredSize(new Dimension(500, 50));
        campoQtdAlimento.setMaximumSize(new Dimension(500, 50));
        campoQtdAlimento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQtdAlimento.add(campoQtdAlimento);
        painelCampos.add(panelQtdAlimento);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelAgendaAnimal = new JPanel();
        panelAgendaAnimal.setLayout(new BoxLayout(panelAgendaAnimal, BoxLayout.Y_AXIS));
        panelAgendaAnimal.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAgendaAnimalId = new JLabel("Id Agenda Animal:");
        labelAgendaAnimalId.setFont(new Font("Serif", Font.BOLD, 18));
        labelAgendaAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgendaAnimal.add(labelAgendaAnimalId);
        JTextField campoAgendaAnimalId = new JTextField();
        campoAgendaAnimalId.setPreferredSize(new Dimension(500, 25));
        campoAgendaAnimalId.setMaximumSize(new Dimension(500, 25));
        campoAgendaAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgendaAnimal.add(campoAgendaAnimalId);
        painelCampos.add(panelAgendaAnimal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

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
        botaoVoltar.addActionListener(e -> mostrarLogin());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    public void Editar(RotinaAlimentar rotinaAlimentar) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Rotina Alimentar");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Campo Data
        JPanel panelData = new JPanel();
        panelData.setLayout(new BoxLayout(panelData, BoxLayout.Y_AXIS));
        panelData.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelData = new JLabel("Data (DD/MM/AAAA):");
        labelData.setFont(new Font("Serif", Font.BOLD, 18));
        labelData.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelData.add(labelData);
        JTextField campoData = new JTextField(rotinaAlimentar.getData() != null ? rotinaAlimentar.getData() : "");
        campoData.setPreferredSize(new Dimension(500, 40));
        campoData.setMaximumSize(new Dimension(500, 40));
        campoData.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelData.add(campoData);
        painelCampos.add(panelData);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Hora
        JPanel panelHora = new JPanel();
        panelHora.setLayout(new BoxLayout(panelHora, BoxLayout.Y_AXIS));
        panelHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHora = new JLabel("Hora (HH:MM):");
        labelHora.setFont(new Font("Serif", Font.BOLD, 18));
        labelHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHora.add(labelHora);
        JTextField campoHora = new JTextField(rotinaAlimentar.getHora() != null ? rotinaAlimentar.getHora() : "");
        campoHora.setPreferredSize(new Dimension(500, 40));
        campoHora.setMaximumSize(new Dimension(500, 40));
        campoHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHora.add(campoHora);
        painelCampos.add(panelHora);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Quantidade de Alimento
        JPanel panelQuantidadeAlimento = new JPanel();
        panelQuantidadeAlimento.setLayout(new BoxLayout(panelQuantidadeAlimento, BoxLayout.Y_AXIS));
        panelQuantidadeAlimento.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelQuantidadeAlimento = new JLabel("Quantidade de Alimento (kg/litros):");
        labelQuantidadeAlimento.setFont(new Font("Serif", Font.BOLD, 18));
        labelQuantidadeAlimento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQuantidadeAlimento.add(labelQuantidadeAlimento);
        JTextField campoQuantidadeAlimento = new JTextField(
                rotinaAlimentar.getQuantidadeAlimento() != null ? rotinaAlimentar.getQuantidadeAlimento() : "");
        campoQuantidadeAlimento.setPreferredSize(new Dimension(500, 40));
        campoQuantidadeAlimento.setMaximumSize(new Dimension(500, 40));
        campoQuantidadeAlimento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQuantidadeAlimento.add(campoQuantidadeAlimento);
        painelCampos.add(panelQuantidadeAlimento);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID da AgendaAnimal
        JPanel panelAgendaAnimal = new JPanel();
        panelAgendaAnimal.setLayout(new BoxLayout(panelAgendaAnimal, BoxLayout.Y_AXIS));
        panelAgendaAnimal.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAgendaAnimal = new JLabel("ID da Agenda do Animal:");
        labelAgendaAnimal.setFont(new Font("Serif", Font.BOLD, 18));
        labelAgendaAnimal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgendaAnimal.add(labelAgendaAnimal);
        String agendaAnimalId = rotinaAlimentar.getAgendaAnimal() != null
                ? String.valueOf(rotinaAlimentar.getAgendaAnimal().getId())
                : "";
        JTextField campoAgendaAnimal = new JTextField(agendaAnimalId);
        campoAgendaAnimal.setPreferredSize(new Dimension(500, 40));
        campoAgendaAnimal.setMaximumSize(new Dimension(500, 40));
        campoAgendaAnimal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAgendaAnimal.add(campoAgendaAnimal);

        painelCampos.add(panelAgendaAnimal);

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
                rotinaAlimentar.setData(campoData.getText().trim());
                rotinaAlimentar.setHora(campoHora.getText().trim());
                rotinaAlimentar.setQuantidadeAlimento(campoQuantidadeAlimento.getText().trim());

                RotinaAlimentarController controller = new RotinaAlimentarController();
                controller.editarRotinaAlimentar(rotinaAlimentar);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Rotina Alimentar atualizada com sucesso!",
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
        criarTelaBuscaID("Editar Rotina Alimentar", "Digite o ID da Rotina Alimentar para Editar:", "Buscar",
                (id) -> {
                    RotinaAlimentarController controller = new RotinaAlimentarController();
                    RotinaAlimentar rotinaAlimentar = controller.selecionarRotinaAlimentar(id);
                    if (rotinaAlimentar != null) {
                        Editar(rotinaAlimentar);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhuma rotina alimentar encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Rotina Alimentar", "Digite o ID da Rotina Alimentar para Remover:", "Remover",
                (id) -> {
                    RotinaAlimentarController controller = new RotinaAlimentarController();
                    RotinaAlimentar rotinaAlimentar = controller.selecionarRotinaAlimentar(id);
                    if (rotinaAlimentar != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                MainFrame.getJanela(),
                                "Tem certeza que deseja remover a rotina alimentar de ID: " + rotinaAlimentar.getId()
                                        + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deleteRotinaAlimentar(id);
                                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                        "Rotina Alimentar removida com sucesso!",
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
                                "Nenhuma rotina alimentar encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Rotina Alimentar", "Digite o ID da Rotina Alimentar para Visualizar:",
                "Visualizar",
                (id) -> {
                    RotinaAlimentarController controller = new RotinaAlimentarController();
                    RotinaAlimentar rotinaAlimentar = controller.selecionarRotinaAlimentar(id);
                    if (rotinaAlimentar != null) {
                        Selecionar(rotinaAlimentar);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhuma rotina alimentar encontrada com ID: " + id,
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
    public void Selecionar(RotinaAlimentar rotinaAlimentar) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (rotinaAlimentar == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Rotina Alimentar não encontrada!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações da Rotina Alimentar");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(6, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(rotinaAlimentar.getId()));
        JTextField campoData = criarCampoReadOnly(
                rotinaAlimentar.getData() != null ? rotinaAlimentar.getData() : "Não informado");
        JTextField campoHora = criarCampoReadOnly(
                rotinaAlimentar.getHora() != null ? rotinaAlimentar.getHora() : "Não informado");
        JTextField campoQuantidadeAlimento = criarCampoReadOnly(
                rotinaAlimentar.getQuantidadeAlimento() != null ? rotinaAlimentar.getQuantidadeAlimento()
                        : "Não informado");

        String agendaAnimalInfo = rotinaAlimentar.getAgendaAnimal() != null
                ? "ID: " + rotinaAlimentar.getAgendaAnimal().getId()
                : "Não informado";
        JTextField campoAgendaAnimal = criarCampoReadOnly(agendaAnimalInfo);

        // Construir lista de alimentos
        StringBuilder alimentosInfo = new StringBuilder();
        if (rotinaAlimentar.getAlimentos() != null && !rotinaAlimentar.getAlimentos().isEmpty()) {
            alimentosInfo.append(rotinaAlimentar.getAlimentos().size()).append(" alimento(s)");
        } else {
            alimentosInfo.append("Nenhum alimento associado");
        }
        JTextField campoAlimentos = criarCampoReadOnly(alimentosInfo.toString());

        // Labels
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Data:"));
        painelDados.add(campoData);
        painelDados.add(new JLabel("Hora:"));
        painelDados.add(campoHora);
        painelDados.add(new JLabel("Quantidade de Alimento:"));
        painelDados.add(campoQuantidadeAlimento);
        painelDados.add(new JLabel("Agenda do Animal:"));
        painelDados.add(campoAgendaAnimal);
        painelDados.add(new JLabel("Alimentos:"));
        painelDados.add(campoAlimentos);

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

        JLabel labelTitulo = new JLabel("Listar Todas as Rotinas Alimentares");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            RotinaAlimentarController controller = new RotinaAlimentarController();
            java.util.List<RotinaAlimentar> rotinasAlimentares = controller.listarRotinasAlimentares();

            if (rotinasAlimentares == null || rotinasAlimentares.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhuma rotina alimentar cadastrada no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = { "ID", "Data", "Hora", "Quantidade", "Agenda Animal ID", "Nº Alimentos" };
                Object[][] dados = new Object[rotinasAlimentares.size()][6];

                for (int i = 0; i < rotinasAlimentares.size(); i++) {
                    RotinaAlimentar rotinaAlimentar = rotinasAlimentares.get(i);
                    dados[i][0] = rotinaAlimentar.getId();
                    dados[i][1] = rotinaAlimentar.getData() != null ? rotinaAlimentar.getData() : "";
                    dados[i][2] = rotinaAlimentar.getHora() != null ? rotinaAlimentar.getHora() : "";
                    dados[i][3] = rotinaAlimentar.getQuantidadeAlimento() != null
                            ? rotinaAlimentar.getQuantidadeAlimento()
                            : "";
                    dados[i][4] = rotinaAlimentar.getAgendaAnimal() != null ? rotinaAlimentar.getAgendaAnimal().getId()
                            : "";
                    dados[i][5] = rotinaAlimentar.getAlimentos() != null ? rotinaAlimentar.getAlimentos().size() : 0;
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(5).setPreferredWidth(80);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel(
                        "Total de rotinas alimentares encontradas: " + rotinasAlimentares.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar rotinas alimentares: " + ex.getMessage());
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