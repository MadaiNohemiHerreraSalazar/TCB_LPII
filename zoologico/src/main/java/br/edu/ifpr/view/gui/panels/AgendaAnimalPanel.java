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
//mport javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.AgendaAnimalController;
import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Veterinario;
import br.edu.ifpr.zoologicio.model.RotinaAlimentar;

public class AgendaAnimalPanel extends JPanel {

    private JPanel painelPrincipalInterno; // Para gerenciar sub-painel dentro do panel principal

    public AgendaAnimalPanel() {
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
        JLabel labelTitulo = new JLabel(" Gerenciamento da Agenda de Animais");
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
        painelBotoes.setLayout(new GridLayout(5, 1, 20, 20)); // 5 linhas, 1 coluna
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
        btnVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO
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

    // MÉTODOS CRUD PARA AgendaAnimalPanel

    // Cadastro - REMOVER "static" e usar painelPrincipalInterno
    public void Cadastro() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Cadastro de Agenda Animal");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS
        JPanel panelConsulta = new JPanel();
        panelConsulta.setLayout(new BoxLayout(panelConsulta, BoxLayout.Y_AXIS));
        panelConsulta.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelConsulta = new JLabel("Consulta:");
        labelConsulta.setFont(new Font("Serif", Font.BOLD, 18));
        labelConsulta.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelConsulta.add(labelConsulta);
        JTextField campoConsulta = new JTextField();
        campoConsulta.setPreferredSize(new Dimension(500, 50));
        campoConsulta.setMaximumSize(new Dimension(500, 50));
        campoConsulta.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelConsulta.add(campoConsulta);
        painelCampos.add(panelConsulta);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelBanho = new JPanel();
        panelBanho.setLayout(new BoxLayout(panelBanho, BoxLayout.Y_AXIS));
        panelBanho.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelBanho = new JLabel("Banho:");
        labelBanho.setFont(new Font("Serif", Font.BOLD, 18));
        labelBanho.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBanho.add(labelBanho);
        JTextField campoBanho = new JTextField();
        campoBanho.setPreferredSize(new Dimension(500, 50));
        campoBanho.setMaximumSize(new Dimension(500, 50));
        campoBanho.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBanho.add(campoBanho);
        painelCampos.add(panelBanho);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelMedicacao = new JPanel();
        panelMedicacao.setLayout(new BoxLayout(panelMedicacao, BoxLayout.Y_AXIS));
        panelMedicacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelMedicacao = new JLabel("Medicação:");
        labelMedicacao.setFont(new Font("Serif", Font.BOLD, 18));
        labelMedicacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMedicacao.add(labelMedicacao);
        JTextField campoMedicacao = new JTextField();
        campoMedicacao.setPreferredSize(new Dimension(500, 50));
        campoMedicacao.setMaximumSize(new Dimension(500, 50));
        campoMedicacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMedicacao.add(campoMedicacao);
        painelCampos.add(panelMedicacao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

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

        JPanel panelAnimal = new JPanel();
        panelAnimal.setLayout(new BoxLayout(panelAnimal, BoxLayout.Y_AXIS));
        panelAnimal.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAnimalId = new JLabel("ID Animal:");
        labelAnimalId.setFont(new Font("Serif", Font.BOLD, 18));
        labelAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimal.add(labelAnimalId);
        JTextField campoAnimalId = new JTextField();
        campoAnimalId.setPreferredSize(new Dimension(500, 25));
        campoAnimalId.setMaximumSize(new Dimension(500, 25));
        campoAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimal.add(campoAnimalId);
        painelCampos.add(panelAnimal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelVeterinario = new JPanel();
        panelVeterinario.setLayout(new BoxLayout(panelVeterinario, BoxLayout.Y_AXIS));
        panelVeterinario.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelVeterinarioId = new JLabel("ID Veterinário:");
        labelVeterinarioId.setFont(new Font("Serif", Font.BOLD, 18));
        labelVeterinarioId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVeterinario.add(labelVeterinarioId);
        JTextField campoVeterinarioId = new JTextField();
        campoVeterinarioId.setPreferredSize(new Dimension(500, 25));
        campoVeterinarioId.setMaximumSize(new Dimension(500, 25));
        campoVeterinarioId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVeterinario.add(campoVeterinarioId);
        painelCampos.add(panelVeterinario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelRotina = new JPanel();
        panelRotina.setLayout(new BoxLayout(panelRotina, BoxLayout.Y_AXIS));
        panelRotina.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelRotinaId = new JLabel("ID Rotina Alimentar:");
        labelRotinaId.setFont(new Font("Serif", Font.BOLD, 18));
        labelRotinaId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRotina.add(labelRotinaId);
        JTextField campoRotinaId = new JTextField();
        campoRotinaId.setPreferredSize(new Dimension(500, 25));
        campoRotinaId.setMaximumSize(new Dimension(500, 25));
        campoRotinaId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRotina.add(campoRotinaId);
        painelCampos.add(panelRotina);

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
                AgendaAnimal agenda = new AgendaAnimal();
                agenda.setConsulta(campoConsulta.getText().trim());
                agenda.setBanho(campoBanho.getText().trim());
                agenda.setMedicacao(campoMedicacao.getText().trim());
                agenda.setAtividade(campoAtividade.getText().trim());

                // ID Animal
                if (!campoAnimalId.getText().trim().isEmpty()) {
                    Animal animal = new Animal();
                    animal.setId(Integer.parseInt(campoAnimalId.getText().trim()));
                    agenda.setAnimal(animal);
                }

                // ID Veterinário
                if (!campoVeterinarioId.getText().trim().isEmpty()) {
                    Veterinario vet = new Veterinario();
                    vet.setId(Integer.parseInt(campoVeterinarioId.getText().trim()));
                    agenda.setVeterinario(vet);
                }

                // ID Rotina Alimentar
                if (!campoRotinaId.getText().trim().isEmpty()) {
                    RotinaAlimentar rotina = new RotinaAlimentar();
                    rotina.setId(Integer.parseInt(campoRotinaId.getText().trim()));
                    agenda.setRotinaAlimentar(rotina);
                }

                // Salvar
                AgendaAnimalController controller = new AgendaAnimalController();
                controller.cadastrarAgendaAnimal(agenda);

                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "Agenda cadastrada com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                MainFrame.voltarAoMenuPrincipal(); // VOLTAR AO MENU APÓS SALVAR

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "IDs devem conter apenas números!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "Erro ao salvar: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // Editar - REMOVER "static" e usar painelPrincipalInterno
    public void Editar(AgendaAnimal agenda) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Agenda Animal");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS
        JPanel panelConsulta = new JPanel();
        panelConsulta.setLayout(new BoxLayout(panelConsulta, BoxLayout.Y_AXIS));
        panelConsulta.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelConsulta = new JLabel("Consulta:");
        labelConsulta.setFont(new Font("Serif", Font.BOLD, 18));
        labelConsulta.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoConsulta = new JTextField(agenda.getConsulta());
        campoConsulta.setPreferredSize(new Dimension(500, 50));
        campoConsulta.setMaximumSize(new Dimension(500, 50));
        campoConsulta.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelConsulta.add(labelConsulta);
        panelConsulta.add(campoConsulta);
        painelCampos.add(panelConsulta);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelBanho = new JPanel();
        panelBanho.setLayout(new BoxLayout(panelBanho, BoxLayout.Y_AXIS));
        panelBanho.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelBanho = new JLabel("Banho:");
        labelBanho.setFont(new Font("Serif", Font.BOLD, 18));
        labelBanho.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoBanho = new JTextField(agenda.getBanho());
        campoBanho.setPreferredSize(new Dimension(500, 50));
        campoBanho.setMaximumSize(new Dimension(500, 50));
        campoBanho.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelBanho.add(labelBanho);
        panelBanho.add(campoBanho);
        painelCampos.add(panelBanho);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelMedicacao = new JPanel();
        panelMedicacao.setLayout(new BoxLayout(panelMedicacao, BoxLayout.Y_AXIS));
        panelMedicacao.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelMedicacao = new JLabel("Medicação:");
        labelMedicacao.setFont(new Font("Serif", Font.BOLD, 18));
        labelMedicacao.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoMedicacao = new JTextField(agenda.getMedicacao());
        campoMedicacao.setPreferredSize(new Dimension(500, 50));
        campoMedicacao.setMaximumSize(new Dimension(500, 50));
        campoMedicacao.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelMedicacao.add(labelMedicacao);
        panelMedicacao.add(campoMedicacao);
        painelCampos.add(panelMedicacao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

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

        JPanel panelAnimal = new JPanel();
        panelAnimal.setLayout(new BoxLayout(panelAnimal, BoxLayout.Y_AXIS));
        panelAnimal.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelAnimalId = new JLabel("ID Animal:");
        labelAnimalId.setFont(new Font("Serif", Font.BOLD, 18));
        labelAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoAnimalId = new JTextField(
                agenda.getAnimal() != null && agenda.getAnimal().getId() != null // CORRIGIDO: getAnimal() não
                                                                                 // getFuncionario()
                        ? String.valueOf(agenda.getAnimal().getId())
                        : "");
        campoAnimalId.setPreferredSize(new Dimension(500, 25));
        campoAnimalId.setMaximumSize(new Dimension(500, 25));
        campoAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelAnimal.add(labelAnimalId);
        panelAnimal.add(campoAnimalId);
        painelCampos.add(panelAnimal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelVeterinario = new JPanel();
        panelVeterinario.setLayout(new BoxLayout(panelVeterinario, BoxLayout.Y_AXIS));
        panelVeterinario.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelVeterinarioId = new JLabel("ID Veterinário:");
        labelVeterinarioId.setFont(new Font("Serif", Font.BOLD, 18));
        labelVeterinarioId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoVeterinarioId = new JTextField(
                agenda.getVeterinario() != null && agenda.getVeterinario().getId() != null
                        ? String.valueOf(agenda.getVeterinario().getId())
                        : "");
        campoVeterinarioId.setPreferredSize(new Dimension(500, 25));
        campoVeterinarioId.setMaximumSize(new Dimension(500, 25));
        campoVeterinarioId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelVeterinario.add(labelVeterinarioId);
        panelVeterinario.add(campoVeterinarioId);
        painelCampos.add(panelVeterinario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelRotina = new JPanel();
        panelRotina.setLayout(new BoxLayout(panelRotina, BoxLayout.Y_AXIS));
        panelRotina.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JLabel labelRotinaId = new JLabel("ID Rotina Alimentar:");
        labelRotinaId.setFont(new Font("Serif", Font.BOLD, 18));
        labelRotinaId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        JTextField campoRotinaId = new JTextField(
                agenda.getRotinaAlimentar() != null && agenda.getRotinaAlimentar().getId() != null
                        ? String.valueOf(agenda.getRotinaAlimentar().getId())
                        : "");
        campoRotinaId.setPreferredSize(new Dimension(500, 25));
        campoRotinaId.setMaximumSize(new Dimension(500, 25));
        campoRotinaId.setAlignmentX(Component.LEFT_ALIGNMENT); // ADICIONADO
        panelRotina.add(labelRotinaId);
        panelRotina.add(campoRotinaId);
        painelCampos.add(panelRotina);

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
            agenda.setConsulta(campoConsulta.getText());
            agenda.setBanho(campoBanho.getText());
            agenda.setMedicacao(campoMedicacao.getText());
            agenda.setAtividade(campoAtividade.getText());

            // Atualizar Animal - CORRIGIDO: agenda.getAnimal() não getFuncionario()
            try {
                String animalIdTexto = campoAnimalId.getText().trim();
                if (!animalIdTexto.isEmpty()) {
                    if (agenda.getAnimal() == null) // CORRIGIDO
                        agenda.setAnimal(new Animal()); // CORRIGIDO
                    agenda.getAnimal().setId(Integer.parseInt(animalIdTexto)); // CORRIGIDO
                } else {
                    agenda.setAnimal(null); // CORRIGIDO
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "ID do Animal inválido! Digite apenas números.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Atualizar Veterinário
            try {
                String vetIdTexto = campoVeterinarioId.getText().trim();
                if (!vetIdTexto.isEmpty()) {
                    if (agenda.getVeterinario() == null)
                        agenda.setVeterinario(new Veterinario());
                    agenda.getVeterinario().setId(Integer.parseInt(vetIdTexto));
                } else {
                    agenda.setVeterinario(null);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "ID do Veterinário inválido! Digite apenas números.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Atualizar Rotina Alimentar
            try {
                String rotinaIdTexto = campoRotinaId.getText().trim();
                if (!rotinaIdTexto.isEmpty()) {
                    if (agenda.getRotinaAlimentar() == null)
                        agenda.setRotinaAlimentar(new RotinaAlimentar());
                    agenda.getRotinaAlimentar().setId(Integer.parseInt(rotinaIdTexto));
                } else {
                    agenda.setRotinaAlimentar(null);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "ID da Rotina Alimentar inválido! Digite apenas números.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                AgendaAnimalController.editarAgendaAnimal(agenda);
                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "Registro atualizado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                MainFrame.voltarAoMenuPrincipal(); // VOLTAR AO MENU APÓS ATUALIZAR
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                        "Erro ao atualizar no banco de dados!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoCancelar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }
    // BUSCAR AGENDA POR ID - PARA A EDIÇÃO
    // ________________________________________________________________

    public void telaBuscaIDEdicao() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Busca da Agenda Animal");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelId = new JLabel("Digite o ID da Agenda Animal:");
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
                    JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                            "Por favor, digite um ID válido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);

                    AgendaAnimalController controller = new AgendaAnimalController();
                    AgendaAnimal agenda = controller.selecionarAgendaAnimal(id);

                    if (agenda != null) {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                                "Agenda carregada com sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                        Editar(agenda); // Abre a tela com os dados preenchidos
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                                "Nenhuma agenda encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                            "Por favor, digite um número válido para o ID!",
                            "Erro de Formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO

        campoID.addActionListener(e -> botaoVerificar.doClick());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // telaBuscaIDRemover_Remover - REMOVER "static" e usar painelPrincipalInterno
    public void telaBuscaIDRemover_Remover() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Remover Agenda Animal");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelId = new JLabel("Digite o ID da Agenda Animal a ser Removida:");
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
                    JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                            "Por favor, digite um ID válido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);

                    AgendaAnimalController controller = new AgendaAnimalController();
                    AgendaAnimal agenda = controller.selecionarAgendaAnimal(id);

                    if (agenda != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                MainFrame.getJanela(), // CORRIGIDO
                                "Tem certeza que deseja remover a agenda animal com ID: " + id + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deletarAgendaAnimal(id);
                                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                                        "Agenda animal removida com sucesso!",
                                        "Sucesso",
                                        JOptionPane.INFORMATION_MESSAGE);
                                MainFrame.voltarAoMenuPrincipal(); // VOLTAR AO MENU APÓS REMOVER
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                                        "Erro ao remover a agenda: " + ex.getMessage(),
                                        "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                                "Nenhuma agenda animal encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                            "Por favor, digite um número válido para o ID!",
                            "Erro de Formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO

        campoID.addActionListener(e -> botaoRemover.doClick());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // Selecionar - REMOVER "static" e usar painelPrincipalInterno
    public void Selecionar(AgendaAnimal agenda) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (agenda == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                    "Agenda não encontrada!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal(); // VOLTAR AO MENU SE AGENDA NULL
            return;
        }

        // Título
        JLabel labelTitulo = new JLabel("Informações da Agenda Animal");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        // CORRIGIDO: GridLayout com 8 linhas (ID, Consulta, Banho, Medicação,
        // Atividade, Animal, Veterinário, Rotina)
        JPanel painelDados = new JPanel(new GridLayout(8, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Criar campos de texto não editáveis para mostrar os dados
        JTextField campoID = new JTextField(String.valueOf(agenda.getId()));
        campoID.setFont(new Font("Serif", Font.PLAIN, 14));
        campoID.setEditable(false);
        campoID.setBackground(Color.WHITE);
        campoID.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField campoConsulta = new JTextField(agenda.getConsulta());
        campoConsulta.setFont(new Font("Serif", Font.PLAIN, 14));
        campoConsulta.setEditable(false);
        campoConsulta.setBackground(Color.WHITE);
        campoConsulta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField campoBanho = new JTextField(agenda.getBanho());
        campoBanho.setFont(new Font("Serif", Font.PLAIN, 14));
        campoBanho.setEditable(false);
        campoBanho.setBackground(Color.WHITE);
        campoBanho.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField campoMedicacao = new JTextField(agenda.getMedicacao());
        campoMedicacao.setFont(new Font("Serif", Font.PLAIN, 14));
        campoMedicacao.setEditable(false);
        campoMedicacao.setBackground(Color.WHITE);
        campoMedicacao.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField campoAtividade = new JTextField(agenda.getAtividade());
        campoAtividade.setFont(new Font("Serif", Font.PLAIN, 14));
        campoAtividade.setEditable(false);
        campoAtividade.setBackground(Color.WHITE);
        campoAtividade.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // CORRIGIDO: agenda.getAnimal() não getFuncionario()
        String animalInfo = agenda.getAnimal() != null ? agenda.getAnimal().toString() : "Não informado";
        JTextField campoAnimal = new JTextField(animalInfo);
        campoAnimal.setFont(new Font("Serif", Font.PLAIN, 14));
        campoAnimal.setEditable(false);
        campoAnimal.setBackground(Color.WHITE);
        campoAnimal.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        String veterinarioInfo = agenda.getVeterinario() != null ? agenda.getVeterinario().toString() : "Não informado";
        JTextField campoVeterinario = new JTextField(veterinarioInfo);
        campoVeterinario.setFont(new Font("Serif", Font.PLAIN, 14));
        campoVeterinario.setEditable(false);
        campoVeterinario.setBackground(Color.WHITE);
        campoVeterinario.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        String rotinaInfo = agenda.getRotinaAlimentar() != null ? agenda.getRotinaAlimentar().toString()
                : "Não informado";
        JTextField campoRotina = new JTextField(rotinaInfo);
        campoRotina.setFont(new Font("Serif", Font.PLAIN, 14));
        campoRotina.setEditable(false);
        campoRotina.setBackground(Color.WHITE);
        campoRotina.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Criar labels
        JLabel labelID = new JLabel("ID:");
        labelID.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelConsulta = new JLabel("Consulta:");
        labelConsulta.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelBanho = new JLabel("Banho:");
        labelBanho.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelMedicacao = new JLabel("Medicação:");
        labelMedicacao.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelAtividade = new JLabel("Atividade:");
        labelAtividade.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelAnimal = new JLabel("Animal:");
        labelAnimal.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelVeterinario = new JLabel("Veterinário:");
        labelVeterinario.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelRotina = new JLabel("Rotina Alimentar:");
        labelRotina.setFont(new Font("Serif", Font.BOLD, 14));

        // Agregar etiquetas e campos ao panel
        painelDados.add(labelID);
        painelDados.add(campoID);
        painelDados.add(labelConsulta);
        painelDados.add(campoConsulta);
        painelDados.add(labelBanho);
        painelDados.add(campoBanho);
        painelDados.add(labelMedicacao);
        painelDados.add(campoMedicacao);
        painelDados.add(labelAtividade);
        painelDados.add(campoAtividade);
        painelDados.add(labelAnimal);
        painelDados.add(campoAnimal);
        painelDados.add(labelVeterinario);
        painelDados.add(campoVeterinario);
        painelDados.add(labelRotina);
        painelDados.add(campoRotina);

        painelPrincipalInterno.add(painelDados, BorderLayout.CENTER);

        // BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO

        painelBotoes.add(botaoVoltar);
        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // telaBuscaIDSelecionar - REMOVER "static" e usar painelPrincipalInterno
    public void telaBuscaIDSelecionar() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Visualizar Agenda Animal");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelId = new JLabel("Digite o ID da Agenda Animal a ser Visualizada:");
        labelId.setFont(new Font("Serif", Font.BOLD, 20));
        labelId.setForeground(Color.BLACK);
        labelId.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        // BOTÕES
        JButton botaoVisualizar = new JButton("Visualizar");
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
                    JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                            "Por favor, digite um ID válido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);

                    AgendaAnimalController controller = new AgendaAnimalController();
                    AgendaAnimal agenda = controller.selecionarAgendaAnimal(id);

                    if (agenda != null) {
                        Selecionar(agenda); // Chama ao metodo selecionar
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                                "Nenhuma agenda animal encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(), // CORRIGIDO
                            "Por favor, digite um número válido para o ID!",
                            "Erro de Formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO

        campoID.addActionListener(e -> botaoVisualizar.doClick());

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }

    // Listar - REMOVER "static" e usar painelPrincipalInterno
    public void Listar() {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Listar Todas as Agendas Animais");
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
            AgendaAnimalController controller = new AgendaAnimalController();
            java.util.List<AgendaAnimal> agendas = controller.listarAgendaAnimais();

            if (agendas == null || agendas.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhuma agenda animal cadastrada no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                // Criar tabela com os dados - CORRIGIDO: agenda.getAnimal() não
                // getFuncionario()
                String[] colunas = { "ID", "Consulta", "Banho", "Medicação", "Atividade", "Animal", "Veterinário",
                        "Rotina Alimentar" };
                Object[][] dados = new Object[agendas.size()][8];

                for (int i = 0; i < agendas.size(); i++) {
                    AgendaAnimal agenda = agendas.get(i);
                    dados[i][0] = agenda.getId();
                    dados[i][1] = agenda.getConsulta() != null ? agenda.getConsulta() : "";
                    dados[i][2] = agenda.getBanho() != null ? agenda.getBanho() : "";
                    dados[i][3] = agenda.getMedicacao() != null ? agenda.getMedicacao() : "";
                    dados[i][4] = agenda.getAtividade() != null ? agenda.getAtividade() : "";
                    dados[i][5] = agenda.getAnimal() != null ? agenda.getAnimal().toString() : "Não informado"; // CORRIGIDO
                    dados[i][6] = agenda.getVeterinario() != null ? agenda.getVeterinario().toString()
                            : "Não informado";
                    dados[i][7] = agenda.getRotinaAlimentar() != null ? agenda.getRotinaAlimentar().toString()
                            : "Não informado";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                // Configurar largura das colunas
                tabela.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
                tabela.getColumnModel().getColumn(1).setPreferredWidth(120); // Consulta
                tabela.getColumnModel().getColumn(2).setPreferredWidth(80); // Banho
                tabela.getColumnModel().getColumn(3).setPreferredWidth(100); // Medicação
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120); // Atividade
                tabela.getColumnModel().getColumn(5).setPreferredWidth(150); // Animal
                tabela.getColumnModel().getColumn(6).setPreferredWidth(150); // Veterinário
                tabela.getColumnModel().getColumn(7).setPreferredWidth(150); // Rotina Alimentar

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                // Etiqueta com quantidade de registros
                JLabel labelContador = new JLabel("Total de agendas animais encontradas: " + agendas.size());
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
        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal()); // CORRIGIDO

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(botaoVoltar);

        painelPrincipalInterno.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipalInterno.revalidate();
        painelPrincipalInterno.repaint();
    }
}