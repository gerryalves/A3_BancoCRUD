package bancocrud;

/**
 *
 * @author Gerry
 */
import java.time.LocalDate;

public class Usuario {
    private String numero;
    private String senha;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String rg;
    private LocalDate dataNascimento;

    public Usuario(String numero, String senha, String nomeCompleto, String cpf, String email, String rg, LocalDate dataNascimento) {
        this.numero = numero;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    public String getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getRg() {
        return rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
