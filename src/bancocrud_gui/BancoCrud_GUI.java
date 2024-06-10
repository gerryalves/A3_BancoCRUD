package bancocrud_gui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import telas.TelaLogin;

/**
 *
 * @author Gerry
 */
public class BancoCrud_GUI {

    private final Map<String, ContaBancaria> contas = new HashMap<>();
    private final Map<String, Usuario> usuarios = new HashMap<>();
    private Usuario usuarioLogado = null; // Armazena o usuário logado
   
   

    public static void main(String[] args) throws SQLException {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
        
        
    }
    
}
