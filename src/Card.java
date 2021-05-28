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
    public int compare(Card  other)
    {
        if(this.number < other.number)
            return -1;
        if(this.number == other.number)
            return 0;
        return 1;
    }
    private char getShapeCharacter(){

    }

}
