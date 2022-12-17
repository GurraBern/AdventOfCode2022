import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.*;

public class Day10 {

    private static int cycle = 0;
    private static int incCycle = 0;
    private static int regX = 1;
    private static List<Integer> strengthLevels = new ArrayList<>();
    private static String gridDisplay = "";

    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay10.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNext()) {
            var data = myReader.nextLine().split(" ");
            if(data[0].equals("addx")){
                addx(Integer.parseInt(data[1]));
            } else {
                noop();
            }
        }

        int res = 0;
        for (var item : strengthLevels){
            res += item;
        }

        System.out.println("Solution part 1: " + res);
        printGrid();
    }

    private static void printGrid(){
        String[] result = gridDisplay.split("(?<=\\G.{" + 40 + "})");

        System.out.println("Solution part 2: ");
        for (var item : result){
            System.out.println(item);
        }
    }

    private static void drawpixel(){
        if(cycle % 40 == 0)
            incCycle = 0;

        if(incCycle >= regX-1 && incCycle <= regX+1) {
            gridDisplay += "#";
        } else {
            gridDisplay += ".";
        }
    }

    private static void addStrengthLevel(){
        if(cycle % 40 == 20){
            strengthLevels.add(cycle * regX);
        }
    }

    private static void incCycle(){
        cycle++;
        incCycle++;
    }

    private static void addx(int value){
        for(int i = 0; i < 2; i++){
            drawpixel();
            incCycle();
            addStrengthLevel();
        }

        regX += value;
    }

    private static void noop(){
        drawpixel();
        incCycle();
        addStrengthLevel();
    }
}

