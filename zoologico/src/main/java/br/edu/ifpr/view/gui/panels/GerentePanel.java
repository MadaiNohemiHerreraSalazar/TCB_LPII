package br.edu.ifpr.view.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.edu.ifpr.view.gui.MainFrame;

public class GerentePanel extends JPanel {

    public GerentePanel() {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Painel do Gerente - Selecione um gerenciamento");
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(7, 2, 20, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        String[] gerenciamentos = {
                "Animais", "Agenda de Animais", "Funcionários", "Agenda de Funcionários",
                "Cargos", "Áreas", "Habitats", "Vendas", "Rotinas Alimentares",
                "Fornecedores", "Alimentos", "Veterinários", "Sair do Sistema"
        };

        for (String nome : gerenciamentos) {
            JButton botao = criarBotaoGerenciamento(nome);
            painelBotoes.add(botao);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    private JButton criarBotaoGerenciamento(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.WHITE);

        botao.addActionListener(e -> {
            switch (texto) {

                case "Animais":
                    MainFrame.mostrarPanel(new AnimalPanel());
                    break;
                case "Agenda de Animais":
                    MainFrame.mostrarPanel(new AgendaAnimalPanel());
                    break;
                case "Funcionários":
                    MainFrame.mostrarPanel(new FuncionarioPanel());
                    break;
                case "Agenda de Funcionários":
                    MainFrame.mostrarPanel(new AgendaFuncionarioPanel());
                    break;
                case "Cargos":
                    MainFrame.mostrarPanel(new CargoPanel());
                    break;
                case "Áreas":
                    MainFrame.mostrarPanel(new AreaPanel());
                    break;
                case "Habitats":
                    MainFrame.mostrarPanel(new HabitatPanel());
                    break;
                case "Vendas":
                    MainFrame.mostrarPanel(new CompraPanel());
                    break;
                case "Rotinas Alimentares":
                    MainFrame.mostrarPanel(new RotinaAlimentarPanel());
                    break;
                case "Alimentos":
                    MainFrame.mostrarPanel(new AlimentoPanel());
                    break;
                case "Fornecedores":
                    MainFrame.mostrarPanel(new FornecedorPanel());
                    break;
                case "Veterinários":
                    MainFrame.mostrarPanel(new VeterinarioPanel());
                    break;

                case "Sair do Sistema":
                    int confirm = JOptionPane.showConfirmDialog(
                            this,
                            "Deseja realmente sair?",
                            "Sair",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION)
                        System.exit(0);
                    break;
            }
        });

        return botao;
    }
}
