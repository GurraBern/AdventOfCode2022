import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("src/main/inputDay8.txt"));

        List<List<Integer>> intList = new ArrayList<>();
        while (myReader.hasNext()){
            var data = myReader.nextLine();
            intList.add(addRow(data));
        }


        var res1 = checkVisibilityOutside(intList);

    }



    private static int checkVisibilityOutside(List<List<Integer>> intList){
        Set<Point> treeCoordinates = new HashSet<>();

        for (int i = 0; i < intList.size(); i++){
            calcRow(intList, i, treeCoordinates);
            calcCol(intList, i, treeCoordinates);
        }

        return treeCoordinates.size();
    }

    private static void calcRow(List<List<Integer>> intList, int row, Set<Point> treeCoordinates){
        int leftPointer = 0;
        int rightPointer = intList.size()-1;
        int maxValueLeft = -1;
        int maxValueRight = -1;

        while(true){
            if(leftPointer < intList.size()){
                int treeValue = intList.get(row).get(leftPointer);
                if(treeValue > maxValueLeft){
                    treeCoordinates.add(new Point(leftPointer, row));
                    maxValueLeft = treeValue;
                }
                leftPointer++;
            } else if(rightPointer > 0){
                int treeValue = intList.get(row).get(rightPointer);
                if (treeValue > maxValueRight) {
                    treeCoordinates.add(new Point(rightPointer, row));
                    maxValueRight = treeValue;
                }
                rightPointer--;
            } else{
                break;
            }
        }
    }

    private static void calcCol(List<List<Integer>> intList, int row, Set<Point> treeCoordinates) {
        int maxValueTop = -1;
        int maxValueBottom = -1;
        int topPointer = 0;
        int bottomPointer = intList.size() - 1;
        while(true){
            if(topPointer < intList.size()){
                int treeValue = intList.get(topPointer).get(row);
                if(treeValue > maxValueTop){
                    treeCoordinates.add(new Point(row, topPointer));
                    maxValueTop = treeValue;
                }
                topPointer++;
            } else if(bottomPointer > 0){
                int treeValue = intList.get(bottomPointer).get(row);
                if (treeValue > maxValueBottom) {
                    treeCoordinates.add(new Point(row, bottomPointer));
                    maxValueBottom = treeValue;
                }
                bottomPointer--;
            } else{
                break;
            }
        }
    }

    private static List<Integer> addRow(String data){
        List<Integer> row = new ArrayList<>();

        for(char c : data.toCharArray()){
            var treeHeight = Character.getNumericValue(c);
            row.add(treeHeight);
        }

        return row;
    }
}