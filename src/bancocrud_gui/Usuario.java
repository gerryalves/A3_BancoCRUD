
package bancocrud_gui;

public class Usuario {
    private String numero_conta;
    private String senha;
    private String nome_completo;
    private String cpf;
    private String email;
    private String sexo; 

    public Usuario(String numero, String senha, String nomeCompleto, String cpf, String email, String rg, String sexo) {
        this.numero_conta = numero;
        this.senha = senha;
        this.nome_completo = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.sexo = sexo;
    }

    public String getNumero() {
        return numero_conta;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeCompleto() {
        return nome_completo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        return "Nome: " + nome_completo + "\nCPF: " + cpf + "\nE-mail: " + email;
    }
   
}
