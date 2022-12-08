import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("src/main/inputDay5.txt"));

        List<List<String>> boxes = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        boolean isCommand = false;
        while (myReader.hasNextLine()) {
            var line = myReader.nextLine();

            if(!isCommand && Character.isDigit(line.charAt(1))){
                isCommand = true;
            } else if (isCommand) {
                if(!line.isEmpty()){
                    commands.add(line);
                }
            } else {
                var boxRow = line.split("(?<=\\G.{" + 4 + "})");
                List<String> row = List.of(boxRow);
                boxes.add(row);
            }
        }

        var result1 = part1(boxes, commands).replaceAll("[\\[\\] ]", "");
        var result2 = part2(boxes, commands).replaceAll("[\\[\\] ]", "");
        System.out.println("Solution 1: "+ result1);
        System.out.println("Solution 2: "+ result2);
    }

    private static void executeCommands(List<Stack<String>> columns, List<String> commands, boolean moveMultiple){
        for (var cmd : commands){
            moveCreate(cmd, columns, moveMultiple);
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

    private static void moveCreate(String command, List<Stack<String>> columns, boolean moveMultiple){
        var str = command.replaceAll("\\D+", " ").trim().split(" ");
        int[] cmdValues = Arrays.stream(str)
                .mapToInt(Integer::parseInt)
                .toArray();

        int numElem = cmdValues[0]; //Number of elements to move
        int from = cmdValues[1]-1; //From stack
        int to = cmdValues[2]-1; //To stack

        if(moveMultiple){
            List<String> moveBoxes = new ArrayList<>();
            for(int i = 0; i < numElem; i++){
                var temp = columns.get(from).remove(0);
                moveBoxes.add(temp);
            }
            Collections.reverse(moveBoxes);
            for(var item : moveBoxes){
                columns.get(to).add(0, item);
            }

        } else {
            for (int i = 0; i < numElem; i++){
                var temp = columns.get(from).remove(0);
                columns.get(to).add(0, temp);
            }
        }
    }

    private static String part1(List<List<String>> boxes, List<String> commands){
        List<Stack<String>> columns = toStacks(boxes);
        executeCommands(columns, commands, false);
        return listTopBoxes(columns);
    }

    private static String part2(List<List<String>> boxes, List<String> commands){
        List<Stack<String>> columns = toStacks(boxes);
        executeCommands(columns, commands, true);
        return listTopBoxes(columns);
    }
}