public class SistemaNotificacao {

    // Padrão Strategy
    interface NotificacaoStrategy {
        void enviar(String mensagem, String destinatario);
    }

    // Estratégias concretas
    static class EmailNotificacao implements NotificacaoStrategy {
        public void enviar(String mensagem, String destinatario) {
            System.out.println("Email enviado para " + destinatario + ": " + mensagem);
        }
    }

    static class SmsNotificacao implements NotificacaoStrategy {
        public void enviar(String mensagem, String destinatario) {
            System.out.println("SMS enviado para " + destinatario + ": " + mensagem);
        }
    }

    static class PushNotificacao implements NotificacaoStrategy {
        public void enviar(String mensagem, String destinatario) {
            System.out.println("Push enviado para " + destinatario + ": " + mensagem);
        }
    }

    // Enum com tipos de notificação
    enum TipoNotificacao {
        EMAIL, SMS, PUSH
    }

    // Padrão Factory
    static class NotificacaoFactory {
        public static NotificacaoStrategy criar(TipoNotificacao tipo) {
            return switch (tipo) {
                case EMAIL -> new EmailNotificacao();
                case SMS -> new SmsNotificacao();
                case PUSH -> new PushNotificacao();
            };
        }
    }

    // Padrão Singleton
    static class ServicoNotificacao {
        private static ServicoNotificacao instancia;

        private ServicoNotificacao() {}

        public static ServicoNotificacao getInstancia() {
            if (instancia == null) {
                instancia = new ServicoNotificacao();
            }
            return instancia;
        }

        public void notificar(TipoNotificacao tipo, String mensagem, String destinatario) {
            NotificacaoStrategy estrategia = NotificacaoFactory.criar(tipo);
            estrategia.enviar(mensagem, destinatario);
        }
    }

    // Método main para testar
    public static void main(String[] args) {
        ServicoNotificacao servico = ServicoNotificacao.getInstancia();

        servico.notificar(TipoNotificacao.EMAIL, "Bem-vindo ao sistema!", "joao@email.com");
        servico.notificar(TipoNotificacao.SMS, "Seu código é 123456", "+5511999999999");
        servico.notificar(TipoNotificacao.PUSH, "Você tem uma nova mensagem.", "usuario123");
    }
}
