import java.awt.*;

public class TailSegment {


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
