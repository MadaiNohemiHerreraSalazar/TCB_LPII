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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.ifpr.zoologicio.controller.AgendaAnimalController;
import br.edu.ifpr.zoologicio.model.AgendaAnimal;
import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Veterinario;
import br.edu.ifpr.zoologicio.model.dao.AgendaAnimalDAO;

public class AgendaAnimalPanel {

    private static JFrame janela;
    private static JPanel painelPrincipal;

    public static void main(String[] args) {
        criarJanelaPrincipal();
        // mostrarTelaVerificacao();
        // Cadastro();
        // telaBuscaID();

    }

    // CRIAR JANELA PRINCIPAL COM TITULO
    // ___________________________________________________________

    private static void criarJanelaPrincipal() {

        janela = new JFrame("Sistema do Zoológico - Gerenciamento da Agenda de Animais");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1200, 800);
        janela.setLocationRelativeTo(null);

        janela.setLayout(new BorderLayout());

        // Painel principal que será trocado
        painelPrincipal = new JPanel(new BorderLayout());
        janela.add(painelPrincipal, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    // MOSTRAR TELA DE VERIFICAÇÃO DE PERMISSO
    // __________________________________________________________

    private static void mostrarTelaVerificacao() {
        // Limpar painel principal
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        // Título - CENTRO
        JLabel labelTitulo = new JLabel("Validação de Entrada");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Painel CENTRAL com texto e caixinha
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        // Texto "Coloque a senha"
        JLabel labelSenha = new JLabel("Coloque a senha:");
        labelSenha.setFont(new Font("Serif", Font.BOLD, 20));
        labelSenha.setForeground(Color.BLACK);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espaço
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Painel para caixinha e botão
        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JPasswordField campoSenha = new JPasswordField(15);
        campoSenha.setFont(new Font("Serif", Font.PLAIN, 18));

        JButton botaoVerificar = new JButton("Verificar");
        botaoVerificar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVerificar.setBackground(Color.BLACK);
        botaoVerificar.setForeground(Color.WHITE);

        painelInput.add(campoSenha);
        painelInput.add(botaoVerificar);

        // Adicionar ao painel central
        painelCentral.add(labelSenha);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // Atualizar e ActionListeners (mesmo código)
        painelPrincipal.revalidate();
        painelPrincipal.repaint();

    }

    // MOSTRAR MENU PRINCIPAL
    // ________________________________________________________

    private static void mostrarMenuPrincipal() {
        // Limpar painel principal
        painelPrincipal.removeAll();

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
        painelTitulo.add(Box.createRigidArea(new Dimension(0, 15))); // Espaço entre título e subtítulo
        painelTitulo.add(labelSubtitulo);

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Painel central com os botões de gerenciamento
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(7, 2, 20, 20)); // 4 linhas, 2 colunas, espaço 20px
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));

        // Criar botões de gerenciamento
        String[] gerenciamentos = { " Cadastro ", "Editar ", " Remover ", " Selecionar ", " Listar " };

        for (String gerenciamento : gerenciamentos) {
            JButton botao = criarBotaoGerenciamento(gerenciamento);
            painelBotoes.add(botao);
        }

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        // Painel inferior com botão de voltar
        JPanel painelInferior = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Início");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setBackground(Color.BLACK);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        painelInferior.add(btnVoltar);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        // Atualizar a janela
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // CRAIR BOTÕES DE GERENCIAMENTO
    // _________________________________________________________________

    private static JButton criarBotaoGerenciamento(String texto) {

        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(200, 80));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);

        // Asignar la acción correspondiente a cada botón
        botao.addActionListener(e -> {
            switch (texto) {
                case "Cadastro":
                    Cadastro();
                    break;
                case "Editar":
                    telaBuscaIDEdicao();
                    break;
                case "Remover":
                    Remover();
                    break;
                case "Selecionar":
                    Selecionar();
                case "Listar":
                    Listar();
                default:
                    JOptionPane.showMessageDialog(janela, "Abrindo: " + texto);
            }
        });

        return botao;
    }

    public static void Cadastro() {
        // Limpar painel principal
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        // Título
        JLabel labelTitulo = new JLabel("Cadastro de Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipal.add(labelTitulo, BorderLayout.NORTH);

        // Painel com campos - ahora con BoxLayout vertical
        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Campo Consulta
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

        // Campo Banho
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

        // Campo Medicação
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

        // Campo Atividade
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

        // Campo ID Animal
        JPanel panelAnimal = new JPanel();
        panelAnimal.setLayout(new BoxLayout(panelAnimal, BoxLayout.Y_AXIS));
        panelAnimal.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAnimalId = new JLabel("ID Animal:");
        labelAnimalId.setFont(new Font("Serif", Font.BOLD, 18));
        labelAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimal.add(labelAnimalId);
        JTextField campoAnimalId = new JTextField();
        campoAnimalId.setPreferredSize(new Dimension(500, 25)); // Mismo ancho que los grandes
        campoAnimalId.setMaximumSize(new Dimension(500, 25));
        campoAnimalId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimal.add(campoAnimalId);
        painelCampos.add(panelAnimal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Veterinário
        JPanel panelVeterinario = new JPanel();
        panelVeterinario.setLayout(new BoxLayout(panelVeterinario, BoxLayout.Y_AXIS));
        panelVeterinario.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelVeterinarioId = new JLabel("ID Veterinário:");
        labelVeterinarioId.setFont(new Font("Serif", Font.BOLD, 18));
        labelVeterinarioId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVeterinario.add(labelVeterinarioId);
        JTextField campoVeterinarioId = new JTextField();
        campoVeterinarioId.setPreferredSize(new Dimension(500, 25)); // Mismo ancho que los grandes
        campoVeterinarioId.setMaximumSize(new Dimension(500, 25));
        campoVeterinarioId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVeterinario.add(campoVeterinarioId);
        painelCampos.add(panelVeterinario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Rotina Alimentar
        JPanel panelRotina = new JPanel();
        panelRotina.setLayout(new BoxLayout(panelRotina, BoxLayout.Y_AXIS));
        panelRotina.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelRotinaId = new JLabel("ID Rotina Alimentar:");
        labelRotinaId.setFont(new Font("Serif", Font.BOLD, 18));
        labelRotinaId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRotina.add(labelRotinaId);
        JTextField campoRotinaId = new JTextField();
        campoRotinaId.setPreferredSize(new Dimension(500, 25)); // Mismo ancho que los grandes
        campoRotinaId.setMaximumSize(new Dimension(500, 25));
        campoRotinaId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRotina.add(campoRotinaId);
        painelCampos.add(panelRotina);

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // Botão Salvar
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBackground(Color.BLACK);
        botaoSalvar.setForeground(Color.WHITE);
        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoSalvar);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        // Atualizar
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    public static void Editar(AgendaAnimal agenda) {
        // Limpar painel principal
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        // Título
        JLabel labelTitulo = new JLabel("Editar Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipal.add(labelTitulo, BorderLayout.NORTH);

        // Painel com campos - mismo formato que Cadastro
        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Campo Consulta
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
        // Cargar datos existentes
        campoConsulta.setText(agenda.getConsulta());
        panelConsulta.add(campoConsulta);
        painelCampos.add(panelConsulta);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Banho
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
        // Cargar datos existentes
        campoBanho.setText(agenda.getBanho());
        panelBanho.add(campoBanho);
        painelCampos.add(panelBanho);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Medicação
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
        // Cargar datos existentes
        campoMedicacao.setText(agenda.getMedicacao());
        panelMedicacao.add(campoMedicacao);
        painelCampos.add(panelMedicacao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Atividade
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
        // Cargar datos existentes
        campoAtividade.setText(agenda.getAtividade());
        panelAtividade.add(campoAtividade);
        painelCampos.add(panelAtividade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Animal
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
        campoAnimalId.setText(
                agenda.getAnimal() != null && agenda.getAnimal().getId() != null
                        ? String.valueOf(agenda.getAnimal().getId())
                        : "");
        panelAnimal.add(campoAnimalId);
        painelCampos.add(panelAnimal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Veterinário
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
        campoVeterinarioId.setText(
                agenda.getVeterinario() != null && agenda.getVeterinario().getId() != null
                        ? String.valueOf(agenda.getVeterinario().getId())
                        : "");
        panelVeterinario.add(campoVeterinarioId);
        painelCampos.add(panelVeterinario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Rotina Alimentar
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
        campoRotinaId.setText(
                agenda.getRotinaAlimentar() != null && agenda.getRotinaAlimentar().getId() != null
                        ? String.valueOf(agenda.getRotinaAlimentar().getId())
                        : "");
        panelRotina.add(campoRotinaId);
        painelCampos.add(panelRotina);

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // Botones para Editar
        JPanel painelBotoes = new JPanel();
        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.setBackground(Color.BLACK);
        botaoAtualizar.setForeground(Color.WHITE);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBackground(Color.GRAY);
        botaoCancelar.setForeground(Color.WHITE);

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(botaoCancelar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Action Listeners para los botones
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Atualiza o objeto com os novos valores
                agenda.setConsulta(campoConsulta.getText());
                agenda.setBanho(campoBanho.getText());
                agenda.setMedicacao(campoMedicacao.getText());
                agenda.setAtividade(campoAtividade.getText());

                // Atualizar Animal
                String animalIdTexto = campoAnimalId.getText().trim();
                if (!animalIdTexto.isEmpty()) {
                    if (agenda.getAnimal() == null)
                        agenda.setAnimal(new Animal());
                    agenda.getAnimal().setId(Integer.parseInt(animalIdTexto));
                }

                // Atualizar Veterinário
                String vetIdTexto = campoVeterinarioId.getText().trim();
                if (!vetIdTexto.isEmpty()) {
                    if (agenda.getVeterinario() == null)
                        agenda.setVeterinario(new Veterinario());
                    agenda.getVeterinario().setId(Integer.parseInt(vetIdTexto));
                }

                // Atualizar Rotina
                String rotinaIdTexto = campoRotinaId.getText().trim();
                if (!rotinaIdTexto.isEmpty()) {
                    if (agenda.getRotinaAlimentar() == null)
                        agenda.setRotinaAlimentar(new RotinaAlimentar());
                    agenda.getRotinaAlimentar().setId(Integer.parseInt(rotinaIdTexto));
                }
                // salvar no banco
                AgendaAnimalController.editarAgendaAnimal(agenda);

                JOptionPane.showMessageDialog(painelPrincipal, "Registro atualizado com sucesso!");
            }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver al menú principal o lista de registros
                System.out.println("Edição cancelada");
            }
        });

        // Atualizar
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // BUSCAR AGENDA POR ID
    // ________________________________________________________________

    public static void telaBuscaIDEdicao() {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Busca da Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelSenha = new JLabel("Coloque a o ID do Agenda Escolhida:");
        labelSenha.setFont(new Font("Serif", Font.BOLD, 20));
        labelSenha.setForeground(Color.BLACK);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        JButton botaoVerificar = new JButton("Verificar");
        botaoVerificar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVerificar.setBackground(Color.BLACK);
        botaoVerificar.setForeground(Color.WHITE);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoCancelar.setBackground(Color.GRAY);
        botaoCancelar.setForeground(Color.WHITE);

        painelInput.add(campoID);
        painelInput.add(botaoVerificar);
        painelInput.add(botaoCancelar);

        painelCentral.add(labelSenha);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // ACTION BUTTON
        botaoVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = campoID.getText().trim();

                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(painelPrincipal,
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
                        JOptionPane.showMessageDialog(painelPrincipal,
                                "Agenda carregada com sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                        Editar(agenda); // Apenas abre a tela com os dados preenchidos
                    } else {
                        JOptionPane.showMessageDialog(painelPrincipal,
                                "Nenhuma agenda encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(painelPrincipal,
                            "Por favor, digite um número válido para o ID!",
                            "Erro de Formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoCancelar.addActionListener(e -> System.out.println("Busca cancelada"));

        campoID.addActionListener(e -> botaoVerificar.doClick());

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    public static void Remover() {

    }

    public static void Selecionar() {

    }

    public static void Listar() {

    }

}
