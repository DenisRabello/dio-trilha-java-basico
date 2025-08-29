import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Candidato {
    String nome;
    String cpf;
    double nota;
    double salarioPretendido;
    int tentativasContato;

    public Candidato(String nome, String cpf, double nota, double salarioPretendido) {
        this.nome = nome;
        this.cpf = cpf;
        this.nota = nota;
        this.salarioPretendido = salarioPretendido;
        this.tentativasContato = 0;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Nota: " + nota + ", Salário Pretendido: R$ " + salarioPretendido;
    }

    // Método para simular a tentativa de contato com o candidato
    public void realizarContato() {
        tentativasContato++;
        System.out.println("Conseguimos contato com " + nome + " na " + tentativasContato + "ª Tentativa.");
    }
}

class ProcessoSeletivo {
    String nomeProcesso;
    int vagasDisponiveis;
    List<Candidato> candidatos;

    // Salário base da empresa
    double salarioBase = 2000.00;

    public ProcessoSeletivo(String nomeProcesso, int vagasDisponiveis) {
        this.nomeProcesso = nomeProcesso;
        this.vagasDisponiveis = vagasDisponiveis;
        this.candidatos = new ArrayList<>();
    }

    // Método para cadastrar um candidato
    public void cadastrarCandidato(String nome, String cpf, double nota, double salarioPretendido) {
        if (candidatos.size() < vagasDisponiveis) {
            Candidato candidato = new Candidato(nome, cpf, nota, salarioPretendido);
            candidatos.add(candidato);
            System.out.println("Candidato " + nome + " cadastrado com sucesso!");
        } else {
            System.out.println("Não há vagas disponíveis! Já existem " + vagasDisponiveis + " candidatos.");
        }
    }

    // Método para listar todos os candidatos
    public void listarCandidatos() {
        if (candidatos.isEmpty()) {
            System.out.println("Não há candidatos cadastrados.");
        } else {
            System.out.println("Candidatos cadastrados no processo seletivo:");
            for (Candidato candidato : candidatos) {
                System.out.println(candidato);
            }
        }
    }

    // Método para verificar as vagas disponíveis
    public void verificarVagas() {
        System.out.println("Vagas disponíveis: " + (vagasDisponiveis - candidatos.size()));
    }

    // Método para analisar o candidato com base no salário pretendido
    public void analisarCandidato(Candidato candidato) {
        System.out.println("\nAnalisando o candidato: " + candidato.nome);

        if (candidato.salarioPretendido < salarioBase) {
            System.out.println("Ligar para o Candidato " + candidato.nome + ".");
        } else if (candidato.salarioPretendido == salarioBase) {
            System.out.println("Ligar para o Candidato " + candidato.nome + " com contra proposta.");
        } else {
            System.out.println("Aguardando o resultado dos demais candidatos.");
        }
    }

    // Método para selecionar os 5 primeiros candidatos
    public void selecionarCandidatos() {
        if (candidatos.size() > vagasDisponiveis) {
            candidatos = candidatos.subList(0, vagasDisponiveis); // Limita a lista aos 5 primeiros
        }

        // Ordena os candidatos por ordem decrescente de nota
        Collections.sort(candidatos, (c1, c2) -> Double.compare(c2.nota, c1.nota));

        System.out.println("\nCandidatos selecionados e classificados em ordem decrescente:");

        // Criando uma lista com as mensagens sobre os candidatos
        List<String> candidatosSelecionadosMensagem = new ArrayList<>();
        for (int i = 0; i < candidatos.size(); i++) {
            Candidato candidato = candidatos.get(i);
            String mensagem = "O candidato ("
                    + candidato.nome
                    + ") solicitou este valor de salário (R$ "
                    + candidato.salarioPretendido
                    + ").\n"
                    + "O candidato ("
                    + candidato.nome
                    + ") foi selecionado para a vaga.";
            candidatosSelecionadosMensagem.add(mensagem);
        }

        // Exibir as mensagens
        for (String mensagem : candidatosSelecionadosMensagem) {
            System.out.println(mensagem);
        }
    }

    // Método principal para execução do processo seletivo
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Criando o processo seletivo
        ProcessoSeletivo processo = new ProcessoSeletivo("Processo Seletivo para Desenvolvedor", 5);

        // Exibindo a mensagem do Processo Seletivo
        System.out.println("Bem-vindo ao " + processo.nomeProcesso);

        // Cadastro de candidatos automáticos
        processo.cadastrarCandidato("Felipe", "12345678901", 9.0, 1900.00);
        processo.cadastrarCandidato("Amanda", "23456789012", 8.5, 2200.00);
        processo.cadastrarCandidato("Lucas", "34567890123", 7.5, 2000.00);
        processo.cadastrarCandidato("Fernanda", "45678901234", 8.0, 1800.00);
        processo.cadastrarCandidato("Ricardo", "56789012345", 9.5, 2000.00);
        processo.cadastrarCandidato("Juliana", "67890123456", 7.0, 2500.00);

        // Menu de interação
        int opcao;
        do {
            System.out.println("\n--- Menu Processo Seletivo ---");
            System.out.println("1. Cadastrar Candidato");
            System.out.println("2. Listar Candidatos");
            System.out.println("3. Verificar Vagas Disponíveis");
            System.out.println("4. Analisar Candidatos");
            System.out.println("5. Selecionar Candidatos e Classificar");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    // Impede o cadastro de mais de 5 candidatos
                    if (processo.candidatos.size() < 5) {
                        System.out.print("Digite o nome do candidato: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o CPF do candidato: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Digite a nota do candidato: ");
                        double nota = scanner.nextDouble();
                        System.out.print("Digite o salário pretendido pelo candidato: ");
                        double salarioPretendido = scanner.nextDouble();
                        processo.cadastrarCandidato(nome, cpf, nota, salarioPretendido);
                    } else {
                        System.out.println("Já existem 5 candidatos cadastrados. Não é possível cadastrar mais.");
                    }
                    break;
                case 2:
                    processo.listarCandidatos();
                    break;
                case 3:
                    processo.verificarVagas();
                    break;
                case 4:
                    System.out.println("Escolha o candidato para análise:");
                    for (int i = 0; i < processo.candidatos.size(); i++) {
                        System.out.println((i + 1) + ". " + processo.candidatos.get(i).nome);
                    }
                    System.out.print("Digite o número do candidato: ");
                    int index = scanner.nextInt();
                    if (index > 0 && index <= processo.candidatos.size()) {
                        processo.analisarCandidato(processo.candidatos.get(index - 1));
                    } else {
                        System.out.println("Candidato inválido!");
                    }
                    break;
                case 5:
                    processo.selecionarCandidatos();
                    break;
                case 6:
                    System.out.println("Saindo do processo seletivo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 6);

        // Simulação de contato com os candidatos selecionados
        for (Candidato candidato : processo.candidatos) {
            candidato.realizarContato();
        }

        scanner.close();
    }
}//
