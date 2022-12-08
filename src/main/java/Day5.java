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

        //Remap to stacks
        List<Stack<String>> columns = new ArrayList<>();
        /*for (int i = 0; i <= boxes.size(); i++){
            Stack<String> col = new Stack<>();
            for (int j = 0; j < boxes.size(); j++){
                if(boxes.get(j).size() > i && !boxes.get(j).get(i).contains("  ")){
                    col.add(boxes.get(j).get(i));
                }
            }
            columns.add(col);
        }*/
        toStacks(boxes);
        columns = executeCommands(columns, commands);
        String res = showTopObjects(columns);

        System.out.println(res);
    }

    private static List<Stack<String>> executeCommands(List<Stack<String>> columns, List<String> commands){
        for (var cmd : commands){
            columns = moveCreate(cmd, columns);
        }
        return columns;
    }

    private static List<Stack<String>> toStacks(List<List<String>> boxes){
        List<Stack<String>> columns = new ArrayList<>();
        for (int i = 0; i <= boxes.size(); i++){
            Stack<String> col = new Stack<>();
            for (int j = 0; j < boxes.size(); j++){
                if(boxes.get(j).size() > i && !boxes.get(j).get(i).contains("  ")){
                    col.add(boxes.get(j).get(i));
                }
            }
            columns.add(col);
        }
        return columns;
    }

    private static String showTopObjects(List<Stack<String>> columns){
        String result = "";
        for (var col : columns){
            if(col.size() > 0){
                result += col.get(0);;
            }
        }
        return result;
    }

    private static List<Stack<String>> moveCreate(String command, List<Stack<String>> columns){
        var str = command.replaceAll("\\D+", " ").trim().split(" ");
        int[] cmdValues = Arrays.stream(str)
                .mapToInt(Integer::parseInt)
                .toArray();

        int numElem = cmdValues[0]; //Number of elements to move
        int from = cmdValues[1]-1; //From stack
        int to = cmdValues[2]-1; //To stack

        /*for (int i = 0; i < numElem; i++){
            var temp = columns.get(from).remove(0);
            columns.get(to).add(0, temp);
        }*/

        List<String> moveBoxes = new ArrayList<>();
        for(int i = 0; i < numElem; i++){
            var temp = columns.get(from).remove(0);
            moveBoxes.add(temp);
        }

        //Maybe remove if working with stacks in different way
        Collections.reverse(moveBoxes);
        for(var item : moveBoxes){
            columns.get(to).add(0, item);
        }

        return columns;
    }

}