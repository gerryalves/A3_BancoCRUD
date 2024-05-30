package bancocrud;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
  
 public class BancoCRUD {

    private final Map<String, ContaBancaria> contas = new HashMap<>();
    private final Map<String, Usuario> usuarios = new HashMap<>();
    private Usuario usuarioLogado = null; // Armazena o usuário logado
   
    public void criarConta() {
        String numero = JOptionPane.showInputDialog("Digite o número da conta (5 dígitos):");
        if (numero.matches("\\d{5}")) {
            String senha = JOptionPane.showInputDialog("Digite a senha:");
            contas.put(numero, new ContaBancaria(numero, 0)); // Saldo inicial definido como 0
            usuarios.put(numero, new Usuario(numero, senha)); // Salva o usuário com número da conta e senha
            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
            
        } else {
            JOptionPane.showMessageDialog(null, "Número da conta inválido. Deve conter exatamente 5 dígitos.");
        }
        
    }

    public void login() {
        String numero = JOptionPane.showInputDialog("Digite o número da conta:");
        JPasswordField senhaField = new JPasswordField();
        int result = JOptionPane.showConfirmDialog(null, senhaField, "Digite a senha:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String senha = new String(senhaField.getPassword());
        Usuario usuario = usuarios.get(numero);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            usuarioLogado = usuario; // Define o usuário logado
            menuLogado(); // Exibe o menu após o login
        } else {
            JOptionPane.showMessageDialog(null, "Número da conta ou senha inválidos.");
        }
    }
    }
    public void menuLogado() {
        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "--- Minha conta ---\n" +
                    "1. Visualizar saldo\n" +
                    "2. Depositar\n" +
                    "3. Sacar\n" +
                    "4. Transferir para outra conta\n" + 
                    "5. Deletar conta\n" +        
                    "6. Voltar para o menu inicial\n" +
                    "Escolha uma opção:"));

            switch (opcao) {
                case 1:
                    visualizarSaldo();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    deletarConta();
                    break;
                case 6:
                    usuarioLogado = null; // Desconecta o usuário
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 5);
    }
    
    public void visualizarSaldo() {
        if (usuarioLogado != null) {
            String numero = JOptionPane.showInputDialog("Digite o número da conta:");
            ContaBancaria conta = contas.get(numero);
            if (conta != null) {
                JOptionPane.showMessageDialog(null, "Saldo disponível: R$ " + conta.getSaldo());
            } else {
                JOptionPane.showMessageDialog(null, "Conta não encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faça login antes de visualizar o saldo.");
        }
    }

    public void depositar() {
        // Implementação da opção de depositar
    }
    public void sacar() {
        // Implementação da opção de sacar
    }
    public void transferir() {
        // Implementação da opção de transferir para outra conta
    }

    public void deletarConta() {
        String numero = JOptionPane.showInputDialog("Digite o número da conta que deseja deletar:");
        if (usuarioLogado != null) {
            JOptionPane.showMessageDialog(null, "Conta deletada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Faça login antes de deletar a conta.");
        }
    }

    public static void main(String[] args) {
        BancoCRUD banco = new BancoCRUD();
        ImageIcon icon = new ImageIcon("C:\\Users\\Gerry\\Downloads\\matrix.png"); 
        JOptionPane.showMessageDialog(null, "Bem-vindo ao Matrix Bank", "Matrix Bank", JOptionPane.INFORMATION_MESSAGE, icon);
        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "        Matrix Bank \n" +
                    "1. Criar conta\n" +
                    "2. Login\n" +
                    "3. Sair \n" +
                    "Escolha uma opção:"));

            switch (opcao) {
                case 1:
                    banco.criarConta();
                    break;
                case 2:
                    banco.login();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 3);
    }
}

class ContaBancaria {
    private String numero;
    private double saldo;

    public ContaBancaria(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "numero='" + numero + '\'' +
                ", saldo: R$ " + saldo +
                '}';
    }
}