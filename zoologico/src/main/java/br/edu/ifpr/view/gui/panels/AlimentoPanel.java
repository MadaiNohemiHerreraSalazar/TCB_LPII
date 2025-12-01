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
//import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpr.zoologicio.controller.AgendaAlimentoController;
import br.edu.ifpr.zoologicio.controller.AlimentoController;
import br.edu.ifpr.zoologicio.model.AgendaFuncionario;
import br.edu.ifpr.zoologicio.model.Alimento;
import br.edu.ifpr.zoologicio.model.Funcionario;

public class AlimentoPanel {

    private static JFrame janela;
    private static JPanel painelPrincipal;

    public static void main(String[] args) {
        criarJanelaPrincipal();
        mostrarMenuPrincipal();
    }

    // CRIAR JANELA PRINCIPAL COM TITULO
    // ___________________________________________________________

    private static void criarJanelaPrincipal() {

        janela = new JFrame("Sistema do Zoológico - Gerenciamento de Alimentos");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1200, 800);
        janela.setLocationRelativeTo(null);

        janela.setLayout(new BorderLayout());

        // Painel principal que será trocado
        painelPrincipal = new JPanel(new BorderLayout());
        janela.add(painelPrincipal, BorderLayout.CENTER);

        janela.setVisible(true);
    }

    // MOSTRAR MENU PRINCIPAL
    // ________________________________________________________

    private static void mostrarMenuPrincipal() {
        // Limpar painel principal
        painelPrincipal.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel(" Gerenciamento de Alimentos");
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

    // CRUD
    // _________________________________________________________________

    // arrumar controler
    public static void Cadastro() {

        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Cadastro de Alimento");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipal.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS

        JPanel panelNome = new JPanel();
        panelNome.setLayout(new BoxLayout(panelNome, BoxLayout.Y_AXIS));
        panelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(500, 50));
        campoNome.setMaximumSize(new Dimension(500, 50));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelValidade = new JPanel();
        panelValidade.setLayout(new BoxLayout(panelValidade, BoxLayout.Y_AXIS));
        panelValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelValidade = new JLabel("Validade:");
        labelValidade.setFont(new Font("Serif", Font.BOLD, 18));
        labelValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelValidade.add(labelValidade);
        JTextField campoValidade = new JTextField();
        campoValidade.setPreferredSize(new Dimension(500, 50));
        campoValidade.setMaximumSize(new Dimension(500, 50));
        campoValidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelValidade.add(campoValidade);
        painelCampos.add(panelValidade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelStoque = new JPanel();
        panelStoque.setLayout(new BoxLayout(panelStoque, BoxLayout.Y_AXIS));
        panelStoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelStoque = new JLabel("Stoque:");
        labelStoque.setFont(new Font("Serif", Font.BOLD, 18));
        labelStoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelStoque.add(labelStoque);
        JTextField campoStoque = new JTextField();
        campoStoque.setPreferredSize(new Dimension(500, 50));
        campoStoque.setMaximumSize(new Dimension(500, 50));
        campoStoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelStoque.add(campoStoque);
        painelCampos.add(panelStoque);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

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
                Alimento alimento = new Alimento();
                // alimento.setNome(null););(campoStoque.getText().trim());
                alimento.setValidade(null);
                alimento.setNome(null);
                alimento.setEstoque(null);

                // Salvar
                AgendaAlimentoController controller = new AgendaAlimentoController();
                // controller.cadastrarAlimento(alimento);

                JOptionPane.showMessageDialog(painelPrincipal,
                        "Agenda cadastrada com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpar campos
                campoStoque.setText("");
                // campoValidadeId.setText("");
                // campoNomeId.setText("");

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

    public static void Editar(Alimento alimento) {

        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Alimento");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipal.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // PANEIS DOS ATRIBUTOS COM ETIQUETA E CAMPOS

        JPanel panelNome = new JPanel();
        panelNome.setLayout(new BoxLayout(panelNome, BoxLayout.Y_AXIS));
        JLabel labelAtividade = new JLabel("Nome:");
        labelAtividade.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoNome = new JTextField(alimento.getNome());
        campoNome.setPreferredSize(new Dimension(500, 50));
        campoNome.setMaximumSize(new Dimension(500, 50));
        panelNome.add(labelAtividade);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelValidade = new JPanel();
        panelValidade.setLayout(new BoxLayout(panelValidade, BoxLayout.Y_AXIS));
        JLabel labelValidade = new JLabel("Validade:");
        labelValidade.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoValidade = new JTextField(alimento.getValidade());
        campoValidade.setPreferredSize(new Dimension(500, 50));
        campoValidade.setMaximumSize(new Dimension(500, 50));
        panelValidade.add(labelValidade);
        panelValidade.add(campoValidade);
        painelCampos.add(panelValidade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelStoque = new JPanel();
        panelStoque.setLayout(new BoxLayout(panelStoque, BoxLayout.Y_AXIS));
        JLabel labelStoque = new JLabel("Stoque:");
        labelStoque.setFont(new Font("Serif", Font.BOLD, 18));
        JTextField campoStoque = new JTextField(alimento.getEstoque());
        campoStoque.setPreferredSize(new Dimension(500, 50));
        campoStoque.setMaximumSize(new Dimension(500, 50));
        panelStoque.add(labelStoque);
        panelStoque.add(campoStoque);
        painelCampos.add(panelStoque);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

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

                // * alimento.setValidade(campoStoque.getText());

                try {
                    AlimentoController.editarAlimento(alimento);
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

    // arrumar controler
    public static void telaBuscaIDEdicao() {

        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Busca da Alimento");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelSenha = new JLabel("Coloque a o ID do Alimento Escolhido:");
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

                    AlimentoController controller = new AlimentoController();
                    Alimento agenda = controller.selecionarAlimento(id);

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

        JLabel labelTitulo = new JLabel("Remover Alimento");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelSenha = new JLabel("Coloque o ID da Alimento a ser Removida:");
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

                    AlimentoController controller = new AlimentoController();
                    Alimento alimento = controller.selecionarAlimento(id);

                    if (alimento != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                painelPrincipal,
                                "Tem certeza que deseja remover a agenda com ID: " + id + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                /// controller.deletarAgendaFuncionario(id);
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

    public static void Selecionar(Alimento alimento) {

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

        JTextField campoAtividade = new JTextField(agenda.getAtividade());
        campoAtividade.setFont(new Font("Serif", Font.PLAIN, 14));
        campoAtividade.setEditable(false);
        campoAtividade.setBackground(Color.WHITE);
        campoAtividade.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField campoEstoque = new JTextField(agenda.getAtividade());
        campoEstoque.setFont(new Font("Serif", Font.PLAIN, 14));
        campoEstoque.setEditable(false);
        campoEstoque.setBackground(Color.WHITE);
        campoEstoque.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField campoValidade = new JTextField(agenda.getAtividade());
        campoValidade.setFont(new Font("Serif", Font.PLAIN, 14));
        campoValidade.setEditable(false);
        campoValidade.setBackground(Color.WHITE);
        campoValidade.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Criar labels
        JLabel labelID = new JLabel("ID:");
        labelID.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelNome = new JLabel("Atividade:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelValidade = new JLabel("Funcionario:");
        labelValidade.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel labelEstoque = new JLabel("Funcionario:");
        labelEstoque.setFont(new Font("Serif", Font.BOLD, 14));

        // Agregar etiquetas e campos ao panel
        painelDados.add(labelID);
        painelDados.add(campoID);
        painelDados.add(labelNome);
        painelDados.add(campoValidade);
        painelDados.add(labelEstoque);

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

    //criar metodo controller
    public static void telaBuscaIDSelecionar() {

    
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
            AgendaAlimentoController controller = new AgendaAlimentoController();
            java.util.List<AgendaFuncionario> agendas = controller.listarAgendaFuncionarios();

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
                    AgendaFuncionario agenda = agendas.get(i);
                    dados[i][0] = agenda.getId();
                    dados[i][4] = agenda.getAtividade() != null ? agenda.getAtividade() : "";
                    dados[i][5] = agenda.getFuncionario() != null ? agenda.getFuncionario().toString()
                            : "Não informado";

                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                // Configurar largura das colunas
                tabela.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120); // Atividade
                tabela.getColumnModel().getColumn(5).setPreferredWidth(150); // Funcionario

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