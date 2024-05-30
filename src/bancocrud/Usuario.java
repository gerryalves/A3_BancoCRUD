package bancocrud;

/**
 *
 * @author Gerry
 */
public class Usuario {
    private String numero;
    private String senha;

    public Usuario(String numero, String senha) {
        this.numero = numero;
        this.senha = senha;
    }

    public String getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }
}
