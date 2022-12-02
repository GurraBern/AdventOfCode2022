import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        var startTime = System.nanoTime();

        File myObj = new File("src/main/inputDay1.txt");
        List<Integer> sortedList = countCal(myObj);
        int highest = getTotal(sortedList, 1);
        int topThree = getTotal(sortedList, 3);

        var endTime = System.nanoTime();
        var totalRunningTime = (endTime-startTime)/Math.pow(10,9);
        System.out.println(totalRunningTime);
    }
//0.0661089
    private static List<Integer> countCal(File myObj) throws FileNotFoundException {
        Scanner myReader = new Scanner(myObj);
        List<Integer> elves = new ArrayList<>();
        int val = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if(data.isEmpty()){
                elves.add(val);
                val = 0;
            } else{
                val += Integer.parseInt(data);
            }
        }
        Collections.sort(elves);
        return elves;
    }

    private static int getTotal(List<Integer> sortedList, int elemCount){
        int total = 0;
        for (int i = 1; i<=elemCount; i++){
            total += sortedList.get(sortedList.size()-i);
        }
        return total;
    }
}
