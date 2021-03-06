public class WarGame {
    private final Player player1;
    private final Player player2;
    // there is no use for two decks to hold thrown cards, one is sufficient
    private final Deck mainDeck;
    private int winner;
    private int roundCount;

    public WarGame(String firstName, String secondName) {
        this.winner = 0;
        this.roundCount = 0;
        // create first and second player according to alphabetical order
        int stringComparison = firstName.compareTo(secondName);
        if (stringComparison <= 0) {
            this.player1 = new Player(firstName);
            this.player2 = new Player(secondName);
        }
        else {
            this.player1 = new Player(secondName);
            this.player2 = new Player(firstName);
        }
        this.mainDeck = new Deck(true);
    }

    // at the end of the method mainDeck is empty
    private void initializeGame() {
        this.winner = 0;
        this.roundCount = 0;
        System.out.println("Initializing the game...");
        this.mainDeck.shuffle();
        // distribute cards
        for (int i = 0; !this.mainDeck.isEmpty(); i++) {
            if (i%2 == 0) {
                this.player1.addToGameDeck(this.mainDeck.removeTopCard());
            }
            else {
                this.player2.addToGameDeck(this.mainDeck.removeTopCard());
            }
        }
    }

    public String start() {
        this.initializeGame();

        do {
            System.out.println("------------------------- Round number " +
                    ++this.roundCount + " -------------------------");
            this.round();
            if (this.winCheck()) {
                break;
            }
        } while(this.winner == 0);

        if (this.winner == 1) {
            return this.player1.toString();
        }
        else if (this.winner == 2) {
            return this.player2.toString();
        }
        else {
            return "both players lost (both ran out of cards simultaneously)";
        }
    }

    private void takePrize(int roundWinner) {
        if (roundWinner == 1) {
            this.player1.addToPrizeDeck(this.mainDeck);
        }
        else {
            this.player2.addToPrizeDeck(this.mainDeck);
        }
    }

    // return positive if first player's card was stronger,
    // negative if second player's card was stronger,
    // zero otherwise
    private int throwProcedure(boolean startOfWar) {
        // check for winner (check that both have cards)
        if (this.winCheck()) {
            return 0;
        }
        Card firstCard = this.player1.drawCard();
        Card secondCard = this.player2.drawCard();

        if (!startOfWar) {
            System.out.println(this.player1 + " drew " + firstCard);
            System.out.println(this.player2 + " drew " + secondCard);
        }
        else {
            System.out.println(this.player1 + " drew a war card");
            System.out.println(this.player2 + " drew a war card");
        }

        this.mainDeck.addCard(firstCard);
        this.mainDeck.addCard(secondCard);

        return firstCard.compare(secondCard);
    }

    // return true of there is a decision or false otherwise
    private boolean winCheck() {
        if (this.player1.outOfCards() && !this.player2.outOfCards()) {
            this.winner = 2;
            return true;
        }
        else if (this.player2.outOfCards() && !this.player1.outOfCards()) {
            this.winner = 1;
            return true;
        }
        else if (this.player1.outOfCards() && this.player2.outOfCards()) {
            this.winner = -1;
            return true;
        }
        else return false;
    }

    private void round() {
        int upperHand = this.throwProcedure(false);

        if (this.winner != 0) {
            return;
        }

        if (upperHand > 0) {
            System.out.println(this.player1 + " won");
            this.takePrize(1);
        }
        else if (upperHand < 0) {
            System.out.println(this.player2 + " won");
            this.takePrize(2);
        }
        else {
            this.tieBreaker();
        }
    }

    // return true if someone won during tie breaker
    private void tieBreaker() {
        System.out.println("Starting a war...");
        int upperHand = 0;
        for (int i = 0; i < 3; i++) {
            upperHand = i < 2 ? this.throwProcedure(true) :
                                this.throwProcedure(false);
            if (this.winner != 0) {
                return;
            }
        }

        if (upperHand > 0) {
            System.out.println(this.player1 + " won the war");
            this.takePrize(1);
        }
        else if (upperHand < 0) {
            System.out.println(this.player2 + " won the war");
            this.takePrize(2);
        }
        else {
            // if another tie breaker needed perform it recursively
            this.tieBreaker();
        }
    }
}
