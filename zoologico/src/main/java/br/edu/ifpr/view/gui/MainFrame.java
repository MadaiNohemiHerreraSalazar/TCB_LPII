package br.edu.ifpr.view.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    public static void main(String[] args) {

        // Criar janela principal com título
        JFrame ventana = new JFrame("Sistema del Zoológico");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1200, 800);
        ventana.setLocationRelativeTo(null); // centralizar

        // Layout principal
        ventana.setLayout(new BorderLayout());

        // Label topo com texto
        JLabel label = new JLabel("Welcome to the system of our ZOO!");
        label.setFont(new Font("Serif", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);

        // Painel para o label topo com espaçamento
        JPanel panelLabel = new JPanel(new BorderLayout());
        panelLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0));
        panelLabel.add(label, BorderLayout.CENTER);
        ventana.add(panelLabel, BorderLayout.NORTH);

        // Carregar e redimensionar imagem
        ImageIcon imagemOriginal = new ImageIcon(MainFrame.class.getResource("/imagens/spar.jpg"));
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imagemRedimensionada);

        // JLabel da imagem, centralizado
        JLabel labelImagen = new JLabel(imagem);
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
        labelImagen.setVerticalAlignment(SwingConstants.CENTER);

        // Painel central com GridBagLayout para garantir centralização
        JPanel centro = new JPanel(new GridBagLayout());
        centro.add(labelImagen);

        ventana.add(centro, BorderLayout.CENTER);

        ventana.setVisible(true);
    }
}
