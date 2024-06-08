package bancocrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
  
 public class BancoCRUD {

    private final Map<String, ContaBancaria> contas = new HashMap<>();
    private final Map<String, Usuario> usuarios = new HashMap<>();
    private Usuario usuarioLogado = null; // Armazena o usuário logado
   
   public void criarConta() throws SQLException {
        String numero = JOptionPane.showInputDialog("Digite o número da conta (5 dígitos):");
        if (numero.matches("\\d{5}")) {
            String senha = JOptionPane.showInputDialog("Digite a senha (6 dígitos numéricos):");
            if (senha.matches("\\d{6}")) {
                String nomeCompleto = JOptionPane.showInputDialog("Digite seu nome completo:");
                String cpf = JOptionPane.showInputDialog("Digite seu CPF (11 dígitos numéricos):");
                if (cpf.matches("\\d{11}")) {
                    String email = JOptionPane.showInputDialog("Digite seu e-mail:");
                    String rg = JOptionPane.showInputDialog("Digite seu RG (10 dígitos numéricos):");
                    if (rg.matches("\\d{10}")) {
                        //String dataNascimentoStr = JOptionPane.showInputDialog("Digite sua data de nascimento (formato: dd/mm/aaaa):");
                        //try {
                            //LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            //LocalDate hoje = LocalDate.now();
                            //Period periodo = Period.between(dataNascimento, hoje);
                            //int idade = periodo.getYears();

                            //if (idade >= 18) {
                                String sexo = JOptionPane.showInputDialog("Digite o sexo (M para Masculino, F para Feminino):");

                                // Salvar os dados no banco de dados
                                try (Connection conexao = Conexao.conectar()) {
                                    String sql = "INSERT INTO contas (numero, senha, nomeCompleto, cpf, email, rg, sexo, saldo) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
                                    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                                        stmt.setString(1, numero);
                                        stmt.setString(2, senha);
                                        stmt.setString(3, nomeCompleto);
                                        stmt.setString(4, cpf);
                                        stmt.setString(5, email);
                                        stmt.setString(6, rg);
                                        stmt.setString(7, sexo);
                                        stmt.executeUpdate();
                                        JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                                    } catch (SQLException e) {
                                        JOptionPane.showMessageDialog(null, "Erro ao inserir dados na tabela de contas: " + e.getMessage());
                                    }
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
                                }

                            //} else {
                                //JOptionPane.showMessageDialog(null, "Você deve ter 18 anos ou mais para criar uma conta.");
                            //}
                        //} catch (DateTimeParseException e) {
                            //JOptionPane.showMessageDialog(null, "Data de nascimento inválida. Use o formato dd/mm/aaaa.");
                        //}
                    } else {
                        JOptionPane.showMessageDialog(null, "RG inválido. Deve conter exatamente 10 dígitos numéricos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senha inválida. Deve conter exatamente 6 dígitos numéricos.");
            }
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
        try (Connection conexao = Conexao.conectar()) {
            String sql = "SELECT * FROM contas WHERE numero = ? AND senha = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, numero);
                stmt.setString(2, senha);
                try (ResultSet resultado = stmt.executeQuery()) {
                    if (resultado.next()) {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            usuarioLogado = usuario; // Define o usuário logado
            menuLogado(); // Exibe o menu após o login
        } else {
            JOptionPane.showMessageDialog(null, "Número da conta ou senha inválidos.");
        }
                }
            }
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}    
    public void menuLogado() {
    int opcao;
    do {
        opcao = Integer.parseInt(JOptionPane.showInputDialog(
                "--- Conta poupança ---\n" +
                "1. Visualizar saldo\n" +
                "2. Depositar\n" +
                "3. Sacar\n" +
                "4. Transferir para outra conta\n" +
                "5. Extrato\n" +
                "6. Deletar conta\n" +
                "7. Alterar dados cadastrados\n" +
                "8. Visualizar dados cadastrados\n" +
                "9. Sair da conta\n" +
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
                extrato();
                break;
            case 6:
                deletarConta();
                break;
            case 7:
                usuarioLogado.alterarDados();
                break;
            case 8:
                visualizarDadosCadastrados(); // Novo método para exibir os dados
                break;
            case 9:
                usuarioLogado = null;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                break;
        }
    } while (opcao != 9);
}

private void visualizarDadosCadastrados() {
    JOptionPane.showMessageDialog(null, usuarioLogado.toString());
}



    
    public void visualizarSaldo() {
    if (usuarioLogado != null) {
        //JOptionPane.showMessageDialog(null, "Faça login antes de visualizar o saldo.");
    } else {
        String numero = JOptionPane.showInputDialog("Digite o número da conta:");
        try (Connection conexao = Conexao.conectar()) {
            String sql = "SELECT saldo FROM contas WHERE numero = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, numero);
                try (ResultSet resultado = stmt.executeQuery()) {
                    if (resultado.next()) {
                        double saldo = resultado.getDouble("saldo");
                        JOptionPane.showMessageDialog(null, "Saldo disponível: R$ " + saldo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}




    public void depositar() {
    if (usuarioLogado == null) {
        String numero = JOptionPane.showInputDialog("Digite o número da conta:");
        try (Connection conexao = Conexao.conectar()) {
            String sql = "SELECT saldo FROM contas WHERE numero = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, numero);
                try (ResultSet resultado = stmt.executeQuery()) {
                    if (resultado.next()) {
                        double saldoAtual = resultado.getDouble("saldo");
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser depositado: R$"));
                        double novoSaldo = saldoAtual + valor;

                        // Atualiza o saldo no banco de dados
                        String sqlAtualizarSaldo = "UPDATE contas SET saldo = ? WHERE numero = ?";
                        try (PreparedStatement stmtAtualizarSaldo = conexao.prepareStatement(sqlAtualizarSaldo)) {
                            stmtAtualizarSaldo.setDouble(1, novoSaldo);
                            stmtAtualizarSaldo.setString(2, numero);
                            stmtAtualizarSaldo.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso! Novo saldo: R$ " + novoSaldo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    
        //JOptionPane.showMessageDialog(null, "Faça login antes de realizar um depósito.");
    }
}


    public void sacar() {
    if (usuarioLogado == null) {
        String numero = JOptionPane.showInputDialog("Digite o número da conta:");
        try (Connection conexao = Conexao.conectar()) {
            String sql = "SELECT saldo FROM contas WHERE numero = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, numero);
                try (ResultSet resultado = stmt.executeQuery()) {
                    if (resultado.next()) {
                        double saldoAtual = resultado.getDouble("saldo");
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser sacado: R$"));
                        if (valor <= saldoAtual) {
                            double novoSaldo = saldoAtual - valor;

                            // Atualiza o saldo no banco de dados
                            String sqlAtualizarSaldo = "UPDATE contas SET saldo = ? WHERE numero = ?";
                            try (PreparedStatement stmtAtualizarSaldo = conexao.prepareStatement(sqlAtualizarSaldo)) {
                                stmtAtualizarSaldo.setDouble(1, novoSaldo);
                                stmtAtualizarSaldo.setString(2, numero);
                                stmtAtualizarSaldo.executeUpdate();
                            }

                            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso! Novo saldo: R$ " + novoSaldo);
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente para o saque.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Faça login antes de realizar um saque.");
    }
}


    public void transferir() {
    if (usuarioLogado != null) {
        String numeroOrigem = JOptionPane.showInputDialog("Digite o número da sua conta:");
        ContaBancaria contaOrigem = contas.get(numeroOrigem);
        if (contaOrigem != null) {
            JPasswordField senhaField = new JPasswordField();
                int result = JOptionPane.showConfirmDialog(null, senhaField, "Digite a senha:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String senha = new String(senhaField.getPassword());
        if (usuarioLogado.getSenha().equals(senha)) {    
            String numeroDestino = JOptionPane.showInputDialog("Digite o número da conta de destino:");
            ContaBancaria contaDestino = contas.get(numeroDestino);
            if (contaDestino != null) {
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser transferido: R$"));
                if (contaOrigem.sacar(valor)) {
                    contaDestino.depositar(valor);
                    JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                    JOptionPane.showMessageDialog(null, "Novo saldo da conta de origem: R$ " + contaOrigem.getSaldo());
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente para a transferência.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Conta de destino não encontrada.");
            }
            } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta.");
                }
        } else {
            JOptionPane.showMessageDialog(null, "Conta de origem não encontrada.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Faça login antes de realizar uma transferência.");
       }
    }
}

    public void deletarConta() {
        String numero = JOptionPane.showInputDialog("Digite o número da conta que deseja deletar:");
        if (usuarioLogado != null) {
            JOptionPane.showMessageDialog(null, "Conta deletada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Faça login antes de deletar a conta.");
        }
    }
    public void extrato() {
        if (usuarioLogado != null) {
            String numero = JOptionPane.showInputDialog("Digite o número da conta:");
            ContaBancaria conta = contas.get(numero);
            if (conta != null) {
                List<String> transacoes = conta.getTransacoes();
                StringBuilder extrato = new StringBuilder("Extrato da conta " + numero + ":\n");
                for (String transacao : transacoes) {
                    extrato.append(transacao).append("\n");
                }
                JOptionPane.showMessageDialog(null, extrato.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Conta não encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faça login antes de visualizar o extrato.");
        }
    }

    public static void main(String[] args) throws SQLException {
        BancoCRUD banco = new BancoCRUD();
         
        JOptionPane.showMessageDialog(null, "Bem-vindo ao Matrix Bank", "Matrix Bank", JOptionPane.INFORMATION_MESSAGE);
        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "              Matrix Bank \n" +
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

