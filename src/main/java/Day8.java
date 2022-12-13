import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("src/main/inputDay8.txt"));

        List<List<Integer>> intList = new ArrayList<>();
        while (myReader.hasNext()){
            var data = myReader.nextLine();
            intList.add(addRow(data));
        }


        //var res1 = checkVisibilityOutside(intList);

        var res2 = checkVisibilityInside(intList);

        var t = 3;
    }



    private static int checkVisibilityOutside(List<List<Integer>> intList){
        Set<Point> treeCoordinates = new HashSet<>();

        int leftPointer = 0;
        int rightPointer = intList.size()-1;
        for (int i = 0; i < intList.size(); i++){
            //calcRow(intList, i, treeCoordinates, leftPointer, rightPointer);
            //calcCol(intList, i, treeCoordinates, leftPointer, rightPointer);
        }

        return treeCoordinates.size();
    }

    private static int checkVisibilityInside(List<List<Integer>> intList){
        //Each tree should have a position and the number of trees that are visible
        //Multiply amount of trees visible in all directions

        int highestScore = 0;

        for (int i = 1; i < intList.size(); i++){
            for(int j = 1; j < intList.get(0).size(); j++){
                Set<Point> treeCoordinates = new HashSet<>();
                int leftPointer = j+1; // East
                int rightPointer = j-1; //West
                var r = calcRow(intList, j, treeCoordinates, leftPointer, rightPointer, new Point(j,i));
                var c = calcCol(intList, j, treeCoordinates, leftPointer, rightPointer, new Point(j,i));

                var res = r.get(0)*r.get(1)*c.get(0)*c.get(1);
                if(res > highestScore){
                    highestScore = res;
                }

            }
        }


        return highestScore;
    }


    private static List<Integer> calcRow(List<List<Integer>> intList, int row, Set<Point> treeCoordinates, int leftPointer, int rightPointer, Point tree){
        int maxValueLeft = -1;
        int maxValueRight = -1;

        List<Integer> dirCount = new ArrayList<>();
        dirCount.add(0);
        dirCount.add(0);


        while(true){
            if(leftPointer < intList.size()){
                int treeValue = intList.get(row).get(leftPointer);

                if(treeValue == intList.get(tree.y).get(tree.x)){
                    dirCount.set(0,dirCount.get(0)+1);
                    leftPointer = intList.size();
                } else{
                    if(treeValue > maxValueLeft){
                        dirCount.set(0,dirCount.get(0)+1);
                        maxValueLeft = treeValue;
                    }
                }


                leftPointer++;
            } else if(rightPointer >= 0){
                int treeValue = intList.get(row).get(rightPointer);

                if(treeValue == intList.get(tree.y).get(tree.x)){
                    dirCount.set(1,dirCount.get(1)+1);
                    rightPointer = intList.size();
                } else {
                    if (treeValue > maxValueRight) {
                        dirCount.set(1,dirCount.get(1)+1);
                        maxValueRight = treeValue;
                    }
                }

                rightPointer--;
            } else{
                break;
            }
        }
        return dirCount;
    }

    private static List<Integer> calcCol(List<List<Integer>> intList, int row, Set<Point> treeCoordinates, int topPointer, int bottomPointer, Point tree) {
        int maxValueTop = -1;
        int maxValueBottom = -1;


        List<Integer> dirCount = new ArrayList<>();
        dirCount.add(0);
        dirCount.add(0);

        //int topPointer = 0;
        //int bottomPointer = intList.size() - 1;
        while(true){
            if(topPointer < intList.size()){
                int treeValue = intList.get(topPointer).get(row);
                if(treeValue == intList.get(tree.y).get(tree.x)){
                    dirCount.set(0,dirCount.get(0)+1);
                } else{
                    if(treeValue > maxValueTop){
                        dirCount.set(0,dirCount.get(0)+1);
                        maxValueTop = treeValue;
                    }
                }

                topPointer++;
            } else if(bottomPointer >= 0){
                int treeValue = intList.get(bottomPointer).get(row);

                if(treeValue == intList.get(tree.y).get(tree.x)){
                    dirCount.set(1,dirCount.get(1)+1);
                } else {
                    if (treeValue > maxValueBottom) {
                        dirCount.set(1,dirCount.get(1)+1);
                        maxValueBottom = treeValue;
                    }
                }


                bottomPointer--;
            } else{
                break;
            }
        }

        return dirCount;
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