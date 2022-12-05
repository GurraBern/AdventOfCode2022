import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay3.txt");
        Scanner myReader = new Scanner(myObj);

        HashMap<Character, Integer> resultMap = new HashMap<>();
        String resultLetters = "";
        while (myReader.hasNextLine()) {
            var inputString = myReader.nextLine();

            int mid = inputString.length() / 2; //get the middle of the String
            String[] parts = {inputString.substring(0, mid), inputString.substring(mid)};
            String part1 = parts[0];
            var part2 = parts[1].toCharArray();

            for (var letter : part2) {
                if (part1.indexOf(letter) >= 0) {
                    resultLetters += letter;
                    break;
                }
            }
        }

        var letterValues = numerize("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");

        int sum = 0;
        for (var resLetter : resultLetters.toCharArray()) {
            int t = letterValues.get(resLetter);
            sum += t;
        }

        System.out.println(sum);
    }

    private static HashMap<Character, Integer> numerize(String input){
        HashMap<Character, Integer> letterValues = new HashMap<Character, Integer>();
        var charLetters = input.toCharArray();
        for (int i = 0; i < charLetters.length; i++) {
            letterValues.put(charLetters[i], i + 1);
        }
        return letterValues;
    }
}