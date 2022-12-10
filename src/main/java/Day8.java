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


        var t = checkVisibilityOutside(intList);



        var b = 5;
    }



    private static int checkVisibilityOutside(List<List<Integer>> intList){
        Set<Point> treeCoordinates = new HashSet<>();



        //Check columnwise
        for (int i = 0; i < intList.size(); i++){

            int previousCount = 0;
            int leftPointer = 0;
            int rightPoint = intList.size()-1;
            while(true){


                var test1 = intList.get(i).get(leftPointer);
                var test2 = intList.get(i).get(leftPointer+1);
                if (leftPointer == 0 || leftPointer+1 < intList.size() && intList.get(i).get(leftPointer) > intList.get(i).get(leftPointer+1)){
                    //May reduce


                    treeCoordinates.add(new Point(i,leftPointer));
                    treeCoordinates.add(new Point(i,leftPointer+1));
                }

                if(previousCount >= treeCoordinates.size()){
                    break;
                }

                previousCount = treeCoordinates.size();

                leftPointer++;
                rightPoint--;
            }
        }

        var b = 5;




        //Look Left --> Right
        /*for (int i = 0; i < intList.size(); i++){
            for (int j = 0; j < intList.get(i).size()-1; j++){
                if(intList.get(i).get(j) < intList.get(i).get(j+1)){
                    treeCoordinates.add(new Point(i,j));
                } else{
                    if(i==0){
                        treeCoordinates.add(new Point(i,j));
                    }

                    break;
                }
            }
        }*/


        return treeCoordinates.size();
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