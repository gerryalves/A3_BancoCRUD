package bancocrud_gui;

import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {
 private String numero;
 private double saldo;
 private List<String> transacoes = new ArrayList<>();

    public ContaBancaria(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        transacoes.add("Depósito: R$ " + valor);
    }
    
    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            transacoes.add("Saque: R$ " + valor);
            return true;
        } else {
            return false;
        }
    }
    public void transferir(ContaBancaria contaDestino, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            contaDestino.depositar(valor);
            transacoes.add("Transferência para conta " + contaDestino.getNumero() + ": R$ " + valor);
        } else {
            System.out.println("Saldo insuficiente para a transferência.");
        }

    } 
    public List<String> getTransacoes() {
        return transacoes;
    }  
}
