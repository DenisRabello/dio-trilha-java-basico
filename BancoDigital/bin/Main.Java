public class Main {
    public static void main(String[] args) {
        // Criando clientes
        Cliente cliente1 = new Cliente("Carlos Silva", "123.456.789-00");
        Cliente cliente2 = new Cliente("Ana Pereira", "987.654.321-00");

        // Criando contas
        Conta conta1 = new ContaCorrente(1001, cliente1);
        Conta conta2 = new ContaPoupanca(2001, cliente2);

        // Criando o banco digital
        BancoDigital bancoDigital = new BancoDigital("Banco Digital");

        // Adicionando as contas ao banco digital
        bancoDigital.adicionarConta(conta1);
        bancoDigital.adicionarConta(conta2);

        // Realizando operações bancárias
        conta1.depositar(500);
        conta1.sacar(100);
        conta1.transferir(200, conta2);

        // Imprimir extratos das contas
        bancoDigital.listarContas();
    }
}
