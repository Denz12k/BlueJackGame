package BlueJackGame;
import java.util.Random;
public class HandDecks {
    GameDeck myDeck = new GameDeck();
    private Card[] comHandCards;
    public Card[] getComHandCards() {
        return comHandCards;
    }
    public void setComHandCards(Card[] comHandCards_) {
        comHandCards = comHandCards_;
    }
    public void ComDeck(){
        String[] colors = {"B","G","Y","R"};
        Random rd =new Random();
        Card[] x10 = new Card[10];
        comHandCards = new Card[4];
        for(int i=0;i<5;i++){
            x10[i] = myDeck.getCards()[i];
        }
        for(int i=5;i<8;i++){
            int randomColor = rd.nextInt(4);
            int negativeOrPositive = rd.nextInt(2);
            int a;
            if(negativeOrPositive==0){
                a = rd.nextInt(6)+1;
            }
            else{
                a =rd.nextInt(6)-6;
            }
            x10[i] = new Card(colors[randomColor], a );
        }
        for(int i =8;i<10;i++){
            int randomPersentage = rd.nextInt(10)+1;
            if (randomPersentage<9){
                int randomColor = rd.nextInt(4);
                int negativeOrPositive = rd.nextInt(2);
                int a;
                if(negativeOrPositive==0){
                    a = rd.nextInt(6)+1;
                }
                else{
                    a =rd.nextInt(6)-6;
                }
                x10[i] = new Card(colors[randomColor], a );
                }
            else if (randomPersentage==9){
                x10[i] = new Card("(x2)",0);
            }
            else{
                x10[i] = new Card ("(+/-)",0);
            }
        }
        myDeck.Shuffle(x10);
        for(int i =0;i<4;i++){
            comHandCards[i] = x10[i];
        }  
    }
    private Card[] plaHandCards;
    public Card[] getPlaHandCards() {
        return plaHandCards;
    }
    public void setPlaHandCards(Card[] plaHandCards_) {
        plaHandCards = plaHandCards_;
    }
    public void PlaDeck(){
        String[] colors = {"B","G","Y","R"};
        Random rd =new Random();
        Card[] x10 = new Card[10];
        plaHandCards = new Card[4];
        for(int i=0,y=39;i<5;i++){
            x10[i] = myDeck.getCards()[y];
            y--;
        }
        for(int i=5;i<8;i++){
            int randomColor = rd.nextInt(4);
            int negativeOrPositive = rd.nextInt(2);
            int a;
            if(negativeOrPositive==0){
                a = rd.nextInt(6)+1;
            }
            else{
                a =rd.nextInt(6)-6;
            }
            x10[i] = new Card(colors[randomColor], a );
        }
        for(int i =8;i<10;i++){
            int randomPersentage = rd.nextInt(10)+1;
            if (randomPersentage<9){
                int randomColor = rd.nextInt(4);
                int negativeOrPositive = rd.nextInt(2);
                int a;
                if(negativeOrPositive==0){
                    a = rd.nextInt(6)+1;
                }
                else{
                    a =rd.nextInt(6)-6;
                }
                x10[i] = new Card(colors[randomColor], a );
                }
            else if (randomPersentage==9){
                x10[i] = new Card("(x2)",0);
            }
            else{
                x10[i] = new Card ("(+/-)",0);
            }
        }
        myDeck.Shuffle(x10);
        for(int i =0;i<4;i++){
            plaHandCards[i] = x10[i];
        }  
    }
}