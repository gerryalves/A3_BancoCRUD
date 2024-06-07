package bancocrud;

/**
 *
 * @author Gerry
 */
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Usuario {
    private String numero;
    private String senha;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String rg;
    private LocalDate dataNascimento;
    private String sexo; // Novo campo para o sexo (M ou F)

    public Usuario(String numero, String senha, String nomeCompleto, String cpf, String email, String rg, LocalDate dataNascimento, String sexo) {
        this.numero = numero;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        return "Nome: " + nomeCompleto + "\nCPF: " + cpf + "\nE-mail: " + email + "\nRG: " + rg + "\nData de Nascimento: " + dataNascimento;
    }

    public void alterarDados() {
        String opcao = JOptionPane.showInputDialog("Escolha o campo que deseja corrigir:\n1. Nome\n2. E-mail\n3. RG");
        switch (opcao) {
            case "1":
                nomeCompleto = JOptionPane.showInputDialog("Digite o novo nome completo:");
                break;
            case "2":
                email = JOptionPane.showInputDialog("Digite o novo e-mail:");
                break;
            case "3":
                rg = JOptionPane.showInputDialog("Digite o novo RG (10 dígitos numéricos):");
                if (rg.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "RG atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "RG inválido. Deve conter exatamente 10 dígitos numéricos.");
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
        }
    }
}




