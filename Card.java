package BlueJackGame;
public class Card {
    private String color;
    private int value;
    public Card(String color_,int value_){
        value = value_;
        color = color_;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value_) {
        value = value_;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color_) {
        color = color_;
    }
    public String toString(){
        if (value == 0){
            return color;
        }
        else{
            return color + value;
        }
    }
}