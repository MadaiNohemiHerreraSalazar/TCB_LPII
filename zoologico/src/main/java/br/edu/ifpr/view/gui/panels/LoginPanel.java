package br.edu.ifpr.view.gui.panels;

import javax.swing.*;

import br.edu.ifpr.view.gui.MainFrame;

import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel() {

        setLayout(new BorderLayout());

        // ===============================
        // TÍTULO
        // ===============================
        JLabel labelTitulo = new JLabel("Validação de Entrada");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        painelTitulo.add(labelTitulo, BorderLayout.CENTER);

        add(painelTitulo, BorderLayout.NORTH);

        // ===============================
        // PAINEL CENTRAL
        // ===============================
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel labelSenha = new JLabel("Digite sua senha:");
        labelSenha.setFont(new Font("Serif", Font.BOLD, 22));
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(labelSenha);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // ===============================
        // INPUT + BOTÕES
        // ===============================
        JPanel painelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JPasswordField campoSenha = new JPasswordField(15);
        campoSenha.setFont(new Font("Serif", Font.PLAIN, 18));

        JButton botaoEntrar = new JButton("Entrar");
        botaoEntrar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoEntrar.setBackground(Color.BLACK);
        botaoEntrar.setForeground(Color.WHITE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Serif", Font.BOLD, 16));
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setForeground(Color.WHITE);


        painelInput.add(campoSenha);
        painelInput.add(botaoEntrar);
        painelInput.add(botaoVoltar);

        painelCentral.add(painelInput);

        add(painelCentral, BorderLayout.CENTER);

        // ===============================
        // LÓGICA DO LOGIN
        // ===============================
        botaoEntrar.addActionListener(e -> realizarLogin(campoSenha));
        campoSenha.addActionListener(e -> realizarLogin(campoSenha));

        botaoVoltar.addActionListener(e -> MainFrame.voltarAoMenuPrincipal());
    }

    // ===============================
    // MÉTODO QUE VALIDA E REDIRECIONA
    // ===============================
    private void realizarLogin(JPasswordField campoSenha) {

        String senha = new String(campoSenha.getPassword()).trim();

        if (senha.length() < 2) {
            JOptionPane.showMessageDialog(this, "Senha muito curta!");
            return;
        }

        // ============================
        // ✔ SENHA MESTRA - GERENTE
        // ============================
        if (senha.equals("2000")) {
            MainFrame.mostrarPanel(new GerentePanel());
            return;
        }

        // Prefixo 2 primeiros dígitos
        String prefixo = senha.substring(0, 2);

        switch (prefixo) {
            case "01":
                MainFrame.mostrarPanel(new AnimalPanel());
                break;

            case "02":
                MainFrame.mostrarPanel(new AgendaAnimalPanel());
                break;

            case "03":
                MainFrame.mostrarPanel(new FuncionarioPanel());
                break;

            case "04":
                MainFrame.mostrarPanel(new AgendaFuncionarioPanel());
                break;

            case "05":
                MainFrame.mostrarPanel(new CargoPanel());
                break;

            case "06":
                MainFrame.mostrarPanel(new AreaPanel());
                break;

            case "07":
                MainFrame.mostrarPanel(new HabitatPanel());
                break;

            case "08":
                MainFrame.mostrarPanel(new RotinaAlimentarPanel());
                break;

            case "09":
                MainFrame.mostrarPanel(new FornecedorPanel());
                break;

            case "10":
                MainFrame.mostrarPanel(new AlimentoPanel());
                break;

            case "11":
                MainFrame.mostrarPanel(new VeterinarioPanel());
                break;


            default:
                JOptionPane.showMessageDialog(this, "Senha inválida!");
        }
    }

    // Chamada principal
    public static void iniciarLogin() {
        MainFrame.mostrarPanel(new LoginPanel());
    }
}
