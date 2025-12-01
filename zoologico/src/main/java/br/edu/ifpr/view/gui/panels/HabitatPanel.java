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
import br.edu.ifpr.zoologicio.controller.HabitatController;
import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Area;
import br.edu.ifpr.zoologicio.model.Habitat;

public class HabitatPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public HabitatPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarMenuPrincipal();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarMenuPrincipal() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Habitats");
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

        JLabel labelTitulo = new JLabel("Cadastro de Habitat");
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
        JLabel labelNome = new JLabel("Nome do Habitat:");
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

        // Campo Capacidade
        JPanel panelCapacidade = new JPanel();
        panelCapacidade.setLayout(new BoxLayout(panelCapacidade, BoxLayout.Y_AXIS));
        panelCapacidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCapacidade = new JLabel("Capacidade (número de animais):");
        labelCapacidade.setFont(new Font("Serif", Font.BOLD, 18));
        labelCapacidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCapacidade.add(labelCapacidade);
        JTextField campoCapacidade = new JTextField();
        campoCapacidade.setPreferredSize(new Dimension(500, 40));
        campoCapacidade.setMaximumSize(new Dimension(500, 40));
        campoCapacidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCapacidade.add(campoCapacidade);
        painelCampos.add(panelCapacidade);
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
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Animais (separados por vírgula)
        JPanel panelAnimais = new JPanel();
        panelAnimais.setLayout(new BoxLayout(panelAnimais, BoxLayout.Y_AXIS));
        panelAnimais.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAnimais = new JLabel("IDs dos Animais (separados por vírgula):");
        labelAnimais.setFont(new Font("Serif", Font.BOLD, 18));
        labelAnimais.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimais.add(labelAnimais);
        JTextField campoAnimais = new JTextField();
        campoAnimais.setPreferredSize(new Dimension(500, 40));
        campoAnimais.setMaximumSize(new Dimension(500, 40));
        campoAnimais.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimais.add(campoAnimais);
        painelCampos.add(panelAnimais);

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
                Habitat habitat = new Habitat();
                habitat.setNome(campoNome.getText().trim());
                habitat.setDescricao(campoDescricao.getText().trim());
                habitat.setCapacidade(campoCapacidade.getText().trim());

                // Validar capacidade
                String capacidadeStr = campoCapacidade.getText().trim();
                if (!capacidadeStr.isEmpty()) {
                    try {
                        Integer.parseInt(capacidadeStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Capacidade deve ser um número!",
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
                        habitat.setArea(area);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "ID da Área deve ser um número!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Processar IDs dos Animais
                String animaisStr = campoAnimais.getText().trim();
                if (!animaisStr.isEmpty()) {
                    ArrayList<Animal> animaisList = new ArrayList<>();
                    String[] ids = animaisStr.split(",");
                    for (String idStr : ids) {
                        try {
                            Integer id = Integer.parseInt(idStr.trim());
                            Animal a = new Animal();
                            a.setId(id);
                            animaisList.add(a);
                        } catch (NumberFormatException ex) {
                            // Ignorar IDs inválidos
                        }
                    }
                    habitat.setAnimais(animaisList);
                }

                HabitatController controller = new HabitatController();
                controller.cadastrarHabitat(habitat);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Habitat cadastrado com sucesso!",
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
    public void Editar(Habitat habitat) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Habitat");
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
        JLabel labelNome = new JLabel("Nome do Habitat:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(habitat.getNome());
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
        JTextArea campoDescricao = new JTextArea(habitat.getDescricao(), 3, 40);
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setPreferredSize(new Dimension(500, 80));
        scrollDescricao.setMaximumSize(new Dimension(500, 80));
        scrollDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(scrollDescricao);
        painelCampos.add(panelDescricao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Capacidade
        JPanel panelCapacidade = new JPanel();
        panelCapacidade.setLayout(new BoxLayout(panelCapacidade, BoxLayout.Y_AXIS));
        panelCapacidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelCapacidade = new JLabel("Capacidade (número de animais):");
        labelCapacidade.setFont(new Font("Serif", Font.BOLD, 18));
        labelCapacidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCapacidade.add(labelCapacidade);
        JTextField campoCapacidade = new JTextField(habitat.getCapacidade());
        campoCapacidade.setPreferredSize(new Dimension(500, 40));
        campoCapacidade.setMaximumSize(new Dimension(500, 40));
        campoCapacidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCapacidade.add(campoCapacidade);
        painelCampos.add(panelCapacidade);
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
                habitat.getArea() != null && habitat.getArea().getId() != null
                        ? String.valueOf(habitat.getArea().getId())
                        : "");
        campoArea.setPreferredSize(new Dimension(500, 40));
        campoArea.setMaximumSize(new Dimension(500, 40));
        campoArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelArea.add(campoArea);
        painelCampos.add(panelArea);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo IDs Animais
        JPanel panelAnimais = new JPanel();
        panelAnimais.setLayout(new BoxLayout(panelAnimais, BoxLayout.Y_AXIS));
        panelAnimais.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAnimais = new JLabel("IDs dos Animais (separados por vírgula):");
        labelAnimais.setFont(new Font("Serif", Font.BOLD, 18));
        labelAnimais.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimais.add(labelAnimais);

        // Converter ArrayList de Animais para string de IDs
        StringBuilder animaisStr = new StringBuilder();
        if (habitat.getAnimais() != null) {
            for (Animal a : habitat.getAnimais()) {
                if (a != null && a.getId() != null) {
                    if (animaisStr.length() > 0)
                        animaisStr.append(",");
                    animaisStr.append(a.getId());
                }
            }
        }

        JTextField campoAnimais = new JTextField(animaisStr.toString());
        campoAnimais.setPreferredSize(new Dimension(500, 40));
        campoAnimais.setMaximumSize(new Dimension(500, 40));
        campoAnimais.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAnimais.add(campoAnimais);
        painelCampos.add(panelAnimais);

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
            habitat.setNome(campoNome.getText().trim());
            habitat.setDescricao(campoDescricao.getText().trim());
            habitat.setCapacidade(campoCapacidade.getText().trim());

            // Validar capacidade
            String capacidadeStr = campoCapacidade.getText().trim();
            if (!capacidadeStr.isEmpty()) {
                try {
                    Integer.parseInt(capacidadeStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "Capacidade deve ser um número!",
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
                    if (habitat.getArea() == null) {
                        habitat.setArea(new Area());
                    }
                    habitat.getArea().setId(areaId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.getJanela(),
                            "ID da Área deve ser um número!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                habitat.setArea(null);
            }

            // Processar IDs dos Animais
            String animaisTexto = campoAnimais.getText().trim();
            if (!animaisTexto.isEmpty()) {
                ArrayList<Animal> animaisList = new ArrayList<>();
                String[] ids = animaisTexto.split(",");
                for (String idStr : ids) {
                    try {
                        Integer id = Integer.parseInt(idStr.trim());
                        Animal a = new Animal();
                        a.setId(id);
                        animaisList.add(a);
                    } catch (NumberFormatException ex) {
                        // Ignorar IDs inválidos
                    }
                }
                habitat.setAnimais(animaisList);
            } else {
                habitat.setAnimais(new ArrayList<>());
            }

            try {
                HabitatController controller = new HabitatController();
                controller.editarHabitat(habitat);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Habitat atualizado com sucesso!",
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
        criarTelaBuscaID("Editar Habitat", "Digite o ID do Habitat para Editar:", "Buscar",
                (id) -> {
                    HabitatController controller = new HabitatController();
                    Habitat habitat = controller.selecionarHabitat(id);
                    if (habitat != null) {
                        Editar(habitat);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhum habitat encontrado com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Habitat", "Digite o ID do Habitat para Remover:", "Remover",
                (id) -> {
                    HabitatController controller = new HabitatController();
                    Habitat habitat = controller.selecionarHabitat(id);
                    if (habitat != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                MainFrame.getJanela(),
                                "Tem certeza que deseja remover o habitat: " + habitat.getNome() + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deletarHabitat(id);
                                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                        "Habitat removido com sucesso!",
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
                                "Nenhum habitat encontrado com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Habitat", "Digite o ID do Habitat para Visualizar:", "Visualizar",
                (id) -> {
                    HabitatController controller = new HabitatController();
                    Habitat habitat = controller.selecionarHabitat(id);
                    if (habitat != null) {
                        Selecionar(habitat);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhum habitat encontrado com ID: " + id,
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
    public void Selecionar(Habitat habitat) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (habitat == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Habitat não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações do Habitat");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        // Grid com linhas básicas + linha para animais
        int rowCount = 5; // ID, Nome, Descrição, Capacidade, Área
        String animaisInfo = converterAnimaisParaString(habitat);

        if (!animaisInfo.isEmpty())
            rowCount++;

        JPanel painelDados = new JPanel(new GridLayout(rowCount, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(habitat.getId()));
        JTextField campoNome = criarCampoReadOnly(habitat.getNome());
        JTextField campoDescricao = criarCampoReadOnly(habitat.getDescricao());
        JTextField campoCapacidade = criarCampoReadOnly(habitat.getCapacidade());

        // Campo Área
        String areaInfo = habitat.getArea() != null ? "ID: " + habitat.getArea().getId() +
                (habitat.getArea().getNome() != null ? " (" + habitat.getArea().getNome() + ")" : "") : "Não atribuída";
        JTextField campoArea = criarCampoReadOnly(areaInfo);

        // Labels e campos básicos
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("Descrição:"));
        painelDados.add(campoDescricao);
        painelDados.add(new JLabel("Capacidade:"));
        painelDados.add(campoCapacidade);
        painelDados.add(new JLabel("Área:"));
        painelDados.add(campoArea);

        // Animais
        if (!animaisInfo.isEmpty()) {
            painelDados.add(new JLabel("Animais:"));
            painelDados.add(criarCampoReadOnly(animaisInfo));
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

    // MÉTODO AUXILIAR PARA CONVERTER ARRAY DE ANIMAIS
    private String converterAnimaisParaString(Habitat habitat) {
        if (habitat.getAnimais() == null || habitat.getAnimais().isEmpty()) {
            return "Nenhum animal";
        }

        StringBuilder sb = new StringBuilder();
        for (Animal a : habitat.getAnimais()) {
            if (a != null) {
                if (sb.length() > 0)
                    sb.append(", ");
                sb.append(a.getId());
                if (a.getNome() != null) {
                    sb.append(" (").append(a.getNome()).append(")");
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

        JLabel labelTitulo = new JLabel("Listar Todos os Habitats");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            HabitatController controller = new HabitatController();
            java.util.List<Habitat> habitats = controller.listarHabitats();

            if (habitats == null || habitats.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhum habitat cadastrado no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = { "ID", "Nome", "Descrição", "Capacidade", "Área", "Qtd Animais" };
                Object[][] dados = new Object[habitats.size()][6];

                for (int i = 0; i < habitats.size(); i++) {
                    Habitat habitat = habitats.get(i);
                    dados[i][0] = habitat.getId();
                    dados[i][1] = habitat.getNome() != null ? habitat.getNome() : "";
                    dados[i][2] = habitat.getDescricao() != null
                            ? (habitat.getDescricao().length() > 30 ? habitat.getDescricao().substring(0, 27) + "..."
                                    : habitat.getDescricao())
                            : "";
                    dados[i][3] = habitat.getCapacidade() != null ? habitat.getCapacidade() : "";
                    dados[i][4] = habitat.getArea() != null && habitat.getArea().getNome() != null
                            ? habitat.getArea().getNome()
                            : "Sem área";
                    dados[i][5] = habitat.getAnimais() != null ? habitat.getAnimais().size() : 0;
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
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(5).setPreferredWidth(100);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de habitats encontrados: " + habitats.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar habitats: " + ex.getMessage());
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