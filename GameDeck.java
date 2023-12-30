package BlueJack;
import java.util.Random;
public class GameDeck {
    private Card[] cards;
    public Card[] getCards() {
        return cards;
    }
    public void setCards(Card[] cards_) {
        cards = cards_;
    }
    private Card[] gameDeck;
    public Card[] getGameDeck() {
        return gameDeck;
    }
    public void setGameDeck(Card[] gameDeck_) {
        gameDeck = gameDeck_;
    }
    public GameDeck(){
        cards = new Card[40];
        String[] colors = {"B","R","Y","G"};
        for(int i = 0,index = 0;i<colors.length;i++){
            for(int x = 1;x<=10;x++){
                cards[index] = new Card(colors[i], x);
                index++;
            }
        }
        Shuffle(cards);
        gameDeck = new Card[30];
        for(int i=0,x = 5;i<gameDeck.length;i++){
            gameDeck[i] = cards[x];
            x++;
        }
    }
    public void Shuffle(Card[] deck){
        Random rd = new Random();
        for(int i =0;i<deck.length;i++){
            int x = rd.nextInt(deck.length);
            Card temp = deck[i];
            deck[i] = deck[x];
            deck[x] = temp;
        }
    }
}