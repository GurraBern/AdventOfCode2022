import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay2.txt");
        List<int[]> playerHands = readShapes(myObj);

        var t = play2(playerHands);
    }

    private static int play2(List<int[]> playerHands){
        int myScore = 0;
        int oppScore = 0;

        for (int[] playerHand: playerHands) {
            int oppMove = playerHand[0];
            int myMove = playerHand[1];


            //Should lose
            if(myMove == 1){
                if (oppMove == 1){
                    myScore += 3;
                } else if(oppMove == 2){
                    myScore += 1;
                } else {
                    myScore += 2;
                }
                //Draw
            } else if (myMove == 2) {
                if (oppMove == 1){
                    myScore += 1+3;
                } else if(oppMove == 2){
                    myScore += 2+3;
                } else {
                    myScore += 3+3;
                }
            } else if(myMove == 3){
                if (oppMove == 1){
                    myScore += 2+6;
                } else if(oppMove == 2){
                    myScore += 3+6;
                } else {
                    myScore += 1+6;
                }            }

        }

        return myScore;
    }

    private static int play(List<int[]> playerHands){
        int myScore = 0;
        int oppScore = 0;

        for (int[] playerHand: playerHands) {
            int oppMove = playerHand[0];
            int myMove = playerHand[1];

            if(oppMove == myMove){
                myScore += myMove + 3;
                oppScore += oppMove + 3;
            //Rock -- Paper
            } else if(oppMove == myMove){
                oppScore += oppMove + 3;
                myScore += myMove + 3;
            } else if(oppMove == 2 && myMove == 1){
                oppScore += oppMove + 6;
                myScore += myMove;
            }
            //Rock -- Scissors
            else if (oppMove == 2 && myMove == 3) {
                oppScore += oppMove;
                myScore += myMove+6;
            } else if (oppMove == 3 && myMove == 1) {
                oppScore += oppMove;
                myScore += myMove + 6;
            }
            //Paper -- Scissors
            else if (oppMove == 2 && myMove == 3){
                oppScore += oppMove;
                myScore += myMove + 6;
            } else if (oppMove == 2 && myMove == 2){
                oppScore += oppMove + 3;
                myScore += myMove + 3;
            }

        }

        return myScore;
    }



    private static List<int[]> readShapes(File myObj) throws FileNotFoundException {
        Scanner myReader = new Scanner(myObj);
        List<int[]> handValues = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] playerHand = data.split(" ");

            int i = 0;
            int[] valuePair = new int[2];
            for (String hand : playerHand)
            {
                if(hand.equals("A") || hand.equals("X")){
                    valuePair[i] = 1;
                }else if(hand.equals("B") || hand.equals("Y")){
                    valuePair[i] = 2;
                } else if(hand.equals("C") || hand.equals("Z")){
                    valuePair[i] = 3;
                }
                i++;
            }
            handValues.add(valuePair);
        }

        return handValues;
    }
}
