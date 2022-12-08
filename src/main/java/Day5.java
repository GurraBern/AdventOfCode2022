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

        /*boolean isNum = false;
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
        }*/

        List<Stack<String>> columns = toStacks(boxes);
        executeCommands(columns, commands);
        String res = listTopBoxes(columns);

        System.out.println(res);
    }

    private static void executeCommands(List<Stack<String>> columns, List<String> commands){
        for (var cmd : commands){
            moveCreate(cmd, columns);
        }
    }

    private static List<Stack<String>> toStacks(List<List<String>> boxes){
        List<Stack<String>> columns = new ArrayList<>();
        for (int i = 0; i <= boxes.size(); i++){
            Stack<String> stack = new Stack<>();
            for (List<String> box : boxes) {
                if (box.size() > i && !box.get(i).contains("  ")) {
                    stack.add(box.get(i));
                }
            }
            columns.add(stack);
        }
        return columns;
    }

    private static String listTopBoxes(List<Stack<String>> columns){
        StringBuilder result = new StringBuilder();
        for (var col : columns){
            if(col.size() > 0){
                result.append(col.get(0));;
            }
        }
        return result.toString();
    }

    private static void moveCreate(String command, List<Stack<String>> columns){
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
    }

}