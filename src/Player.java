public class Player {
    private String name;
    private Deck gameDeck;
    private Deck prizeDeck;

    //creates a Player object with the given name and 2 empty decks
    public Player(String name) {
        this.name = name;
        gameDeck = new Deck(false);
        prizeDeck = new Deck(false);
    }

    //adds the card to the game deck
    public void addToGameDeck(Card card) {
        this.gameDeck.addCard(card);
    }
    //adds the card to the prize deck
    public void addToPrizeDeck(Card card) {
        this.prizeDeck.addCard(card);
    }
    //adds the entire deck to the prize deck
    public void addToPrizeDeck(Deck deck) {
        while (!deck.isEmpty()) {
            this.addToPrizeDeck(deck.removeTopCard());
        }
    }
    //draws the first card from the game deck.
    //if the game deck is empty then the method turns
    // the prize deck to the game deck
    public Card drawCard() {
        //changing the prize deck to the game deck
        if(gameDeck.isEmpty() && !prizeDeck.isEmpty()) {
            //shuffling the prize deck before the switch
            prizeDeck.shuffle();
            gameDeck = prizeDeck;
            //emptying the prize deck
            prizeDeck = new Deck(false);

        }
        //returning null if both decks are empty
        else if (this.outOfCards()) {
            return null;
        }
        return gameDeck.removeTopCard();
    }

    // return true if the player is completely out of cards and false otherwise
    public  boolean outOfCards() {
        return prizeDeck.isEmpty() && gameDeck.isEmpty();
    }

    //returns the players name
    @Override
    public String toString() {
        return name;
    }
}
