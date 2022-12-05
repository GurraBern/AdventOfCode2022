import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay3.txt");
        Scanner myReader = new Scanner(myObj);

        HashMap<Character, Integer> resultMap = new HashMap<>();
        String resultLetters = "";

        int i = 0;
        List<String> groupStrings = new ArrayList<>();
        while (myReader.hasNextLine()) {
            var inputString = myReader.nextLine();
            int mid = inputString.length() / 2; //get the middle of the String
            String[] compartments = {inputString.substring(0, mid), inputString.substring(mid)};
            String firstCompartments = compartments[0];
            var secondCompartments = compartments[1].toCharArray();

            //Part 1
            for (var letter : secondCompartments) {
                if (firstCompartments.indexOf(letter) >= 0) {
                    resultLetters += letter;
                    break;
                }
            }


            Map<Character, Integer> numValues = new HashMap<>();



            //Part 2
            groupStrings.add(inputString);
            if(i < 3){
                //cmp all 3 strings
                HashSet<Character> chars = new HashSet<>();
                for (var groupStr: groupStrings) {
                    var t = groupStr.toCharArray();
                    for (var b : t) {
                        chars.add(b);
                    }
                }

                var uniqueChars = chars.stream().toList();
                chars.retainAll(uniqueChars);

                //for each array we run retainAll
                var letterValues = numerize("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                int sum = 0;
                for (var resLetter : chars) {
                    int t = letterValues.get(resLetter);
                    sum += t;
                }
var b = sum;
                //do something
                //groupStrings.clear();
            }

            i++;
        }

        var letterValues = numerize("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int sum = 0;
        for (var resLetter : groupStrings) {
            int t = letterValues.get(resLetter);
            sum += t;
        }

        /*for (var resLetter : resultLetters.toCharArray()) {
            int t = letterValues.get(resLetter);
            sum += t;
        }*/

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