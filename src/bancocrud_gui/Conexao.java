package bancocrud_gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
   private static final String URL = "jdbc:mysql://localhost:3306/usuarios";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    public static void desconectar(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
                System.out.println("Conexão encerrada com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar a conexão: " + e.getMessage());
        }
    }  
}
