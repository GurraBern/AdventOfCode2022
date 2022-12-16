import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class Day10 {

    private static int cycle = 0;
    private static int regX = 1;
    private static List<Integer> strengthLevels = new ArrayList<>();

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

        int res1 = 0;
        for (var item : strengthLevels){
            res1 += item;
        }

        System.out.println("Solution part 1: " + res1);
    }

    private static void addStrengthLevel(){
        if(cycle % 40 == 20){
            strengthLevels.add(cycle * regX);
        }
    }

    private static void addx(int value){
        for(int i = 0; i < 2; i++){
            cycle++;
            addStrengthLevel();
        }

        regX += value;
    }

    private static void noop(){
        cycle++;
        addStrengthLevel();
    }
}

