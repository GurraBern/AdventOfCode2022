import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class Day9 {
    private static List<List<String>> grid = new ArrayList<>();
    private static Set<Point> markedTiles = new HashSet<>();
    private static int tailLength = 9;
    private static Point start = new Point(500, 500);

    private static TailSegment head = new TailSegment("H", start);

    private static List<TailSegment> tails = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("src/main/inputDay9.txt"));
        for (int i = 0; i < 1000; i++){
            grid.add(new ArrayList<>());
            for (int j = 0; j < 1000; j++){
                grid.get(i).add(".");
            }
        }

        tails.add(head);
        for (int i = 0; i < tailLength; i++){
            TailSegment tailSegment = new TailSegment(String.valueOf(i+1), tails.get(i));
            tails.add(tailSegment);
        }

        while (myReader.hasNext()){
            var data = myReader.nextLine();
            ropePhysics(data);
        }

        var result = markedTiles.size();
        System.out.println(result);
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
        moveDirection(direction); //Move head

        for (int i = 1; i < tails.size(); i++){
            var tail = tails.get(i);
            int dx = (int) (tail.getParentPosition().getX()-tail.getX());
            int dy = (int) (tail.getParentPosition().getY()-tail.getY());

            if(dx > 1){
                tail.move(tail.getX()+1,tail.getY()+normalize(dy));
            } else if(dx < -1){
                tail.move(tail.getX()-1,tail.getY()+normalize(dy));
            } else if(dy > 1){
                tail.move(tail.getX()+normalize(dx),tail.getY()+1);
            } else if(dy < -1){
                tail.move(tail.getX()+normalize(dx),tail.getY()-1);
            }
        }

        markTiles();
        //printTiles();
        //printGrid();
    }

    private static int normalize(int number){
        if(number != 0)
            return number/Math.abs(number);

        return 0;
    }

    private static void moveDirection(String direction){
        switch (direction) {
            case "U" -> head.move(head.getX(), head.getY() - 1);
            case "D" -> head.move(head.getX(), head.getY() + 1);
            case "R" -> head.move(head.getX() + 1, head.getY());
            case "L" -> head.move(head.getX() - 1, head.getY());
        }
    }

    private static void markTiles(){
        markedTiles.add(new Point(tails.get(tails.size()-1).getPosition()));
    }

    private static void printGrid(){
        System.out.println("");
        System.out.println("");

        for (var c : grid){
            System.out.println(c);
        }
    }

    private static void printTiles(){
        for (var c : grid){
            for (int j = 0; j < grid.get(0).size(); j++){
                c.set(j, ".");
            }
        }

        for (var tile : markedTiles){
            grid.get(tile.y).set(tile.x, "#");
        }

        for(int i = tails.size()-1; i >= 0; i--){
            var tail = tails.get(i);
            grid.get(tail.getY()).set(tail.getX(), tail.getName());
        }
    }

    static class TailSegment {


        private String name;
        private Point position = new Point();
        private TailSegment parent;

        public TailSegment(String name, TailSegment parent){
            this.name = name;
            this.parent = parent;
            this.position = new Point(parent.position);
        }

        public TailSegment(String name, Point position){
            this.name = name;
            this.position = position;
        }

        public String getName(){
            return name;
        }

        public void move(int x, int y){
            position.setLocation(x,y);
        }

        public Point getPosition() {
            return position;
        }

        public Point getParentPosition() {
            return new Point(parent.position);
        }

        public TailSegment getParent() {
            return parent;
        }

        public int getX(){
            return position.x;
        }

        public int getY(){
            return position.y;
        }
    }


}

