import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o número da conta
        System.out.println("Por favor, digite o número da Conta:");
        int numero = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer

        // Solicita a agência
        System.out.println("Por favor, digite o número da Agência:");
        String agencia = scanner.nextLine();

        // Solicita o nome do cliente
        System.out.println("Por favor, digite o nome do Cliente:");
        String cliente = scanner.nextLine();

        // Solicita o saldo
        System.out.println("Por favor, digite o saldo:");
        double saldo = scanner.nextDouble();

        // Formatação de moeda no estilo brasileiro (sem Locale)
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat formatoMoeda = new DecimalFormat("R$ #,##0.00", symbols);
        String saldoFormatado = formatoMoeda.format(saldo);

        // Exibe a mensagem final
        System.out.println();
        System.out.println("Olá " + cliente + ", obrigado por criar uma conta em nosso banco.");
        System.out.println("Sua agência é " + agencia + ", conta " + numero + " e seu saldo " + saldoFormatado + " já está disponível para saque.");

        scanner.close();
    }
}