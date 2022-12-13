import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class Day9 {


    private static Set<Point> markedTiles = new HashSet<>();
    private static Point start = new Point(0, 4);

    private static Point head = new Point(0, 4);
    private static Point tail = new Point(0, 4);
    private static List<List<Character>> grid = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("src/main/inputDay9.txt"));
        for (int i = 0; i < 5; i++){
            grid.add(new ArrayList<>());
            for (int j = 0; j < 6; j++){
                grid.get(i).add('.');
            }
        }

        markTile(tail);

        while (myReader.hasNext()){
            var data = myReader.nextLine();
            ropePhysics(data);

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
           tail.move(tail.x+1,tail.y+dy);
       } else if(dx < -1){
           tail.move(tail.x-1,tail.y+dy);
       }

       if(dy > 1){
           tail.move(tail.x+dx,tail.y+1);
       } else if(dy < -1){
           tail.move(tail.x+dx,tail.y-1);
       }

        markTile(tail);
        printGrid();
    }

    private static void printGrid(){
        System.out.println("");
        System.out.println("");

        markTiles();

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
        markedTiles.add(new Point(tile));
    }

    private static void markTiles(){
        grid.get(start.y).set(start.x, 's');

        for (var c : grid){
            for (int j = 0; j < grid.get(0).size(); j++){
                c.set(j, '.');
            }
        }

        for (var tile : markedTiles){
            grid.get(tile.y).set(tile.x, '#');
        }

        grid.get(head.y).set(head.x, 'H');
        grid.get(tail.y).set(tail.x, 'T');
    }

}