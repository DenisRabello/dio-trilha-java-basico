public abstract class Conta {
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    // Construtor
    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    // Métodos
    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso! Saldo atual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito realizado com sucesso! Saldo atual: " + saldo);
    }

    public void transferir(double valor, Conta contaDestino) {
        if (valor <= saldo) {
            this.sacar(valor);
            contaDestino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    public void imprimirExtrato() {
        System.out.println("Extrato da conta de " + cliente.getNome());
        System.out.println("Número da conta: " + numero);
        System.out.println("Saldo: " + saldo);
    }

    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
