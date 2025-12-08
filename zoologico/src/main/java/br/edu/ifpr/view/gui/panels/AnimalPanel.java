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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.AnimalController;
import br.edu.ifpr.zoologicio.model.Animal;
import br.edu.ifpr.zoologicio.model.Habitat;

public class AnimalPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public AnimalPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarLogin();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarLogin() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Animais");
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

        JLabel labelTitulo = new JLabel("Cadastro de Animal");
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
        JLabel labelNome = new JLabel("Nome do Animal:");
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

        // Campo Espécie
        JPanel panelEspecie = new JPanel();
        panelEspecie.setLayout(new BoxLayout(panelEspecie, BoxLayout.Y_AXIS));
        panelEspecie.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEspecie = new JLabel("Espécie:");
        labelEspecie.setFont(new Font("Serif", Font.BOLD, 18));
        labelEspecie.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecie.add(labelEspecie);
        JTextField campoEspecie = new JTextField();
        campoEspecie.setPreferredSize(new Dimension(500, 40));
        campoEspecie.setMaximumSize(new Dimension(500, 40));
        campoEspecie.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecie.add(campoEspecie);
        painelCampos.add(panelEspecie);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Idade
        JPanel panelIdade = new JPanel();
        panelIdade.setLayout(new BoxLayout(panelIdade, BoxLayout.Y_AXIS));
        panelIdade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelIdade = new JLabel("Idade (anos):");
        labelIdade.setFont(new Font("Serif", Font.BOLD, 18));
        labelIdade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIdade.add(labelIdade);
        JTextField campoIdade = new JTextField();
        campoIdade.setPreferredSize(new Dimension(500, 40));
        campoIdade.setMaximumSize(new Dimension(500, 40));
        campoIdade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIdade.add(campoIdade);
        painelCampos.add(panelIdade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Gênero
        JPanel panelGenero = new JPanel();
        panelGenero.setLayout(new BoxLayout(panelGenero, BoxLayout.Y_AXIS));
        panelGenero.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelGenero = new JLabel("Gênero:");
        labelGenero.setFont(new Font("Serif", Font.BOLD, 18));
        labelGenero.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelGenero.add(labelGenero);
        JComboBox<String> comboGenero = new JComboBox<>(new String[] {
                "Macho", "Fêmea", "Indefinido"
        });
        comboGenero.setPreferredSize(new Dimension(500, 40));
        comboGenero.setMaximumSize(new Dimension(500, 40));
        comboGenero.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelGenero.add(comboGenero);
        painelCampos.add(panelGenero);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Peso
        JPanel panelPeso = new JPanel();
        panelPeso.setLayout(new BoxLayout(panelPeso, BoxLayout.Y_AXIS));
        panelPeso.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelPeso = new JLabel("Peso (kg):");
        labelPeso.setFont(new Font("Serif", Font.BOLD, 18));
        labelPeso.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPeso.add(labelPeso);
        JTextField campoPeso = new JTextField();
        campoPeso.setPreferredSize(new Dimension(500, 40));
        campoPeso.setMaximumSize(new Dimension(500, 40));
        campoPeso.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPeso.add(campoPeso);
        painelCampos.add(panelPeso);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Altura
        JPanel panelAltura = new JPanel();
        panelAltura.setLayout(new BoxLayout(panelAltura, BoxLayout.Y_AXIS));
        panelAltura.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAltura = new JLabel("Altura (metros):");
        labelAltura.setFont(new Font("Serif", Font.BOLD, 18));
        labelAltura.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAltura.add(labelAltura);
        JTextField campoAltura = new JTextField();
        campoAltura.setPreferredSize(new Dimension(500, 40));
        campoAltura.setMaximumSize(new Dimension(500, 40));
        campoAltura.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAltura.add(campoAltura);
        painelCampos.add(panelAltura);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Saúde
        JPanel panelSaude = new JPanel();
        panelSaude.setLayout(new BoxLayout(panelSaude, BoxLayout.Y_AXIS));
        panelSaude.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelSaude = new JLabel("Estado de Saúde:");
        labelSaude.setFont(new Font("Serif", Font.BOLD, 18));
        labelSaude.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSaude.add(labelSaude);
        JComboBox<String> comboSaude = new JComboBox<>(new String[] {
                "Excelente", "Bom", "Regular", "Ruim", "Crítico"
        });
        comboSaude.setPreferredSize(new Dimension(500, 40));
        comboSaude.setMaximumSize(new Dimension(500, 40));
        comboSaude.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSaude.add(comboSaude);
        painelCampos.add(panelSaude);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID do Habitat
        JPanel panelHabitat = new JPanel();
        panelHabitat.setLayout(new BoxLayout(panelHabitat, BoxLayout.Y_AXIS));
        panelHabitat.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHabitat = new JLabel("ID do Habitat:");
        labelHabitat.setFont(new Font("Serif", Font.BOLD, 18));
        labelHabitat.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitat.add(labelHabitat);
        JTextField campoHabitat = new JTextField();
        campoHabitat.setPreferredSize(new Dimension(500, 40));
        campoHabitat.setMaximumSize(new Dimension(500, 40));
        campoHabitat.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitat.add(campoHabitat);
        painelCampos.add(panelHabitat);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Veterinário
        JPanel panelVet = new JPanel();
        panelVet.setLayout(new BoxLayout(panelVet, BoxLayout.Y_AXIS));
        panelVet.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelVet = new JLabel("ID do Veterinário:");
        labelVet.setFont(new Font("Serif", Font.BOLD, 18));
        labelVet.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVet.add(labelVet);

        JTextField campoVeterinario = new JTextField();
        campoVeterinario.setPreferredSize(new Dimension(500, 40));
        campoVeterinario.setMaximumSize(new Dimension(500, 40));
        campoVeterinario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVet.add(campoVeterinario);

        painelCampos.add(panelVet);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Descrição
        JPanel panelDescricao = new JPanel();
        panelDescricao.setLayout(new BoxLayout(panelDescricao, BoxLayout.Y_AXIS));
        panelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setFont(new Font("Serif", Font.BOLD, 18));
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(labelDescricao);
        JTextArea campoDescricao = new JTextArea(3, 30);
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setPreferredSize(new Dimension(500, 80));
        scrollDescricao.setMaximumSize(new Dimension(500, 80));
        scrollDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(scrollDescricao);
        painelCampos.add(panelDescricao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo História
        JPanel panelHistoria = new JPanel();
        panelHistoria.setLayout(new BoxLayout(panelHistoria, BoxLayout.Y_AXIS));
        panelHistoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHistoria = new JLabel("História:");
        labelHistoria.setFont(new Font("Serif", Font.BOLD, 18));
        labelHistoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHistoria.add(labelHistoria);
        JTextArea campoHistoria = new JTextArea(4, 30);
        campoHistoria.setLineWrap(true);
        campoHistoria.setWrapStyleWord(true);
        JScrollPane scrollHistoria = new JScrollPane(campoHistoria);
        scrollHistoria.setPreferredSize(new Dimension(500, 100));
        scrollHistoria.setMaximumSize(new Dimension(500, 100));
        scrollHistoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHistoria.add(scrollHistoria);
        painelCampos.add(panelHistoria);

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
                Animal animal = new Animal();
                animal.setNome(campoNome.getText().trim());
                animal.setEspecie(campoEspecie.getText().trim());
                animal.setIdade(campoIdade.getText().trim());
                animal.setGenero((String) comboGenero.getSelectedItem());
                animal.setPeso(campoPeso.getText().trim());
                animal.setAltura(campoAltura.getText().trim());
                animal.setSaude((String) comboSaude.getSelectedItem());
                animal.setDescricao(campoDescricao.getText().trim());
                animal.setHistoria(campoHistoria.getText().trim());

                // Configurar Habitat (apenas ID por enquanto)
                String habitatId = campoHabitat.getText().trim();
                if (!habitatId.isEmpty()) {
                    // Aqui você buscaria o Habitat pelo ID
                    Habitat habitat = new Habitat();
                    habitat.setId(Integer.parseInt(habitatId));
                    animal.setHabitat(habitat);
                }

                // Ler o ID do veterinário
                String vetIdStr = campoVeterinario.getText().trim();
                int veterinarioId = Integer.parseInt(vetIdStr);

                AnimalController controller = new AnimalController();
                controller.cadastrarAnimal(animal, veterinarioId);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Animal cadastrado com sucesso!",
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
    public void Editar(Animal animal) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Animal");
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
        JLabel labelNome = new JLabel("Nome do Animal:");
        labelNome.setFont(new Font("Serif", Font.BOLD, 18));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(labelNome);
        JTextField campoNome = new JTextField(animal.getNome() != null ? animal.getNome() : "");
        campoNome.setPreferredSize(new Dimension(500, 40));
        campoNome.setMaximumSize(new Dimension(500, 40));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNome.add(campoNome);
        painelCampos.add(panelNome);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Espécie
        JPanel panelEspecie = new JPanel();
        panelEspecie.setLayout(new BoxLayout(panelEspecie, BoxLayout.Y_AXIS));
        panelEspecie.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelEspecie = new JLabel("Espécie:");
        labelEspecie.setFont(new Font("Serif", Font.BOLD, 18));
        labelEspecie.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecie.add(labelEspecie);
        JTextField campoEspecie = new JTextField(animal.getEspecie() != null ? animal.getEspecie() : "");
        campoEspecie.setPreferredSize(new Dimension(500, 40));
        campoEspecie.setMaximumSize(new Dimension(500, 40));
        campoEspecie.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEspecie.add(campoEspecie);
        painelCampos.add(panelEspecie);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Idade
        JPanel panelIdade = new JPanel();
        panelIdade.setLayout(new BoxLayout(panelIdade, BoxLayout.Y_AXIS));
        panelIdade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelIdade = new JLabel("Idade (anos):");
        labelIdade.setFont(new Font("Serif", Font.BOLD, 18));
        labelIdade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIdade.add(labelIdade);
        JTextField campoIdade = new JTextField(animal.getIdade() != null ? animal.getIdade() : "");
        campoIdade.setPreferredSize(new Dimension(500, 40));
        campoIdade.setMaximumSize(new Dimension(500, 40));
        campoIdade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIdade.add(campoIdade);
        painelCampos.add(panelIdade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Gênero
        JPanel panelGenero = new JPanel();
        panelGenero.setLayout(new BoxLayout(panelGenero, BoxLayout.Y_AXIS));
        panelGenero.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelGenero = new JLabel("Gênero:");
        labelGenero.setFont(new Font("Serif", Font.BOLD, 18));
        labelGenero.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelGenero.add(labelGenero);
        JComboBox<String> comboGenero = new JComboBox<>(new String[] {
                "Macho", "Fêmea", "Indefinido"
        });
        if (animal.getGenero() != null) {
            comboGenero.setSelectedItem(animal.getGenero());
        }
        comboGenero.setPreferredSize(new Dimension(500, 40));
        comboGenero.setMaximumSize(new Dimension(500, 40));
        comboGenero.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelGenero.add(comboGenero);
        painelCampos.add(panelGenero);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Peso
        JPanel panelPeso = new JPanel();
        panelPeso.setLayout(new BoxLayout(panelPeso, BoxLayout.Y_AXIS));
        panelPeso.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelPeso = new JLabel("Peso (kg):");
        labelPeso.setFont(new Font("Serif", Font.BOLD, 18));
        labelPeso.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPeso.add(labelPeso);
        JTextField campoPeso = new JTextField(animal.getPeso() != null ? animal.getPeso() : "");
        campoPeso.setPreferredSize(new Dimension(500, 40));
        campoPeso.setMaximumSize(new Dimension(500, 40));
        campoPeso.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPeso.add(campoPeso);
        painelCampos.add(panelPeso);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Altura
        JPanel panelAltura = new JPanel();
        panelAltura.setLayout(new BoxLayout(panelAltura, BoxLayout.Y_AXIS));
        panelAltura.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelAltura = new JLabel("Altura (metros):");
        labelAltura.setFont(new Font("Serif", Font.BOLD, 18));
        labelAltura.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAltura.add(labelAltura);
        JTextField campoAltura = new JTextField(animal.getAltura() != null ? animal.getAltura() : "");
        campoAltura.setPreferredSize(new Dimension(500, 40));
        campoAltura.setMaximumSize(new Dimension(500, 40));
        campoAltura.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAltura.add(campoAltura);
        painelCampos.add(panelAltura);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Saúde
        JPanel panelSaude = new JPanel();
        panelSaude.setLayout(new BoxLayout(panelSaude, BoxLayout.Y_AXIS));
        panelSaude.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelSaude = new JLabel("Estado de Saúde:");
        labelSaude.setFont(new Font("Serif", Font.BOLD, 18));
        labelSaude.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSaude.add(labelSaude);
        JComboBox<String> comboSaude = new JComboBox<>(new String[] {
                "Excelente", "Bom", "Regular", "Ruim", "Crítico"
        });
        if (animal.getSaude() != null) {
            comboSaude.setSelectedItem(animal.getSaude());
        }
        comboSaude.setPreferredSize(new Dimension(500, 40));
        comboSaude.setMaximumSize(new Dimension(500, 40));
        comboSaude.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSaude.add(comboSaude);
        painelCampos.add(panelSaude);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID do Habitat
        JPanel panelHabitat = new JPanel();
        panelHabitat.setLayout(new BoxLayout(panelHabitat, BoxLayout.Y_AXIS));
        panelHabitat.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHabitat = new JLabel("ID do Habitat:");
        labelHabitat.setFont(new Font("Serif", Font.BOLD, 18));
        labelHabitat.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitat.add(labelHabitat);
        String habitatId = animal.getHabitat() != null ? String.valueOf(animal.getHabitat().getId()) : "";
        JTextField campoHabitat = new JTextField(habitatId);
        campoHabitat.setPreferredSize(new Dimension(500, 40));
        campoHabitat.setMaximumSize(new Dimension(500, 40));
        campoHabitat.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHabitat.add(campoHabitat);
        painelCampos.add(panelHabitat);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo ID Veterinário
        JPanel panelVet = new JPanel();
        panelVet.setLayout(new BoxLayout(panelVet, BoxLayout.Y_AXIS));
        panelVet.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelVet = new JLabel("ID do Veterinário:");
        labelVet.setFont(new Font("Serif", Font.BOLD, 18));
        labelVet.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVet.add(labelVet);

        JTextField campoVeterinario = new JTextField();
        campoVeterinario.setPreferredSize(new Dimension(500, 40));
        campoVeterinario.setMaximumSize(new Dimension(500, 40));
        campoVeterinario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVet.add(campoVeterinario);

        painelCampos.add(panelVet);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Descrição
        JPanel panelDescricao = new JPanel();
        panelDescricao.setLayout(new BoxLayout(panelDescricao, BoxLayout.Y_AXIS));
        panelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setFont(new Font("Serif", Font.BOLD, 18));
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(labelDescricao);
        JTextArea campoDescricao = new JTextArea(3, 30);
        campoDescricao.setText(animal.getDescricao() != null ? animal.getDescricao() : "");
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setPreferredSize(new Dimension(500, 80));
        scrollDescricao.setMaximumSize(new Dimension(500, 80));
        scrollDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDescricao.add(scrollDescricao);
        painelCampos.add(panelDescricao);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo História
        JPanel panelHistoria = new JPanel();
        panelHistoria.setLayout(new BoxLayout(panelHistoria, BoxLayout.Y_AXIS));
        panelHistoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelHistoria = new JLabel("História:");
        labelHistoria.setFont(new Font("Serif", Font.BOLD, 18));
        labelHistoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHistoria.add(labelHistoria);
        JTextArea campoHistoria = new JTextArea(4, 30);
        campoHistoria.setText(animal.getHistoria() != null ? animal.getHistoria() : "");
        campoHistoria.setLineWrap(true);
        campoHistoria.setWrapStyleWord(true);
        JScrollPane scrollHistoria = new JScrollPane(campoHistoria);
        scrollHistoria.setPreferredSize(new Dimension(500, 100));
        scrollHistoria.setMaximumSize(new Dimension(500, 100));
        scrollHistoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHistoria.add(scrollHistoria);
        painelCampos.add(panelHistoria);

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
                animal.setNome(campoNome.getText().trim());
                animal.setEspecie(campoEspecie.getText().trim());
                animal.setIdade(campoIdade.getText().trim());
                animal.setGenero((String) comboGenero.getSelectedItem());
                animal.setPeso(campoPeso.getText().trim());
                animal.setAltura(campoAltura.getText().trim());
                animal.setSaude((String) comboSaude.getSelectedItem());
                animal.setDescricao(campoDescricao.getText().trim());
                animal.setHistoria(campoHistoria.getText().trim());

                // Atualizar Habitat se necessário
                String habitatIdStr = campoHabitat.getText().trim();
                if (!habitatIdStr.isEmpty()) {
                    Habitat habitat = new Habitat();
                    habitat.setId(Integer.parseInt(habitatIdStr));
                    animal.setHabitat(habitat);
                } else {
                    animal.setHabitat(null);
                }

                // Ler o ID do veterinário
                String vetIdStr = campoVeterinario.getText().trim();
                int veterinarioId = Integer.parseInt(vetIdStr);

                AnimalController controller = new AnimalController();
                controller.editarAnimal(animal, veterinarioId);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Animal atualizado com sucesso!",
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
        criarTelaBuscaID("Editar Animal", "Digite o ID do Animal para Editar:", "Buscar",
                (id) -> {
                    AnimalController controller = new AnimalController();
                    Animal animal = controller.selecionarAnimal(id);
                    if (animal != null) {
                        Editar(animal);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhum animal encontrado com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Animal", "Digite o ID do Animal para Remover:", "Remover",
                (id) -> {
                    AnimalController controller = new AnimalController();
                    Animal animal = controller.selecionarAnimal(id);
                    if (animal != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                MainFrame.getJanela(),
                                "Tem certeza que deseja remover o animal: " + animal.getNome() + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deleteAnimal(id);
                                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                        "Animal removido com sucesso!",
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
                                "Nenhum animal encontrado com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Animal", "Digite o ID do Animal para Visualizar:", "Visualizar",
                (id) -> {
                    AnimalController controller = new AnimalController();
                    Animal animal = controller.selecionarAnimal(id);
                    if (animal != null) {
                        Selecionar(animal);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhum animal encontrado com ID: " + id,
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
    public void Selecionar(Animal animal) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (animal == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Animal não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações do Animal");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(10, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(animal.getId()));
        JTextField campoNome = criarCampoReadOnly(animal.getNome() != null ? animal.getNome() : "Não informado");
        JTextField campoEspecie = criarCampoReadOnly(
                animal.getEspecie() != null ? animal.getEspecie() : "Não informado");
        JTextField campoIdade = criarCampoReadOnly(
                animal.getIdade() != null ? animal.getIdade() + " anos" : "Não informado");
        JTextField campoGenero = criarCampoReadOnly(animal.getGenero() != null ? animal.getGenero() : "Não informado");
        JTextField campoPeso = criarCampoReadOnly(
                animal.getPeso() != null ? animal.getPeso() + " kg" : "Não informado");
        JTextField campoAltura = criarCampoReadOnly(
                animal.getAltura() != null ? animal.getAltura() + " m" : "Não informado");
        JTextField campoSaude = criarCampoReadOnly(animal.getSaude() != null ? animal.getSaude() : "Não informado");

        String habitatInfo = animal.getHabitat() != null ? "ID: " + animal.getHabitat().getId() : "Não informado";
        JTextField campoHabitat = criarCampoReadOnly(habitatInfo);

        // Labels
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(campoNome);
        painelDados.add(new JLabel("Espécie:"));
        painelDados.add(campoEspecie);
        painelDados.add(new JLabel("Idade:"));
        painelDados.add(campoIdade);
        painelDados.add(new JLabel("Gênero:"));
        painelDados.add(campoGenero);
        painelDados.add(new JLabel("Peso:"));
        painelDados.add(campoPeso);
        painelDados.add(new JLabel("Altura:"));
        painelDados.add(campoAltura);
        painelDados.add(new JLabel("Saúde:"));
        painelDados.add(campoSaude);
        painelDados.add(new JLabel("Habitat:"));
        painelDados.add(campoHabitat);

        painelPrincipalInterno.add(painelDados, BorderLayout.CENTER);

        // Painel para Descrição e História (áreas maiores)
        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Descrição
        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setFont(new Font("Serif", Font.BOLD, 14));
        JTextArea areaDescricao = new JTextArea(3, 30);
        areaDescricao.setText(animal.getDescricao() != null ? animal.getDescricao() : "Sem descrição");
        areaDescricao.setEditable(false);
        areaDescricao.setLineWrap(true);
        areaDescricao.setWrapStyleWord(true);
        areaDescricao.setBackground(Color.WHITE);
        areaDescricao.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollDescricao = new JScrollPane(areaDescricao);
        scrollDescricao.setPreferredSize(new Dimension(700, 60));

        // História
        JLabel labelHistoria = new JLabel("História:");
        labelHistoria.setFont(new Font("Serif", Font.BOLD, 14));
        JTextArea areaHistoria = new JTextArea(4, 30);
        areaHistoria.setText(animal.getHistoria() != null ? animal.getHistoria() : "Sem história");
        areaHistoria.setEditable(false);
        areaHistoria.setLineWrap(true);
        areaHistoria.setWrapStyleWord(true);
        areaHistoria.setBackground(Color.WHITE);
        areaHistoria.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollHistoria = new JScrollPane(areaHistoria);
        scrollHistoria.setPreferredSize(new Dimension(700, 80));

        painelTextos.add(labelDescricao);
        painelTextos.add(Box.createRigidArea(new Dimension(0, 5)));
        painelTextos.add(scrollDescricao);
        painelTextos.add(Box.createRigidArea(new Dimension(0, 15)));
        painelTextos.add(labelHistoria);
        painelTextos.add(Box.createRigidArea(new Dimension(0, 5)));
        painelTextos.add(scrollHistoria);

        JPanel painelContainer = new JPanel(new BorderLayout());
        painelContainer.add(painelDados, BorderLayout.NORTH);
        painelContainer.add(painelTextos, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelContainer, BorderLayout.CENTER);

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

        JLabel labelTitulo = new JLabel("Listar Todos os Animais");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            AnimalController controller = new AnimalController();
            java.util.List<Animal> animais = controller.listarAnimais();

            if (animais == null || animais.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhum animal cadastrado no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = { "ID", "Nome", "Espécie", "Idade", "Gênero", "Peso", "Altura", "Saúde",
                        "Habitat ID" };
                Object[][] dados = new Object[animais.size()][9];

                for (int i = 0; i < animais.size(); i++) {
                    Animal animal = animais.get(i);
                    dados[i][0] = animal.getId();
                    dados[i][1] = animal.getNome() != null ? animal.getNome() : "";
                    dados[i][2] = animal.getEspecie() != null ? animal.getEspecie() : "";
                    dados[i][3] = animal.getIdade() != null ? animal.getIdade() : "";
                    dados[i][4] = animal.getGenero() != null ? animal.getGenero() : "";
                    dados[i][5] = animal.getPeso() != null ? animal.getPeso() : "";
                    dados[i][6] = animal.getAltura() != null ? animal.getAltura() : "";
                    dados[i][7] = animal.getSaude() != null ? animal.getSaude() : "";
                    dados[i][8] = animal.getHabitat() != null ? animal.getHabitat().getId() : "";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(60);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabela.getColumnModel().getColumn(5).setPreferredWidth(60);
                tabela.getColumnModel().getColumn(6).setPreferredWidth(70);
                tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
                tabela.getColumnModel().getColumn(8).setPreferredWidth(80);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de animais encontrados: " + animais.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar animais: " + ex.getMessage());
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