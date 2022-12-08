import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay6.txt");
        Scanner myReader = new Scanner(myObj);

        var part1 = 0;
        var part2 = 0;
        while (myReader.hasNextLine()) {
            var inputString = myReader.nextLine();
            part1 = checkForMessage(inputString, 4);
            part2 = checkForMessage(inputString, 14);
        }

        System.out.println("Part 1 is: " + part1 + ", Part 2 is: " + part2);
    }

    private static int checkForMessage(String inputString, int size){
        for(int i=0; i<inputString.length()-1; i++){
            Set<Character> charSet = new HashSet<>();
            var split = inputString.substring(i,i+size);
            for (var c : split.toCharArray()){
                charSet.add(c);
            }
            if(charSet.size() >= size){
                return i+size;
            }
        }
        return -1;
    }
}