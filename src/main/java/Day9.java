import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class Day9 {


    private static Set<Point> markedTiles = new HashSet<>();
    private static Point start = new Point(500, 500);

    private static Point head = new Point(500,500);
    private static Point tail = new Point(500,500);
    private static List<List<Character>> grid = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("src/main/inputDay9.txt"));
        for (int i = 0; i < 1000; i++){
            grid.add(new ArrayList<>());
            for (int j = 0; j < 1000; j++){
                grid.get(i).add('.');
            }
        }

        markTile(tail);
        //markTiles();

        while (myReader.hasNext()){
            var data = myReader.nextLine();
            ropePhysics(data);


            var b = 5;
        }

        var result = markedTiles.size();

    }

    private static void ropePhysics(String moveInput){
        var move = moveInput.split(" ");
        var dir = move[0];
        var step = Integer.parseInt(move[1]);

        for(int i = 0; i < step; i++){
            move(dir);
        }

    }

    private static void move(String direction){
        moveDirection(direction);//Move head

       var dx = head.x-tail.x;
       var dy = head.y-tail.y;


       if(dx > 1){
           //grid.get(tail.y).set(tail.x, '#');
           tail.move(tail.x+1,tail.y+dy);
           //grid.get(tail.y).set(tail.x, 'T');
       } else if(dx < -1){
           //grid.get(tail.y).set(tail.x, '#');
           tail.move(tail.x-1,tail.y+dy);
           //grid.get(tail.y).set(tail.x, 'T');
       }

       if(dy > 1){
           //grid.get(tail.y).set(tail.x, '#');
           tail.move(tail.x+dx,tail.y+1);
           //grid.get(tail.y).set(tail.x, 'T');
       } else if(dy < -1){
           //grid.get(tail.y).set(tail.x, '#');

           tail.move(tail.x+dx,tail.y-1);
           //grid.get(tail.y).set(tail.x, 'T');
       }
        markTile(tail);
        //markTiles();
        //printGrid();
    }

    private static void printGrid(){
        System.out.println("");
        System.out.println("");

        for (var c : grid){
            System.out.println(c);
        }
    }

    //TODO change to keep # maybe?
    private static void moveDirection(String direction){
        if(direction.equals("U")){
            //grid.get(head.y).set(head.x, '.');
            head.move(head.x,head.y-1);
            //grid.get(head.y).set(head.x, 'H');
        } else if (direction.equals("D")){
            //grid.get(head.y).set(head.x, '.');
            head.move(head.x,head.y+1);
            //grid.get(head.y).set(head.x, 'H');
        } else if (direction.equals("R")){
            //grid.get(head.y).set(head.x, '.');
            head.move(head.x+1,head.y);
            //grid.get(head.y).set(head.x, 'H');
        } else if (direction.equals("L")){
            //grid.get(head.y).set(head.x, '.');
            head.move(head.x-1,head.y);
            //grid.get(head.y).set(head.x, 'H');
        }
    }


    private static void markTile(Point tile){
        Point p = new Point(tile);
        if(!grid.get(tile.y).get(tile.x).equals('s'))
            markedTiles.add(p);
    }

    /*private static void markTiles(){
        grid.get(start.y).set(start.x, 's');

        for (var tile : markedTiles){
            grid.get(tile.y).set(tile.x, '#');
        }

    }*/

}