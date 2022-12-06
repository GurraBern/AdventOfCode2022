import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay3.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
            var inputString = myReader.nextLine();

            //add 4 items to add set
            //if equal to 4 we have the signal start

            for(int i=0; i<inputString.length()-1; i++){

            }

            /*List<Character> charList = new ArrayList<>();
            for(char c : inputString.toCharArray()){
                if(charList.size() < 4){
                    charList.add(c);
                } else {
                    charList.clear();
                }
            }*/
        }
    }

}