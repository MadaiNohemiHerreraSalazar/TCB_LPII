package br.edu.ifpr.zoologicio.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conexao;

    private ConnectionFactory() { }

   public static Connection getConnection() {
    try {
        // Cria a conexão se ela não existir ou se estiver fechada
        if (conexao == null || conexao.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/zoologico";
            String user = "giovanna";
            String password = "3321";

            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado ao banco com sucesso");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return conexao;
}

}
