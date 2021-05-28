public class Player {
    private String name;
    private Deck gameDeck;
    private Deck prizeDeck;

    public Player(String name) {
        this.name = name;
        gameDeck = new Deck(false);
        prizeDeck = new Deck(false);
    }

    public void addToGameDeck(Card card) {
        this.gameDeck.addCard(card);
    }
    public void addToPrizeDeck(Card card) {
        this.prizeDeck.addCard(card);
    }

    public void addToPrizeDeck(Deck deck) {
        while (!deck.isEmpty()) {
            this.addToPrizeDeck(deck.removeTopCard());
        }
    }
    public Card drawCard() {
        if(gameDeck.isEmpty() && !prizeDeck.isEmpty()) {
            prizeDeck.shuffle();
            gameDeck = prizeDeck;
            prizeDeck = new Deck(false);
        }
        else if (this.outOfCards()) {
            return null;
        }
        return gameDeck.removeTopCard();
    }

    public  boolean outOfCards() {
        return prizeDeck.isEmpty() && gameDeck.isEmpty();
    }

    @Override
    public String toString() {
        return name;
    }
}
