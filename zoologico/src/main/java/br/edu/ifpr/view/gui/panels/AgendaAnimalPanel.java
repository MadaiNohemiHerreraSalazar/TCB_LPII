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

public class AgendaAnimalPanel {

    private static JFrame janela;
    private static JPanel painelPrincipal;

    public static void main(String[] args) {
        criarJanelaPrincipal();
        mostrarMenuPrincipal();

    }

    // CRIAR JANELA PRINCIPAL COM TITULO
    // ___________________________________________________________

    private static void criarJanelaPrincipal() {

        janela = new JFrame("Sistema do Zoológico - Gerenciamento da Agenda de Animais");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1200, 800);
        janela.setLocationRelativeTo(null);

        janela.setLayout(new BorderLayout());

        // Painel principal que será trocado depois
        painelPrincipal = new JPanel(new BorderLayout());
        janela.add(painelPrincipal, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    // MOSTRAR TELA DE VERIFICAÇÃO DE PERMISSO
    // __________________________________________________________

    /*
     * private static void mostrarTelaVerificacao() {
     * 
     * painelPrincipal.removeAll();
     * painelPrincipal.setLayout(new BorderLayout());
     * 
     * // Título centralizado
     * JLabel labelTitulo = new JLabel("Validação de Entrada");
     * labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
     * labelTitulo.setHorizontalAlignment(JLabel.CENTER);
     * 
     * JPanel painelTitulo = new JPanel(new BorderLayout());
     * painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
     * painelTitulo.add(labelTitulo, BorderLayout.CENTER);
     * painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
     * 
     * // Painel CENTRAL com texto e caixinha
     * JPanel painelCentral = new JPanel();
     * painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
     * painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
     * 
     * JLabel labelSenha = new JLabel("Coloque a senha:");
     * labelSenha.setFont(new Font("Serif", Font.BOLD, 20));
     * labelSenha.setForeground(Color.BLACK);
     * labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
     * 
     * painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
     * 
     * JPanel painelInput = new JPanel();
     * painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
     * 
     * JPasswordField campoSenha = new JPasswordField(15);
     * campoSenha.setFont(new Font("Serif", Font.PLAIN, 18));
     * 
     * JButton botaoVerificar = new JButton("Verificar");
     * botaoVerificar.setFont(new Font("Serif", Font.BOLD, 16));
     * botaoVerificar.setBackground(Color.BLACK);
     * botaoVerificar.setForeground(Color.WHITE);
     * 
     * JButton botaoVoltar = new JButton("Voltar");
     * botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
     * botaoVoltar.setBackground(Color.GRAY);
     * botaoVoltar.setForeground(Color.WHITE);
     * 
     * painelInput.add(campoSenha);
     * painelInput.add(botaoVerificar);
     * painelInput.add(botaoVoltar);
     * 
     * painelCentral.add(labelSenha);
     * painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
     * painelCentral.add(painelInput);
     * 
     * painelPrincipal.add(painelCentral, BorderLayout.CENTER);
     * 
     * // Verifica senha e vai para próxima tela
     * ActionListener verificarSenhaAction = e -> {
     * String senhaCorreta = "1234"; // GIO : Aqui vc tem que fazer que ele pegue a
     * senha do usuario e buscar se tem a permissão.
     * String senhaDigitada = new String(campoSenha.getPassword()).trim();
     * 
     * if (senhaDigitada.equals(senhaCorreta)) {
     * JOptionPane.showMessageDialog(painelPrincipal,
     * "Acesso permitido!",
     * "Sucesso",
     * JOptionPane.INFORMATION_MESSAGE);
     * 
     * mostrarMenuPrincipal(); // Chama a próxima tela
     * } else {
     * JOptionPane.showMessageDialog(painelPrincipal,
     * "Senha incorreta! Tente novamente.",
     * "Erro",
     * JOptionPane.ERROR_MESSAGE);
     * }
     * };
     * 
     * botaoVerificar.addActionListener(verificarSenhaAction);
     * campoSenha.addActionListener(verificarSenhaAction);
     * botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());
     * 
     * painelPrincipal.revalidate();
     * painelPrincipal.repaint();
     * }
     */

    // MOSTRAR MENU PRINCIPAL
    // ________________________________________________________

    private static void mostrarMenuPrincipal() {
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

        // botões de gerenciamento
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
        btnVoltar.addActionListener(e -> MainFrame.mostrarMenuPrincipalMain()); // GIO: Não está funcionando (arrumar)
        painelInferior.add(btnVoltar);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

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
                    JOptionPane.showMessageDialog(janela, "Abrindo: " + texto);
            }
        });

        return botao;
    }

    public static void Cadastro() {

        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Cadastro de Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipal.add(labelTitulo, BorderLayout.NORTH);

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

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // PANEL DE BOTONES
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

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

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

                JOptionPane.showMessageDialog(painelPrincipal,
                        "Agenda cadastrada com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpar campos
                campoConsulta.setText("");
                campoBanho.setText("");
                campoMedicacao.setText("");
                campoAtividade.setText("");
                campoAnimalId.setText("");
                campoVeterinarioId.setText("");
                campoRotinaId.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(painelPrincipal,
                        "IDs devem conter apenas números!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painelPrincipal,
                        "Erro ao salvar: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    public static void Editar(AgendaAnimal agenda) {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipal.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS
        JPanel panelConsulta = new JPanel();
        panelConsulta.setLayout(new BoxLayout(panelConsulta, BoxLayout.Y_AXIS));
        JLabel labelConsulta = new JLabel("Consulta:");
        labelConsulta.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoConsulta = new JTextField(agenda.getConsulta());
        campoConsulta.setPreferredSize(new Dimension(500, 50));
        campoConsulta.setMaximumSize(new Dimension(500, 50));
        panelConsulta.add(labelConsulta);
        panelConsulta.add(campoConsulta);
        painelCampos.add(panelConsulta);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelBanho = new JPanel();
        panelBanho.setLayout(new BoxLayout(panelBanho, BoxLayout.Y_AXIS));
        JLabel labelBanho = new JLabel("Banho:");
        labelBanho.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoBanho = new JTextField(agenda.getBanho());
        campoBanho.setPreferredSize(new Dimension(500, 50));
        campoBanho.setMaximumSize(new Dimension(500, 50));
        panelBanho.add(labelBanho);
        panelBanho.add(campoBanho);
        painelCampos.add(panelBanho);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelMedicacao = new JPanel();
        panelMedicacao.setLayout(new BoxLayout(panelMedicacao, BoxLayout.Y_AXIS));
        JLabel labelMedicacao = new JLabel("Medicação:");
        labelMedicacao.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoMedicacao = new JTextField(agenda.getMedicacao());
        campoMedicacao.setPreferredSize(new Dimension(500, 50));
        campoMedicacao.setMaximumSize(new Dimension(500, 50));
        panelMedicacao.add(labelMedicacao);
        panelMedicacao.add(campoMedicacao);
        painelCampos.add(panelMedicacao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelAtividade = new JPanel();
        panelAtividade.setLayout(new BoxLayout(panelAtividade, BoxLayout.Y_AXIS));
        JLabel labelAtividade = new JLabel("Atividade:");
        labelAtividade.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoAtividade = new JTextField(agenda.getAtividade());
        campoAtividade.setPreferredSize(new Dimension(500, 50));
        campoAtividade.setMaximumSize(new Dimension(500, 50));
        panelAtividade.add(labelAtividade);
        panelAtividade.add(campoAtividade);
        painelCampos.add(panelAtividade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelAnimal = new JPanel();
        panelAnimal.setLayout(new BoxLayout(panelAnimal, BoxLayout.Y_AXIS));
        JLabel labelAnimalId = new JLabel("ID Animal:");
        labelAnimalId.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoAnimalId = new JTextField(
                agenda.getAnimal() != null && agenda.getAnimal().getId() != null
                        ? String.valueOf(agenda.getAnimal().getId())
                        : "");
        campoAnimalId.setPreferredSize(new Dimension(500, 25));
        campoAnimalId.setMaximumSize(new Dimension(500, 25));
        panelAnimal.add(labelAnimalId);
        panelAnimal.add(campoAnimalId);
        painelCampos.add(panelAnimal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelVeterinario = new JPanel();
        panelVeterinario.setLayout(new BoxLayout(panelVeterinario, BoxLayout.Y_AXIS));
        JLabel labelVeterinarioId = new JLabel("ID Veterinário:");
        labelVeterinarioId.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoVeterinarioId = new JTextField(
                agenda.getVeterinario() != null && agenda.getVeterinario().getId() != null
                        ? String.valueOf(agenda.getVeterinario().getId())
                        : "");
        campoVeterinarioId.setPreferredSize(new Dimension(500, 25));
        campoVeterinarioId.setMaximumSize(new Dimension(500, 25));
        panelVeterinario.add(labelVeterinarioId);
        panelVeterinario.add(campoVeterinarioId);
        painelCampos.add(panelVeterinario);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelRotina = new JPanel();
        panelRotina.setLayout(new BoxLayout(panelRotina, BoxLayout.Y_AXIS));
        JLabel labelRotinaId = new JLabel("ID Rotina Alimentar:");
        labelRotinaId.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoRotinaId = new JTextField(
                agenda.getRotinaAlimentar() != null && agenda.getRotinaAlimentar().getId() != null
                        ? String.valueOf(agenda.getRotinaAlimentar().getId())
                        : "");
        campoRotinaId.setPreferredSize(new Dimension(500, 25));
        campoRotinaId.setMaximumSize(new Dimension(500, 25));
        panelRotina.add(labelRotinaId);
        panelRotina.add(campoRotinaId);
        painelCampos.add(panelRotina);
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // PANEL DE BOTONES
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
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // ACTION

        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                agenda.setConsulta(campoConsulta.getText());
                agenda.setBanho(campoBanho.getText());
                agenda.setMedicacao(campoMedicacao.getText());
                agenda.setAtividade(campoAtividade.getText());

                // Atualizar Animal
                try {
                    String animalIdTexto = campoAnimalId.getText().trim();
                    if (!animalIdTexto.isEmpty()) {
                        if (agenda.getAnimal() == null)
                            agenda.setAnimal(new Animal());
                        agenda.getAnimal().setId(Integer.parseInt(animalIdTexto));
                    } else {
                        agenda.setAnimal(null);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(painelPrincipal,
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
                    JOptionPane.showMessageDialog(painelPrincipal,
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
                    JOptionPane.showMessageDialog(painelPrincipal,
                            "ID da Rotina Alimentar inválido! Digite apenas números.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    AgendaAnimalController.editarAgendaAnimal(agenda);
                    JOptionPane.showMessageDialog(painelPrincipal,
                            "Registro atualizado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painelPrincipal,
                            "Erro ao atualizar no banco de dados!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoCancelar.addActionListener(e -> mostrarMenuPrincipal());

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // BUSCAR AGENDA POR ID - PARA A EDIÇÃO
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

        // BUTÕES

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

        painelCentral.add(labelSenha);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // ACTION
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

                        Editar(agenda); // Abre a tela com os dados preenchidos
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

        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        campoID.addActionListener(e -> botaoVerificar.doClick());

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    public static void telaBuscaIDRemover_Remover() {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Remover Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelSenha = new JLabel("Coloque o ID da Agenda a ser Removida:");
        labelSenha.setFont(new Font("Serif", Font.BOLD, 20));
        labelSenha.setForeground(Color.BLACK);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        // BUTÕES

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

        painelCentral.add(labelSenha);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // ACTION
        botaoRemover.addActionListener(new ActionListener() {
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
                        int confirmacao = JOptionPane.showConfirmDialog(
                                painelPrincipal,
                                "Tem certeza que deseja remover a agenda com ID: " + id + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deletarAgendaAnimal(id);
                                JOptionPane.showMessageDialog(painelPrincipal,
                                        "Agenda removida com sucesso!",
                                        "Sucesso",
                                        JOptionPane.INFORMATION_MESSAGE);
                                campoID.setText("");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(painelPrincipal,
                                        "Erro ao remover a agenda: " + ex.getMessage(),
                                        "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
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

        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        campoID.addActionListener(e -> botaoRemover.doClick());

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    public static void Selecionar(AgendaAnimal agenda) {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        if (agenda == null) {
            JOptionPane.showMessageDialog(null,
                    "Agenda não encontrada!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Título
        JLabel labelTitulo = new JLabel("Informações da Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipal.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(9, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Criar campos de texto não editavéis para mostrar os dados
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

        // Para objetos complexos, mostrar toString() a informação relevante
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

        painelPrincipal.add(painelDados, BorderLayout.CENTER);

        // BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        painelBotoes.add(botaoVoltar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    public static void telaBuscaIDSelecionar() {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Visualizar Agenda");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelSenha = new JLabel("Coloque o ID da Agenda a ser Visualizada:");
        labelSenha.setFont(new Font("Serif", Font.BOLD, 20));
        labelSenha.setForeground(Color.BLACK);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JTextField campoID = new JTextField(15);
        campoID.setFont(new Font("Serif", Font.PLAIN, 18));

        // BOTÕES
        JButton botaoListar = new JButton("Visualizar");
        botaoListar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoListar.setBackground(Color.BLUE);
        botaoListar.setForeground(Color.WHITE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);

        painelInput.add(campoID);
        painelInput.add(botaoListar);
        painelInput.add(botaoVoltar);

        painelCentral.add(labelSenha);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        painelCentral.add(painelInput);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // ACTION
        botaoListar.addActionListener(new ActionListener() {
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
                        Selecionar(agenda); // Chama ao metodo selecionar
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

        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        campoID.addActionListener(e -> botaoListar.doClick());

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    public static void Listar() {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Listar Todas as Agendas");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Panel central com a tabela
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            AgendaAnimalController controller = new AgendaAnimalController();
            java.util.List<AgendaAnimal> agendas = controller.listarAgendaAnimais();

            if (agendas == null || agendas.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhuma agenda cadastrada no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                // Criar tabela com os dados
                String[] colunas = { "ID", "Consulta", "Banho", "Medicação", "Atividade", "Animal", "Veterinário" };
                Object[][] dados = new Object[agendas.size()][7];

                for (int i = 0; i < agendas.size(); i++) {
                    AgendaAnimal agenda = agendas.get(i);
                    dados[i][0] = agenda.getId();
                    dados[i][1] = agenda.getConsulta() != null ? agenda.getConsulta() : "";
                    dados[i][2] = agenda.getBanho() != null ? agenda.getBanho() : "";
                    dados[i][3] = agenda.getMedicacao() != null ? agenda.getMedicacao() : "";
                    dados[i][4] = agenda.getAtividade() != null ? agenda.getAtividade() : "";
                    dados[i][5] = agenda.getAnimal() != null ? agenda.getAnimal().toString() : "Não informado";
                    dados[i][6] = agenda.getVeterinario() != null ? agenda.getVeterinario().toString()
                            : "Não informado";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                // Configurar largura das colunas
                tabela.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
                tabela.getColumnModel().getColumn(1).setPreferredWidth(150); // Consulta
                tabela.getColumnModel().getColumn(2).setPreferredWidth(100); // Banho
                tabela.getColumnModel().getColumn(3).setPreferredWidth(120); // Medicação
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120); // Atividade
                tabela.getColumnModel().getColumn(5).setPreferredWidth(150); // Animal
                tabela.getColumnModel().getColumn(6).setPreferredWidth(150); // Veterinário

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

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

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
        botaoVoltar.addActionListener(e -> mostrarMenuPrincipal());

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(botaoVoltar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

}