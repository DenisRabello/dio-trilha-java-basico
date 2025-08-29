
public class main {
    public static void main(String[] args) {
        Iphone iphone = new Iphone();

        // Reprodutor Musical
        iphone.selecionarMusica("Imagine - John Lennon");
        iphone.tocar();
        iphone.pausar();

        // Aparelho Telefônico
        iphone.ligar("11999998888");
        iphone.atender();
        iphone.iniciarCorreioVoz();

        // Navegador de Internet
        iphone.exibirPagina("https://www.apple.com");
        iphone.adicionarNovaAba();
        iphone.atualizarPagina();
    }
}
