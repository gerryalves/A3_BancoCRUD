package bancocrud;

public class Usuario {
    private String numero;
    private String senha;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String rg;
    private String sexo; // Novo campo para o sexo (M ou F)

    public Usuario(String numero, String senha, String nomeCompleto, String cpf, String email, String rg, String sexo) {
        this.numero = numero;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.rg = rg;
        this.sexo = sexo;
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

    public String getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        return "Nome: " + nomeCompleto + "\nCPF: " + cpf + "\nE-mail: " + email + "\nRG: " + rg;
    }

    
}




