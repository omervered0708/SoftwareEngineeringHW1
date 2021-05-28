public class Player {
    private String name;
    private Deck gameDeck;
    private Deck prizeDeck;

    public Player(String name) {
        this.name = name;
        gameDeck = new Deck(false);
        prizeDeck = new Deck(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(Deck gameDeck) {
        this.gameDeck = gameDeck;
    }

    public Deck getPrizeDeck() {
        return prizeDeck;
    }

    public void setPrizeDeck(Deck prizeDeck) {
        this.prizeDeck = prizeDeck;
    }
     public void addToGameDeck(Card card) {
         gameDeck.addCard(card);
     }
    public void addToPrizeDeck(Card card) {
        prizeDeck.addCard(card);
    }
     public Card drawCard() {
         if(gameDeck.isEmpty()){
             prizeDeck.shuffle();
             gameDeck = prizeDeck;
             prizeDeck = new Deck(false);
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
