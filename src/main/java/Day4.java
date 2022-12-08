import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/main/inputDay4.txt");
        Scanner myReader = new Scanner(myObj);

        //var sum1 = part1(myReader);
        var sum2 = part2(myReader);
    }

    private static int part2(Scanner myReader) {
        int overlapCounter = 0;
        while (myReader.hasNextLine()){
            var data = myReader.nextLine();
            var elves = data.split(",");
            var elvSections = calcRangesPart1(elves[0]);
            var cmpVal = calcRangesPart1(elves[1]);

            if(!(elvSections[1] < cmpVal[0] || cmpVal[1] < elvSections[0])){
                overlapCounter++;
            }
        }

        return overlapCounter;
    }

    private static int part1(Scanner myReader){
        int overlapCounter = 0;
        while (myReader.hasNextLine()){
            var data = myReader.nextLine();
            var elves = data.split(",");
            var elvSections = calcRangesPart1(elves[0]);
            var cmpVal = calcRangesPart1(elves[1]);

            if(elvSections[0] <= cmpVal[0] && elvSections[1] >= cmpVal[1] || cmpVal[0] <= elvSections[0] && cmpVal[1] >= elvSections[1]){
                overlapCounter++;
            }
        }

        return overlapCounter;
    }

    private static int cmpCounter(int[] elvSections, int[] cmpVal){

        if(elvSections[0] <= cmpVal[0] && elvSections[1] >= cmpVal[1] || cmpVal[0] <= elvSections[0] && cmpVal[1] >= elvSections[1]){
            return 1;
        }
        return 0;
    }

    private static int[] calcRangesPart1(String range){
        var ranges = range.split("-");
        var valFrom = Integer.parseInt(ranges[0]);
        var valTo = Integer.parseInt(ranges[1]);

        int[] res = new int[2];
        res[0] = valFrom;
        res[1] = valTo;

        return res;
    }
}
