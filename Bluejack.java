package BlueJackGame;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class Bluejack {
    public static void main(String[] args) {
        GameDeck gameDeck = new GameDeck();
        HandDecks handDecks = new HandDecks();
        Scanner sc = new Scanner(System.in);
        handDecks.ComDeck();
        handDecks.PlaDeck();
        Card[] cardsDeck30 = new Card[30];
        for(int i =0;i<cardsDeck30.length;i++){
            cardsDeck30[i] = gameDeck.getGameDeck()[i];
        }
        Card[] com4 = new Card[4];
        for (int i =0;i< com4.length;i++){
            com4[i] = handDecks.getComHandCards()[i];
        }
        Card[] pla4 = new Card[4];
        for (int i =0;i<pla4.length;i++){
            pla4[i] = handDecks.getPlaHandCards()[i];
        }
        Card[] plaHand = new Card[9];
        Card[] comHand = new Card[9];
        int plaScore =0;
        int comScore =0;
        int deckIndex =0;
        boolean isSomeone3 = false;
        while(isSomeone3!=true){
            boolean isGameContinue = true;
            boolean isComUseHandCard = false;
            int comSum =0;
            int plaSum =0;
            boolean plaTurn = true;
            boolean comTurn = true;
            int plaIndex =0;
            int comIndex =0;
            for(int i =0;i<9;i++){
                plaHand[i]= new Card(null, 0);
                comHand[i]= new Card(null, 0);
            }
            xBoard(plaHand, pla4, plaSum);
            while (isGameContinue){
                boolean comMore9 = false;
                boolean plaMore9 = false;
                if (plaTurn){
                    plaHand[plaIndex]= cardsDeck30[deckIndex];
                    plaSum = updateSum(plaHand, plaSum);
                    plaIndex++;
                    deckIndex++;
                    if(deckIndex==30){
                        gameDeck.Shuffle(cardsDeck30);
                        deckIndex=0;
                    }
                    boolean isRight = false;
                    String m;
                    do {
                        xBoard(plaHand, pla4, plaSum);
                        System.out.println("Use Card(1-2-3-4) / Stay(5) / Take Card(6)");
                        m = sc.nextLine();
                        if((m.equals("1"))||(m.equals("2"))||(m.equals("3"))||(m.equals("4"))||(m.equals("5"))||(m.equals("6"))){
                            isRight=true;
                        }
                        else{
                            System.out.println("You entered something out of 1 to 6. Try again...");
                        }
                    } while (!isRight);
                    switch (m){
                        case "1","2","3","4":
                            if (pla4[(Integer.parseInt(m)-1)].getColor().equals("(x2)")){
                                plaHand[plaIndex-1] = new Card(plaHand[plaIndex-1].getColor(), (plaHand[plaIndex-1].getValue())*2);
                                plaSum = updateSum(plaHand, plaSum);
                                xBoard(plaHand, pla4, plaSum);
                                plaTurn = false;
                            }
                            else if (pla4[(Integer.parseInt(m)-1)].getColor().equals("(+/-)")){
                                plaHand[plaIndex-1] = new Card(plaHand[plaIndex-1].getColor(), (plaHand[plaIndex-1].getValue())*(-1));
                                plaSum = updateSum(plaHand, plaSum);
                                xBoard(plaHand, pla4, plaSum);
                                plaTurn = false;
                            }
                            else{
                                plaHand[plaIndex] = pla4[(Integer.parseInt(m))-1];
                                plaSum = updateSum(plaHand,plaSum);
                                plaIndex++;
                                plaTurn = false;
                                xBoard(plaHand, pla4, plaSum);
                            }
                        break;
                        case "5":
                            plaTurn = false;
                        break;
                        case "6":
                            plaTurn = true;
                        break;
                    }
                    for(Card x: plaHand){
                        if(x.getValue()==0){
                            plaMore9 = true;
                        }
                    }
                    if(plaMore9==false){
                        System.out.println("Now player have maximum card numbers.(9)");
                        System.out.println("Player automatically stayed.");
                        plaTurn = false;
                    }
                }
                if (comTurn){
                    comHand[comIndex]= cardsDeck30[deckIndex];
                    comSum = updateSum(comHand, comSum);
                    comIndex++;
                    deckIndex++;
                    if(deckIndex==30){
                        gameDeck.Shuffle(cardsDeck30);
                        deckIndex=0;
                    }
                    for(int i =0;i<4;i++){
                        if(com4[i].getValue()!=0){
                            if ((com4[i].getValue()+comSum)==19 || (com4[i].getValue()+comSum)==20){
                                comHand[comIndex] = com4[i];
                                comIndex++;
                                comSum = updateSum(comHand, comSum);
                                comTurn = false;
                                isComUseHandCard = true;
                                break;
                            }
                        }
                    }
                    if(comSum>16){
                        comTurn= false;
                    }
                    for(Card x: comHand){
                        if(x.getValue()==0){
                            comMore9 = true;
                        }
                    }
                    if(comMore9==false){
                        comTurn = false;
                    }
                }
                if (comTurn ==false && plaTurn==false){
                    isGameContinue =false;
                }
            }
            rBoard(comHand, com4, comSum, plaHand, pla4, plaSum);
            if(isComUseHandCard){
                System.out.println("Computer used card in his hand.");
            }
            if (plaSum>comSum && plaSum<=20){
                System.out.println("Player won.");
                plaScore++;
                if((isBlueJack(plaHand)==true)&&plaSum==20){
                    plaScore =3;
                    System.out.println("Player made BlueJack!");
                }
            }
            else if(comSum>plaSum && comSum<=20){
                System.out.println("Computer won.");
                comScore++;
                if((isBlueJack(comHand)==true)&&comSum==20){
                    comScore =3;
                    System.out.println("Computer made BlueJack!");
                }
            }
            else if (comSum>20 && plaSum>20) {
                System.out.println("Draw.");
            }
            else if (comSum==plaSum){
                System.out.println("Draw.");
            }
            else if (plaSum>20 && comSum<=20){
                System.out.println("Computer won.");
                comScore++;
                if((isBlueJack(comHand)==true)&&comSum==20){
                    comScore =3;
                    System.out.println("Computer made BlueJack!");
                }
            }
            else if (plaSum<=20 && comSum>20){
                System.out.println("Player won.");
                plaScore++;
                if((isBlueJack(plaHand)==true)&&plaSum==20){
                    plaScore =3;
                    System.out.println("Player made BlueJack!");
                }
            }
            if(plaScore==3){
                isSomeone3 =true;
            }
            else if(comScore==3){
                isSomeone3=true;
            }
        }
        if(plaScore==3){
        System.out.print("PLAYER IS WINNER!");
        System.out.println(" ("+plaScore+"-"+comScore+")");
        }
        else{
            System.out.print("COMPUTER IS WINNER!");
            System.out.println(" ("+comScore+"-"+plaScore+")");
        }
        String winner;
        if(plaScore>comScore){
            winner = "("+plaScore+"-"+comScore+") Player Won!";
        }
        else{
            winner = "("+comScore+"-"+plaScore+") Computer Won!";
        }
        System.out.print("Enter name and surname: ");
        String nameSurname =sc.nextLine();
        System.out.print("Enter the date: ");
        String date = sc.nextLine();
        SaveIO(nameSurname, date, winner);
        sc.close();
    }
    private static void SaveIO(String nameSurname,String date,String winner){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ProjectFile.txt"))) {
            writer.write(nameSurname + ", " + date +", " +winner);
            System.out.println("Saved to file.");
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void xBoard(Card[] plaHand,Card[] pla4,int plaSum){
        System.out.println("---------------------------------");
        System.out.println("Com Hand: X  X  X  X");
        System.out.println("Com Sum: Cannot be seen");
        System.out.print("Pla Sum: ");
        for(Card card: plaHand){
            if(card.getValue()!=0){
                System.out.print(card+",");
            }
        }
        System.out.println("("+plaSum+")");
        System.out.println("Pla Hand: "+pla4[0]+","+pla4[1]+","+pla4[2]+","+pla4[3]);
        System.out.println("---------------------------------");
    }
    public static int updateSum(Card[] sumDeck,int sumValue){
        sumValue =0;
        for(Card x : sumDeck){
            sumValue += x.getValue();
        }
        return sumValue;
    }
    public static void rBoard(Card[] comHand,Card[] com4,int comSum,Card[] plaHand,Card[] pla4,int plaSum){
        System.out.println("---------------------------------");
        System.out.println("Com Hand: "+com4[0]+","+com4[1]+","+com4[2]+","+com4[3]);
        System.out.print("Com Sum: ");
        for(Card card: comHand){
            if(card.getValue()!=0){
                System.out.print(card+",");
            }
        }
        System.out.println("("+comSum+")");
        System.out.print("Pla Sum: ");
        for(Card card: plaHand){
            if(card.getValue()!=0){
                System.out.print(card+",");
            }
        }
        System.out.println("("+plaSum+")");
        System.out.println("Pla Hand: "+pla4[0]+","+pla4[1]+","+pla4[2]+","+pla4[3]);
        System.out.println("---------------------------------");
    }
    public static boolean isBlueJack(Card[] Hand){
        boolean y = true;
        boolean allNull= true;
        for(Card x : Hand){
            if(x.getValue()!=0){
                if(!(x.getColor().equals("B"))){
                    y = false;
                }
                allNull = false;
            }
        }
        if(allNull){
            return false;
        }
        return y;
    }
}