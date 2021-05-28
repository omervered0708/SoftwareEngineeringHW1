public class Player {
    private String name;
    private Deck gameDeck;
    private Deck prizeDeck;

    public Player(String name) {
        this.name = name;
        gameDeck = new Deck(false);
        prizeDeck = new Deck(false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getGameDeck() {
        return gameDeck;
    }

    public Deck getPrizeDeck() {
        return prizeDeck;
    }

     public void addToGameDeck(Card card) {
         this.gameDeck.addCard(card);
     }
    public void addToPrizeDeck(Card card) {
        this.prizeDeck.addCard(card);
    }
     public Card drawCard() {
         if(gameDeck.isEmpty()){
             this.prizeDeck.shuffle();
             this.gameDeck = this.prizeDeck;
             this.prizeDeck = new Deck(false);
         }
         return this.gameDeck.removeTopCard();
     }
     public  boolean outOfCards() {
         return this.prizeDeck.isEmpty() && this.gameDeck.isEmpty();
     }
    @Override
    public String toString() {
        return this.name;
    }
}
