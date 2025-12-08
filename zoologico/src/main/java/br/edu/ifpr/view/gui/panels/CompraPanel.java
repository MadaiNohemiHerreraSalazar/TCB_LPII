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
//import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpr.view.gui.MainFrame;
import br.edu.ifpr.zoologicio.controller.CompraController;
import br.edu.ifpr.zoologicio.model.Compra;

public class CompraPanel extends JPanel {

    private JPanel painelPrincipalInterno;

    public CompraPanel() {
        setLayout(new BorderLayout());
        painelPrincipalInterno = new JPanel(new BorderLayout());
        add(painelPrincipalInterno, BorderLayout.CENTER);

        mostrarMenuPrincipal();
    }

    // MOSTRAR MENU PRINCIPAL
    private void mostrarMenuPrincipal() {
        painelPrincipalInterno.removeAll();

        // Título do menu principal
        JLabel labelTitulo = new JLabel("Gerenciamento de Compras");
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

        JLabel labelTitulo = new JLabel("Cadastro de Compra");
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
        JTextField campoData = new JTextField();
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
        JTextField campoHora = new JTextField();
        campoHora.setPreferredSize(new Dimension(500, 40));
        campoHora.setMaximumSize(new Dimension(500, 40));
        campoHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHora.add(campoHora);
        painelCampos.add(panelHora);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Quantidade
        JPanel panelQuantidade = new JPanel();
        panelQuantidade.setLayout(new BoxLayout(panelQuantidade, BoxLayout.Y_AXIS));
        panelQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelQuantidade = new JLabel("Quantidade:");
        labelQuantidade.setFont(new Font("Serif", Font.BOLD, 18));
        labelQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQuantidade.add(labelQuantidade);
        JTextField campoQuantidade = new JTextField();
        campoQuantidade.setPreferredSize(new Dimension(500, 40));
        campoQuantidade.setMaximumSize(new Dimension(500, 40));
        campoQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQuantidade.add(campoQuantidade);
        painelCampos.add(panelQuantidade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Meio de Pagamento
        JPanel panelMeioPagamento = new JPanel();
        panelMeioPagamento.setLayout(new BoxLayout(panelMeioPagamento, BoxLayout.Y_AXIS));
        panelMeioPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelMeioPagamento = new JLabel("Meio de Pagamento:");
        labelMeioPagamento.setFont(new Font("Serif", Font.BOLD, 18));
        labelMeioPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMeioPagamento.add(labelMeioPagamento);
        JComboBox<String> comboMeioPagamento = new JComboBox<>(new String[] {
                "Dinheiro", "Cartão de Crédito", "Cartão de Débito", "PIX", "Transferência"
        });
        comboMeioPagamento.setPreferredSize(new Dimension(500, 40));
        comboMeioPagamento.setMaximumSize(new Dimension(500, 40));
        comboMeioPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMeioPagamento.add(comboMeioPagamento);
        painelCampos.add(panelMeioPagamento);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Preço Total
        JPanel panelPrecoTotal = new JPanel();
        panelPrecoTotal.setLayout(new BoxLayout(panelPrecoTotal, BoxLayout.Y_AXIS));
        panelPrecoTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelPrecoTotal = new JLabel("Preço Total (R$):");
        labelPrecoTotal.setFont(new Font("Serif", Font.BOLD, 18));
        labelPrecoTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrecoTotal.add(labelPrecoTotal);
        JTextField campoPrecoTotal = new JTextField();
        campoPrecoTotal.setPreferredSize(new Dimension(500, 40));
        campoPrecoTotal.setMaximumSize(new Dimension(500, 40));
        campoPrecoTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrecoTotal.add(campoPrecoTotal);
        painelCampos.add(panelPrecoTotal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Visitante
        JPanel panelVisitante = new JPanel();
        panelVisitante.setLayout(new BoxLayout(panelVisitante, BoxLayout.Y_AXIS));
        panelVisitante.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelVisitante = new JLabel("Visitante (CPF ou Nome):");
        labelVisitante.setFont(new Font("Serif", Font.BOLD, 18));
        labelVisitante.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVisitante.add(labelVisitante);
        JTextField campoVisitante = new JTextField();
        campoVisitante.setPreferredSize(new Dimension(500, 40));
        campoVisitante.setMaximumSize(new Dimension(500, 40));
        campoVisitante.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVisitante.add(campoVisitante);
        painelCampos.add(panelVisitante);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Funcionário (ID)
        JPanel panelFuncionario = new JPanel();
        panelFuncionario.setLayout(new BoxLayout(panelFuncionario, BoxLayout.Y_AXIS));
        panelFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelFuncionario = new JLabel("ID do Funcionário:");
        labelFuncionario.setFont(new Font("Serif", Font.BOLD, 18));
        labelFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionario.add(labelFuncionario);
        JTextField campoFuncionario = new JTextField();
        campoFuncionario.setPreferredSize(new Dimension(500, 40));
        campoFuncionario.setMaximumSize(new Dimension(500, 40));
        campoFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionario.add(campoFuncionario);
        painelCampos.add(panelFuncionario);

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
                Compra compra = new Compra();
                compra.setData(campoData.getText().trim());
                compra.setHora(campoHora.getText().trim());
                compra.setQuantidade(campoQuantidade.getText().trim());
                compra.setMeioPagamento((String) comboMeioPagamento.getSelectedItem());
                compra.setPrecoTotal(campoPrecoTotal.getText().trim());

                // ID do funcionário
                String funcionarioIdStr = campoFuncionario.getText().trim();
                int funcionarioId = Integer.parseInt(funcionarioIdStr);

                CompraController controller = new CompraController();
                controller.cadastrarCompra(compra, funcionarioId);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Compra cadastrada com sucesso!",
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
    public void Editar(Compra compra) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Editar Compra");
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
        JTextField campoData = new JTextField(compra.getData());
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
        JTextField campoHora = new JTextField(compra.getHora());
        campoHora.setPreferredSize(new Dimension(500, 40));
        campoHora.setMaximumSize(new Dimension(500, 40));
        campoHora.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHora.add(campoHora);
        painelCampos.add(panelHora);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Quantidade
        JPanel panelQuantidade = new JPanel();
        panelQuantidade.setLayout(new BoxLayout(panelQuantidade, BoxLayout.Y_AXIS));
        panelQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelQuantidade = new JLabel("Quantidade:");
        labelQuantidade.setFont(new Font("Serif", Font.BOLD, 18));
        labelQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQuantidade.add(labelQuantidade);
        JTextField campoQuantidade = new JTextField(compra.getQuantidade());
        campoQuantidade.setPreferredSize(new Dimension(500, 40));
        campoQuantidade.setMaximumSize(new Dimension(500, 40));
        campoQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelQuantidade.add(campoQuantidade);
        painelCampos.add(panelQuantidade);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Meio de Pagamento
        JPanel panelMeioPagamento = new JPanel();
        panelMeioPagamento.setLayout(new BoxLayout(panelMeioPagamento, BoxLayout.Y_AXIS));
        panelMeioPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelMeioPagamento = new JLabel("Meio de Pagamento:");
        labelMeioPagamento.setFont(new Font("Serif", Font.BOLD, 18));
        labelMeioPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMeioPagamento.add(labelMeioPagamento);
        JComboBox<String> comboMeioPagamento = new JComboBox<>(new String[] {
                "Dinheiro", "Cartão de Crédito", "Cartão de Débito", "PIX", "Transferência"
        });
        comboMeioPagamento.setSelectedItem(compra.getMeioPagamento());
        comboMeioPagamento.setPreferredSize(new Dimension(500, 40));
        comboMeioPagamento.setMaximumSize(new Dimension(500, 40));
        comboMeioPagamento.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMeioPagamento.add(comboMeioPagamento);
        painelCampos.add(panelMeioPagamento);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Preço Total
        JPanel panelPrecoTotal = new JPanel();
        panelPrecoTotal.setLayout(new BoxLayout(panelPrecoTotal, BoxLayout.Y_AXIS));
        panelPrecoTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelPrecoTotal = new JLabel("Preço Total (R$):");
        labelPrecoTotal.setFont(new Font("Serif", Font.BOLD, 18));
        labelPrecoTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrecoTotal.add(labelPrecoTotal);
        JTextField campoPrecoTotal = new JTextField(compra.getPrecoTotal());
        campoPrecoTotal.setPreferredSize(new Dimension(500, 40));
        campoPrecoTotal.setMaximumSize(new Dimension(500, 40));
        campoPrecoTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrecoTotal.add(campoPrecoTotal);
        painelCampos.add(panelPrecoTotal);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Funcionário (ID)
        JPanel panelFuncionario = new JPanel();
        panelFuncionario.setLayout(new BoxLayout(panelFuncionario, BoxLayout.Y_AXIS));
        panelFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel labelFuncionario = new JLabel("ID do Funcionário:");
        labelFuncionario.setFont(new Font("Serif", Font.BOLD, 18));
        labelFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionario.add(labelFuncionario);
        String funcionarioId = compra.getFuncionario() != null ? String.valueOf(compra.getFuncionario().getId()) : "";
        JTextField campoFuncionario = new JTextField(funcionarioId);
        campoFuncionario.setPreferredSize(new Dimension(500, 40));
        campoFuncionario.setMaximumSize(new Dimension(500, 40));
        campoFuncionario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFuncionario.add(campoFuncionario);

        painelCampos.add(panelFuncionario);

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
                compra.setData(campoData.getText().trim());
                compra.setHora(campoHora.getText().trim());
                compra.setQuantidade(campoQuantidade.getText().trim());
                compra.setMeioPagamento((String) comboMeioPagamento.getSelectedItem());
                compra.setPrecoTotal(campoPrecoTotal.getText().trim());

                String funcionarioIdStr = campoFuncionario.getText().trim();
                int funcionario_Id = Integer.parseInt(funcionarioIdStr);

                CompraController controller = new CompraController();
                controller.editarCompra(compra, funcionario_Id);

                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                        "Compra atualizada com sucesso!",
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
        criarTelaBuscaID("Editar Compra", "Digite o ID da Compra para Editar:", "Buscar",
                (id) -> {
                    CompraController controller = new CompraController();
                    Compra compra = controller.selecionarCompra(id);
                    if (compra != null) {
                        Editar(compra);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhuma compra encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA REMOVER
    public void telaBuscaIDRemover_Remover() {
        criarTelaBuscaID("Remover Compra", "Digite o ID da Compra para Remover:", "Remover",
                (id) -> {
                    CompraController controller = new CompraController();
                    Compra compra = controller.selecionarCompra(id);
                    if (compra != null) {
                        int confirmacao = JOptionPane.showConfirmDialog(
                                MainFrame.getJanela(),
                                "Tem certeza que deseja remover a compra de ID: " + compra.getId() + "?",
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                controller.deletarCompra(id);
                                JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                        "Compra removida com sucesso!",
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
                                "Nenhuma compra encontrada com ID: " + id,
                                "Não Encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
    }

    // TELA BUSCA ID PARA SELECIONAR/VISUALIZAR
    public void telaBuscaIDSelecionar() {
        criarTelaBuscaID("Visualizar Compra", "Digite o ID da Compra para Visualizar:", "Visualizar",
                (id) -> {
                    CompraController controller = new CompraController();
                    Compra compra = controller.selecionarCompra(id);
                    if (compra != null) {
                        Selecionar(compra);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getJanela(),
                                "Nenhuma compra encontrada com ID: " + id,
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
    public void Selecionar(Compra compra) {
        painelPrincipalInterno.removeAll();
        painelPrincipalInterno.setLayout(new BorderLayout());

        if (compra == null) {
            JOptionPane.showMessageDialog(MainFrame.getJanela(),
                    "Compra não encontrada!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            MainFrame.voltarAoMenuPrincipal();
            return;
        }

        JLabel labelTitulo = new JLabel("Informações da Compra");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        painelPrincipalInterno.add(labelTitulo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(8, 2, 10, 10));
        painelDados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Campos de texto não editáveis
        JTextField campoID = criarCampoReadOnly(String.valueOf(compra.getId()));
        JTextField campoData = criarCampoReadOnly(compra.getData());
        JTextField campoHora = criarCampoReadOnly(compra.getHora());
        JTextField campoQuantidade = criarCampoReadOnly(compra.getQuantidade());
        JTextField campoMeioPagamento = criarCampoReadOnly(compra.getMeioPagamento());
        JTextField campoPrecoTotal = criarCampoReadOnly(compra.getPrecoTotal());

        String funcionarioInfo = compra.getFuncionario() != null ? "ID: " + compra.getFuncionario().getId()
                : "Não informado";
        JTextField campoFuncionario = criarCampoReadOnly(funcionarioInfo);

        // Labels
        painelDados.add(new JLabel("ID:"));
        painelDados.add(campoID);
        painelDados.add(new JLabel("Data:"));
        painelDados.add(campoData);
        painelDados.add(new JLabel("Hora:"));
        painelDados.add(campoHora);
        painelDados.add(new JLabel("Quantidade:"));
        painelDados.add(campoQuantidade);
        painelDados.add(new JLabel("Meio de Pagamento:"));
        painelDados.add(campoMeioPagamento);
        painelDados.add(new JLabel("Preço Total:"));
        painelDados.add(campoPrecoTotal);
        painelDados.add(new JLabel("Funcionário:"));
        painelDados.add(campoFuncionario);

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

        JLabel labelTitulo = new JLabel("Listar Todas as Compras");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);
        painelPrincipalInterno.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        try {
            CompraController controller = new CompraController();
            java.util.List<Compra> compras = controller.listarCompras();

            if (compras == null || compras.isEmpty()) {
                JLabel labelVazio = new JLabel("Nenhuma compra cadastrada no sistema.");
                labelVazio.setFont(new Font("Serif", Font.BOLD, 20));
                labelVazio.setHorizontalAlignment(JLabel.CENTER);
                labelVazio.setForeground(Color.GRAY);
                painelCentral.add(labelVazio, BorderLayout.CENTER);
            } else {
                String[] colunas = { "ID", "Data", "Hora", "Quantidade", "Meio Pagamento", "Preço Total", "Visitante" };
                Object[][] dados = new Object[compras.size()][7];

                for (int i = 0; i < compras.size(); i++) {
                    Compra compra = compras.get(i);
                    dados[i][0] = compra.getId();
                    dados[i][1] = compra.getData() != null ? compra.getData() : "";
                    dados[i][2] = compra.getHora() != null ? compra.getHora() : "";
                    dados[i][3] = compra.getQuantidade() != null ? compra.getQuantidade() : "";
                    dados[i][4] = compra.getMeioPagamento() != null ? compra.getMeioPagamento() : "";
                    dados[i][5] = compra.getPrecoTotal() != null ? compra.getPrecoTotal() : "";
                }

                JTable tabela = new JTable(dados, colunas);
                tabela.setFont(new Font("Serif", Font.PLAIN, 12));
                tabela.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
                tabela.setRowHeight(25);
                tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
                tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(6).setPreferredWidth(150);

                JScrollPane scrollPane = new JScrollPane(tabela);
                scrollPane.setPreferredSize(new Dimension(1100, 500));
                painelCentral.add(scrollPane, BorderLayout.CENTER);

                JLabel labelContador = new JLabel("Total de compras encontradas: " + compras.size());
                labelContador.setFont(new Font("Serif", Font.BOLD, 14));
                labelContador.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                painelCentral.add(labelContador, BorderLayout.SOUTH);
            }

        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Erro ao carregar compras: " + ex.getMessage());
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