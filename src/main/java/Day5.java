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

        List<List<String>> columns = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++){
            List<String> col = new ArrayList<>();
            for (int j = 0; j < boxes.size(); j++){
                if(boxes.get(j).size() > i && !boxes.get(j).get(i).contains("  ")){
                    col.add(boxes.get(j).get(i));
                }
            }
            columns.add(col);
        }

        for (var cmd : commands){
            columns = moveCreate(cmd, columns);
        }

        var test = "works?";
    }

    private static List<List<String>> moveCreate(String command, List<List<String>> columns){
        var str = command.replaceAll("\\D+", " ").trim().split(" ");
        int[] cmdValues = Arrays.stream(str)
                .mapToInt(Integer::parseInt)
                .toArray();

        int to = cmdValues[0]-1; //Move element
        int from = cmdValues[1]-1; //From element
        //int to = cmdValues[2]; //To element

        var topIndex = columns.get(from).size()-1;
        var temp = columns.get(from).get(topIndex);
        columns.remove(from);
        columns.get(to).add(temp);



        return columns;
    }
}