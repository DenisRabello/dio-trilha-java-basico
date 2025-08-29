import java.util.ArrayList;
import java.util.List;

public class BancoDigital {
    private String nome;
    private List<Conta> contas;

    public BancoDigital(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
        System.out.println("Conta adicionada com sucesso: " + conta.getNumero());
    }

    public Conta buscarConta(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }

    public void listarContas() {
        for (Conta conta : contas) {
            conta.imprimirExtrato();
            System.out.println("---------------");
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }
}
