import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cardArrayList;
    public static Shape[] shapes = {Shape.CLUBS, Shape.DIAMONDS, Shape.SPADES,
                                    Shape.HEARTS};

    public Deck(boolean buildFullDeck) {
        // initialize cardArrayList
        this.cardArrayList = new ArrayList<>();
        if(buildFullDeck) {
            for (Shape s : shapes) {
                for (int j = 1; j < 14; j++) {
                    cardArrayList.add(new Card(j, s));
                }
            }
        }
    }

    // add card to the end of the cardArrayList
    public void addCard(Card card) {
        this.cardArrayList.add(card);
    }

    // remove and return the last card in cardArrayList
    public Card removeTopCard() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            int index = this.cardArrayList.size() - 1;
            Card card = this.cardArrayList.get(index);
            this.cardArrayList.remove(index);
            return card;
        }
    }

    // return if the deck is empty
    public boolean isEmpty() {
        return this.cardArrayList.isEmpty();
    }

    // shuffle the deck
    public void shuffle() {
        // run 50 iteration. in each iteration swap 2 cards in the deck
        for (int i = 0; i < 50; i++) {
            int firstIndex = Main.rnd.nextInt(52);
            int secondIndex = Main.rnd.nextInt(52);
            this.swapCards(firstIndex, secondIndex);
        }
    }

    // swap the cards in indices i%52 and j%52 in cardArrayList
    private void swapCards(int i, int j) {
        Card temp = this.cardArrayList.remove(i % 52);
        this.cardArrayList.add(i%52, this.cardArrayList.get(j%52));
        this.cardArrayList.remove(j%52);
        this.cardArrayList.add(j%52, temp);
    }
}
