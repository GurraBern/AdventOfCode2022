import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay6.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
            var inputString = myReader.nextLine();

            int index = 0;
            for(int i=0; i<inputString.length()-1; i++){
                Set<Character> charSet = new HashSet<>();
                var split = inputString.substring(i,i+4);
                for (var c : split.toCharArray()){
                    charSet.add(c);
                }
                if(charSet.size() >= 4){
                    index = i+4;
                    break;
                }
            }

            var res = index;
        }
    }

}