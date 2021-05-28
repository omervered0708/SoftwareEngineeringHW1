import java.util.ArrayList;

public class WarGame {
    private Player player1;
    private Player player2;
    private Deck mainDeck;
    private int winner;

    public WarGame(String firstName, String secondName) {
        this.winner = 0;
        // create first and second player according to alphabetical order
        int stringComparison = firstName.compareTo(secondName);
        if (stringComparison >= 0) {
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
        System.out.println("Initializing the game...");
        this.mainDeck.shuffle();
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

        while(this.winner == 0) {
            this.round();
        }

        if (this.winner == 1) {
            return this.player1.toString();
        }
        else if (this.winner == 2) {
            return this.player2.toString();
        }
        else {
            return "both players lost";
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
        // check for winner
        int win = this.winCheck();
        if (win != 0) {
            this.winner = win;
            return 0;
        }
        Card firstCard = this.player1.drawCard();
        Card secondCard = this.player2.drawCard();

        if (!startOfWar) {
            System.out.println(this.player1 + " drew " + firstCard);
            System.out.println(this.player2 + " drew " + secondCard);
        }

        this.mainDeck.addCard(firstCard);
        this.mainDeck.addCard(secondCard);

        return firstCard.getNumber() - secondCard.getNumber();
    }

    // return 1 if player 1 won, 2 if player 2 won, 0 if no winner,
    // -1 if both lost (if all the cards are in the main deck)
    private int winCheck() {
        if (this.player1.outOfCards() && !this.player2.outOfCards()) {
            return 2;
        }
        else if (this.player2.outOfCards() && !this.player1.outOfCards()) {
            return 1;
        }
        else if (this.player1.outOfCards() && this.player2.outOfCards()) {
            return -1;
        }
        else return 0;
    }

    private void round() {
        int upperHand = this.throwProcedure();

        if (this.winner != 0) {
            return;
        }

        if (upperHand > 0) {
            this.takePrize(1);
        }
        else if (upperHand < 0) {
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
            upperHand = this.throwProcedure();
            if (this.winner != 0) {
                return;
            }
        }

        if (upperHand > 0) {
            System.out.println(player1 + " won the war");
            this.takePrize(1);
        }
        else if (upperHand < 0) {
            System.out.println(player1 + " won the war");
            this.takePrize(2);
        }
        else {
            // if another tie breaker needed perform it recursively
            tieBreaker();
        }
    }
}
