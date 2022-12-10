import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("src/main/inputDay8.txt"));

        List<List<Integer>> intList = new ArrayList<>();
        while (myReader.hasNext()){
            var data = myReader.nextLine();
            intList.add(addRow(data));
        }


        var t = checkVisibilityOutside(intList);



        var b = 5;
    }



    private static int checkVisibilityOutside(List<List<Integer>> intList){
        Set<Point> treeCoordinates = new HashSet<>();

        for (int i = 0; i < intList.size(); i++){
            calcRow(intList, i, treeCoordinates);
            calcCol(intList, i, treeCoordinates);
        }

        //353 too low
        return treeCoordinates.size();
    }

    private static void calcRow(List<List<Integer>> intList, int row, Set<Point> treeCoordinates){
        boolean stopLeft = false;
        boolean stopRight = false;
        int leftPointer = 0;
        int rightPointer = intList.size()-1;
        while(true){
            if(!stopLeft){
                if (leftPointer == 0 || leftPointer+1 == intList.size()){
                    treeCoordinates.add(new Point(row,leftPointer));
                } else if(intList.get(row).get(leftPointer) > intList.get(row).get(leftPointer+1)){
                    treeCoordinates.add(new Point(row,leftPointer));
                } else {
                    stopLeft = true;
                }
            }

            if(!stopRight){
                if (rightPointer == 0 || rightPointer+1 == intList.size()){
                    treeCoordinates.add(new Point(row,rightPointer));
                } else if(intList.get(row).get(rightPointer) > intList.get(row).get(rightPointer+1)){
                    treeCoordinates.add(new Point(row,rightPointer));
                } else {
                    stopRight = true;
                }
            }


            if (stopLeft && stopRight){
                break;
            }


            leftPointer++;
            rightPointer--;
        }
    }

    private static void calcCol(List<List<Integer>> intList, int row, Set<Point> treeCoordinates) {
        boolean stopTop = false;
        boolean stopBottom = false;
        int topPointer = 0;
        int bottomPointer = intList.size() - 1;
        while (true) {
            if (!stopTop) {

                if (topPointer == 0) {
                    treeCoordinates.add(new Point(row, topPointer));
                }

                if (row + 1 < intList.size() && intList.get(topPointer).get(row) < intList.get(topPointer).get(row + 1)) {
                    treeCoordinates.add(new Point(row, topPointer+1));
                } else {
                    stopTop = true;
                }
            }

            if (!stopBottom) {

                if (bottomPointer == intList.size()-1) {
                    treeCoordinates.add(new Point(row, bottomPointer));
                }

                if (row > 0 && intList.get(bottomPointer).get(row) < intList.get(bottomPointer-1).get(row)) {
                    treeCoordinates.add(new Point(row, bottomPointer-1));
                } else {
                    stopBottom = true;
                }
            }


            if (stopTop && stopBottom) {
                break;
            }


            topPointer++;
            bottomPointer--;
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