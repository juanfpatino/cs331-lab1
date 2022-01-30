import java.util.ArrayList;

public class Point {

    public int x;
    public int y;
    public double elevation;
    public Color c;

    public Point(int x, int y){

        this.x = x;
        this.y = y;

    }

    public Point[] getNeighbors(){//returns coordinates* of neighbors. algorithm will ignore negative

        Point[] p = new Point[8];

        p[0] = new Point(x-1, y-1);
        p[1] = new Point(x, y-1);
        p[2] = new Point(x+1, y);
        p[3] = new Point(x-1, y);
        p[4] = new Point(x+1, y);
        p[5] = new Point(x-1, y+1);
        p[6] = new Point(x, y+1);
        p[7] = new Point(x+1, y+1);

        return p;

    }

    public void setElevation(double d){

        this.elevation = d;

    }

    public void setColor(int d){

        int blue = d & 0xff;
        int green = (d & 0xff00) >> 8;
        int red = (d & 0xff0000) >> 16;

        this.c = new Color(red,green,blue);

    }

}
