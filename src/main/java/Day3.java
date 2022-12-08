import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay3.txt");
        Scanner myReader = new Scanner(myObj);

        HashMap<Character, Integer> resultMap = new HashMap<>();
        String resultLetters = "";

        List<Character> res = new ArrayList<>();
        List<Character> finalRes = new ArrayList<>();

        List<List<Character>> charList = new ArrayList<>();
        int i = 0;
        List<String> groupStrings = new ArrayList<>();
        while (myReader.hasNextLine()) {
            var inputString = myReader.nextLine();
            int mid = inputString.length() / 2; //get the middle of the String
            String[] compartments = {inputString.substring(0, mid), inputString.substring(mid)};
            String firstCompartments = compartments[0];
            var secondCompartments = compartments[1].toCharArray();

            //Part 1
            /*for (var letter : secondCompartments) {
                if (firstCompartments.indexOf(letter) >= 0) {
                    resultLetters += letter;
                    break;
                }
            }*/

            //Part 2
            List<Character> tempList = new ArrayList<>();
            for (var t : inputString.toCharArray()){
                tempList.add(t);
            }
            charList.add(tempList);

            i++;
            Set<Character> test = new HashSet<>();
            if(i >= 3){
                var bb = charList.get(0);
                for(int j = 1; j < charList.size(); j++){
                    bb.retainAll(charList.get(j));
                }
                for (var item : bb){
                    test.add(item);
                }
                for (var item : test){
                    finalRes.add(item);
                }
                i=0;
                charList.clear();
            }
        }

        var letterValues = numerize("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int sum = 0;
        for (var resLetter : finalRes) {
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