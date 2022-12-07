import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay5.txt");
        Scanner myReader = new Scanner(myObj);

        Pattern pattern = Pattern.compile(".*\\d.*");
        List<List<String>> boxes = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        boolean isNum = false;
        while (myReader.hasNextLine()) {
            List<String> row = new ArrayList<>();
            var inputString = myReader.nextLine();
            var rowStrings = inputString.split("(?<=\\G.{" + 4 + "})");

            for (var rowStr : rowStrings){
                if(pattern.matcher(rowStr).matches()){
                    isNum = true;
                }
                row.add(rowStr);
            }
            if(!isNum){
                boxes.add(row);
            } else {
                if(inputString.contains("m")){
                    commands.add(inputString);
                }
            }
        }

        List<Stack<String>> columns = new ArrayList<>();
        for (int i = 0; i <= boxes.size(); i++){
            Stack<String> col = new Stack<>();
            for (int j = 0; j < boxes.size(); j++){
                if(boxes.get(j).size() > i && !boxes.get(j).get(i).contains("  ")){
                    var remove = boxes.get(j).get(i);
                    col.add(boxes.get(j).get(i));
                }
            }
            columns.add(col);
        }

        for (var cmd : commands){
            columns = moveCreate(cmd, columns);
        }

    }

    private static List<Stack<String>> moveCreate(String command, List<Stack<String>> columns){
        var str = command.replaceAll("\\D+", " ").trim().split(" ");
        int[] cmdValues = Arrays.stream(str)
                .mapToInt(Integer::parseInt)
                .toArray();

        int numElem = cmdValues[0]; //Move element
        int from = cmdValues[1]-1; //From element
        int to = cmdValues[2]-1; //From element

        for (int i = 0; i < numElem; i++){
            var temp = columns.get(from).remove(0);
            columns.get(to).add(0, temp);
        }

        return columns;
    }

    //Doesnt Work RBLMGVMS
}