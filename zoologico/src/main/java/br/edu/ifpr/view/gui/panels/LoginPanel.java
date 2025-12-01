package br.edu.ifpr.view.gui.panels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel {

    public static void main(String[] args) {
        criarTelaLogin();
    }

    private static void criarTelaLogin() {

        JFrame frame = new JFrame("Sistema - Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel labelSenha = new JLabel("Digite a sua senha:");
        JPasswordField campoSenha = new JPasswordField(15);

        JButton botaoLogin = new JButton("Entrar");

        panel.add(labelSenha);
        panel.add(campoSenha);
        panel.add(botaoLogin);

        frame.add(panel);
        frame.setVisible(true);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
String senha = new String(campoSenha.getPassword());

if (senha.length() < 2) {
    JOptionPane.showMessageDialog(frame, "Senha muito curta!");
    return;
}

String prefixo2 = senha.substring(0, 2); // pega "01", "02", "10", etc.

frame.dispose();

//if gerente

switch (prefixo2) {

    case "01":
        AnimalPanel.main(null);
        break;

    case "02":
        AgendaAnimalPanel.main(null);
        break;

    case "03":
        FuncionarioPanel.main(null);
        break;

    case "04":
        AgendaFuncionarioPanel.main(null);
        break;

    case "05":
        CargoPanel.main(null);
        break;

    case "06":
        AreaPanel.main(null);
        break;

    case "07":
        HabitatPanel.main(null);
        break;

    case "08":
        VendaPanel.main(null);
        break;

    case "09":
        RotinaAlimentarPanel.main(null);
        break;

    case "10":
        FornecedorPanel.main(null);
        break;

    case "11":
        AlimentoPanel.main(null);
        break;

    case "12":
        VeterinarioPanel.main(null);
        break;

    default:
        JOptionPane.showMessageDialog(null,
                "Senha inválida ou não atribuída a nenhum módulo!");
        criarTelaLogin();
}
            }
        });
    }
}
