package br.edu.ifpr.view.gui;

import javax.swing.*;
import java.awt.*;


public class TelaInicialPanel extends JPanel {

    public TelaInicialPanel() {

        setLayout(new BorderLayout());

        // ----------- TÍTULO ------------
        JLabel label = new JLabel("Welcome to the system of our ZOO!");
        label.setFont(new Font("Serif", Font.BOLD, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelLabel = new JPanel(new BorderLayout());
        painelLabel.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
        painelLabel.add(label, BorderLayout.CENTER);

        add(painelLabel, BorderLayout.NORTH);


        // ----------- IMAGEM CENTRAL ------------
        ImageIcon imgOriginal =
            new ImageIcon(MainFrame.class.getResource("/imagens/spar.jpg"));

        Image imgRedimensionada =
            imgOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        JLabel labelImagem = new JLabel(new ImageIcon(imgRedimensionada));
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelImagem.setVerticalAlignment(SwingConstants.CENTER);

        JPanel centro = new JPanel(new GridBagLayout());
        centro.add(labelImagem);

        add(centro, BorderLayout.CENTER);


        // ----------- BOTÕES INFERIORES ------------
        JButton btnLogin = new JButton("Ir para Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 22));
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(250, 60));
        btnLogin.addActionListener(e -> MainFrame.iniciar());


        JButton btnSair = new JButton("Sair do Sistema");
        btnSair.setFont(new Font("Arial", Font.BOLD, 22));
        btnSair.setBackground(Color.DARK_GRAY);
        btnSair.setForeground(Color.WHITE);
        btnSair.setPreferredSize(new Dimension(250, 60));

        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente sair do sistema?",
                "Confirmar Saída",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 0, 40, 0));
        painelBotoes.add(btnLogin);
        painelBotoes.add(Box.createHorizontalStrut(20));
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.SOUTH);
    }
}
