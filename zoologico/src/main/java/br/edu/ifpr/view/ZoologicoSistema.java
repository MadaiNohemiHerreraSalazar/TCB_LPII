package br.edu.ifpr.view;

import javax.swing.*;

public class ZoologicoSistema {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame ventana = new JFrame("Sistema del Zoológico");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1200, 800);
        ventana.setLocationRelativeTo(null); // Centrar en pantalla
        
        // Crear panel principal
        JPanel panel = new JPanel();
        ventana.add(panel);
        
        // Hacer visible la ventana
        ventana.setVisible(true);

        
    }
}