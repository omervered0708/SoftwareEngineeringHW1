public class Card
{
    private int number;
    private Shape shape;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Card(int number, Shape shape) {
        this.number = number;
        this.shape = shape;
    }
    //returns -1 if the current card number is less than the other
    //returns -1 if the current card number is greater than the other
    //and return 0 if they are equal
    public int compare(Card  other)
    {
        if(this.number < other.number)
            return -1;
        if(this.number == other.number)
            return 0;
        return 1;
    }
    //return the unicode of the shape
    private String getShapePicture(){
        return switch (shape) {
            case CLUBS -> "\u2663";
            case HEARTS -> "\u2665";
            case SPADES -> "\u2660";
            case DIAMONDS -> "\u2666";
        };
    }
    //returns a string describing the card
    @Override
    public String toString() {
        String picture = "";
        if(number == 1)
            picture ="Ace";
        else if(number == 11)
            picture = "Jack";
        else if(number == 12)
            picture = "Queen";
        else if(number == 13)
            picture = "King";
        if(picture.equals(""))
            return this.number + "of" + this.getShapePicture();
        return picture + "of" + this.getShapePicture();


    }
}
