import java.util.*;
import java.text.SimpleDateFormat;

enum ColumnType {
    INITIAL, PENDING, CANCELLED, FINAL
}

class Card {
    private String title;
    private String description;
    private Date creationDate;
    private boolean blocked;
    private String blockReason;

    public Card(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationDate = new Date();
        this.blocked = false;
        this.blockReason = null;
    }

    public String getTitle() { return title; }
    public boolean isBlocked() { return blocked; }

    public void block(String reason) {
        blocked = true;
        blockReason = reason;
    }

    public void unblock() {
        blocked = false;
        blockReason = null;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("Card: %s | Criado em: %s | Bloqueado: %s",
            title, sdf.format(creationDate), blocked ? "Sim (" + blockReason + ")" : "Não");
    }
}

class Column {
    private String name;
    private ColumnType type;
    private List<Card> cards;

    public Column(String name, ColumnType type) {
        this.name = name;
        this.type = type;
        this.cards = new ArrayList<>();
    }

    public String getName() { return name; }
    public ColumnType getType() { return type; }
    public List<Card> getCards() { return cards; }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
}

public class Board {
    private String name;
    private List<Column> columns;
    private static Scanner scanner = new Scanner(System.in);
    private static List<Board> boards = new ArrayList<>();
    private static Board selectedBoard = null;

    public Board(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
        columns.add(new Column("Inicial", ColumnType.INITIAL));
        columns.add(new Column("Cancelado", ColumnType.CANCELLED));
        columns.add(new Column("Final", ColumnType.FINAL));
    }

    public String getName() { return name; }
    public List<Column> getColumns() { return columns; }

    public void addPendingColumn(String columnName) {
        columns.add(columns.size() - 1, new Column(columnName, ColumnType.PENDING));
    }

    // Método principal para rodar o programa
    public static void main(String[] args) {
        while (true) {
            if (selectedBoard == null) {
                mainMenu();
            } else {
                boardMenu();
            }
        }
    }

    private static void mainMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1 - Criar novo board");
        System.out.println("2 - Selecionar board");
        System.out.println("3 - Excluir board");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch(option) {
            case 1:
                createBoard();
                break;
            case 2:
                selectBoard();
                break;
            case 3:
                deleteBoard();
                break;
            case 0:
                System.out.println("Saindo...");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void createBoard() {
        System.out.print("Nome do board: ");
        String name = scanner.nextLine();
        Board board = new Board(name);

        System.out.println("Deseja adicionar colunas pendentes? (s/n)");
        String resp = scanner.nextLine();
        while(resp.equalsIgnoreCase("s")) {
            System.out.print("Nome da coluna pendente: ");
            String colName = scanner.nextLine();
            board.addPendingColumn(colName);

            System.out.println("Adicionar mais colunas pendentes? (s/n)");
            resp = scanner.nextLine();
        }

        boards.add(board);
        System.out.println("Board criado com sucesso!");
    }

    private static void selectBoard() {
        if (boards.isEmpty()) {
            System.out.println("Nenhum board criado.");
            return;
        }

        System.out.println("Boards disponíveis:");
        for (int i = 0; i < boards.size(); i++) {
            System.out.printf("%d - %s\n", i, boards.get(i).getName());
        }

        System.out.print("Digite o número do board para selecionar: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx < 0 || idx >= boards.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        selectedBoard = boards.get(idx);
        System.out.println("Board \"" + selectedBoard.getName() + "\" selecionado.");
    }

    private static void deleteBoard() {
        if (boards.isEmpty()) {
            System.out.println("Nenhum board criado.");
            return;
        }

        System.out.println("Boards disponíveis:");
        for (int i = 0; i < boards.size(); i++) {
            System.out.printf("%d - %s\n", i, boards.get(i).getName());
        }

        System.out.print("Digite o número do board para excluir: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx < 0 || idx >= boards.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        boards.remove(idx);
        System.out.println("Board removido com sucesso.");
    }

    private static void boardMenu() {
        System.out.println("\n=== Board: " + selectedBoard.getName() + " ===");
        List<Column> cols = selectedBoard.getColumns();

        for (int i = 0; i < cols.size(); i++) {
            Column c = cols.get(i);
            System.out.printf("%d - %s (%s) - %d cards\n", i, c.getName(), c.getType(), c.getCards().size());
        }

        System.out.println("Ações:");
        System.out.println("1 - Criar card");
        System.out.println("2 - Mover card para próxima coluna");
        System.out.println("3 - Bloquear card");
        System.out.println("4 - Desbloquear card");
        System.out.println("5 - Cancelar card");
        System.out.println("0 - Voltar para menu principal");
        System.out.print("Opção: ");

        int op = scanner.nextInt();
        scanner.nextLine();

        switch(op) {
            case 1:
                createCard();
                break;
            case 2:
                moveCard();
                break;
            case 3:
                blockCard();
                break;
            case 4:
                unblockCard();
                break;
            case 5:
                cancelCard();
                break;
            case 0:
                selectedBoard = null;
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void createCard() {
        Column initialCol = selectedBoard.getColumns().stream()
                .filter(c -> c.getType() == ColumnType.INITIAL)
                .findFirst()
                .orElse(null);

        if (initialCol == null) {
            System.out.println("Coluna inicial não encontrada!");
            return;
        }

        System.out.print("Título do card: ");
        String title = scanner.nextLine();

        System.out.print("Descrição do card: ");
        String desc = scanner.nextLine();

        Card card = new Card(title, desc);
        initialCol.addCard(card);

        System.out.println("Card criado na coluna inicial.");
    }

    private static void moveCard() {
        System.out.println("Selecione a coluna atual do card:");
        List<Column> cols = selectedBoard.getColumns();

        for (int i = 0; i < cols.size(); i++) {
            System.out.printf("%d - %s\n", i, cols.get(i).getName());
        }
        int colIdx = scanner.nextInt();
        scanner.nextLine();

        if (colIdx < 0 || colIdx >= cols.size()) {
            System.out.println("Coluna inválida.");
            return;
        }

        Column colAtual = cols.get(colIdx);

        if (colAtual.getCards().isEmpty()) {
            System.out.println("Nenhum card nessa coluna.");
            return;
        }

        System.out.println("Selecione o card para mover:");
        List<Card> cards = colAtual.getCards();
        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("%d - %s\n", i, cards.get(i).toString());
        }
        int cardIdx = scanner.nextInt();
        scanner.nextLine();

        if (cardIdx < 0 || cardIdx >= cards.size()) {
            System.out.println("Card inválido.");
            return;
        }

        Card card = cards.get(cardIdx);

        if (card.isBlocked()) {
            System.out.println("Card está bloqueado e não pode ser movido.");
            return;
        }

        if (colIdx == cols.size() - 1) {
            System.out.println("Card já está na última coluna.");
            return;
        }

        Column nextCol = cols.get(colIdx + 1);

        // Regra: Não pular etapas, exceto a coluna Cancelado
        colAtual.removeCard(card);
        nextCol.addCard(card);

        System.out.println("Card movido para a coluna " + nextCol.getName());
    }

    private static void blockCard() {
        Column col = selectColumn("bloquear");
        if (col == null) return;

        Card card = selectCard(col, "bloquear");
        if (card == null) return;

        System.out.print("Motivo do bloqueio: ");
        String reason = scanner.nextLine();

        card.block(reason);
        System.out.println("Card bloqueado com sucesso.");
    }

    private static void unblockCard() {
        Column col = selectColumn("desbloquear");
        if (col == null) return;

        Card card = selectCard(col, "desbloquear");
        if (card == null) return;

        System.out.print("Motivo para desbloquear: ");
        scanner.nextLine(); // Só para leitura do motivo, não usado

        card.unblock();
        System.out.println("Card desbloqueado com sucesso.");
    }

    private static void cancelCard() {
        Column cancelCol = selectedBoard.getColumns().stream()
                .filter(c -> c.getType() == ColumnType.CANCELLED)
                .findFirst()
                .orElse(null);

        if (cancelCol == null) {
            System.out.println("Coluna de cancelamento não encontrada!");
            return;
        }

        Column col = selectColumn("cancelar");
        if (col == null) return;

        Card card = selectCard(col, "cancelar");
        if (card == null) return;

        if (card.isBlocked()) {
            System.out.println("Card está bloqueado e não pode ser cancelado.");
            return;
        }

        col.removeCard(card);
        cancelCol.addCard(card);
        System.out.println("Card movido para a coluna Cancelado.");
    }

    private static Column selectColumn(String action) {
        System.out.println("Selecione a coluna para " + action + ":");
        List<Column> cols = selectedBoard.getColumns();
        for (int i = 0; i < cols.size(); i++) {
            System.out.printf("%d - %s\n", i, cols.get(i).getName());
        }
        int colIdx = scanner.nextInt();
        scanner.nextLine();

        if (colIdx < 0 || colIdx >= cols.size()) {
            System.out.println("Coluna inválida.");
            return null;
        }
        return cols.get(colIdx);
    }

    private static Card selectCard(Column col, String action) {
        if (col.getCards().isEmpty()) {
            System.out.println("Nenhum card para " + action + " nesta coluna.");
            return null;
        }

        System.out.println("Selecione o card para " + action + ":");
        List<Card> cards = col.getCards();
        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("%d - %s\n", i, cards.get(i).toString());
        }
        int cardIdx = scanner.nextInt();
        scanner.nextLine();

        if (cardIdx < 0 || cardIdx >= cards.size()) {
            System.out.println("Card inválido.");
            return null;
        }

        return cards.get(cardIdx);
    }
}
