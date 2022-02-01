import java.util.Objects;

public class Point implements Comparable<Point> {

    public int x;
    public int y;
    public double elevation;
    private Color color;
    private double terrainLevel;

    private Point parent;
    private double f;
    private double g = 0.0;

    public Point(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

    public Point getParent() {
        return parent;
    }

    public Point[] getNeighbors() {//returns coordinates* of neighbors. algorithm will ignore negative

        Point[] p = new Point[8];

        if (x == 0 || y == 0) {//southwest

            p[0] = null;    //these are to prevent out of bounds

        } else {

            Point p0 = new Point(x - 1, y - 1);
            p0.setParent(this);
            p[0] = p0;

        }
        if (y == 0) {

            p[1] = null;

        } else {

            Point p1 = new Point(x, y - 1);//south
            p1.setParent(this);
            p[1] = p1;

        }
        if (x >= 394 || y == 0) {

            p[2] = null;

        } else {

            Point p2 = new Point(x + 1, y - 1);//southeast
            p2.setParent(this);
            p[2] = p2;

        }
        if (x == 0) {

            p[3] = null;

        } else {

            Point p3 = new Point(x - 1, y); //west
            p3.setParent(this);
            p[3] = p3;

        }
        if (x >= 394) {

            p[4] = null;

        } else {

            Point p4 = new Point(x + 1, y);//east
            p4.setParent(this);
            p[4] = p4;

        }
        if (x == 0 || y >= 499) {

            p[5] = null;

        } else {

            Point p5 = new Point(x - 1, y + 1);//northwest
            p5.setParent(this);
            p[5] = p5;

        }
        if (y >= 499) {

            p[6] = null;

        } else {

            Point p6 = new Point(x, y + 1);//north
            p6.setParent(this);
            p[6] = p6;
        }
        if (x >= 394 || y >= 499) {

            p[7] = null;

        } else {

            Point p7 = new Point(x + 1, y + 1);//northeast
            p7.setParent(this);
            p[7] = p7;

        }

        return p;

    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public double getG() {
        return g;
    }

    public void setElevation(double d) {

        this.elevation = d;

    }

    public void setColor(int d) {

        int blue = d & 0xff;
        int green = (d & 0xff00) >> 8;
        int red = (d & 0xff0000) >> 16;

        this.color = new Color(red, green, blue);
        this.terrainLevel = setTerrainLevel();

    }

    public double setTerrainLevel() {

        if (this.color.equals(new Color(248, 148, 18))) {

            return 0.1;// Open land

        } else if (this.color.equals(new Color(255, 192, 0))) {

            return 3.0;//Rough meadow

        } else if (this.color.equals(new Color(255, 255, 255))) {

            return 1.5;//Easy movement forest

        } else if (this.color.equals(new Color(2, 208, 60))) {

            return 3.5;//Slow run forest

        } else if (this.color.equals(new Color(2, 136, 40))) {

            return 4.4;//Walk forest

        } else if (this.color.equals(new Color(5, 73, 24))) {

            return 999.9;//Impassable vegetation

        } else if (this.color.equals(new Color(0, 0, 255))) {

            return 9.0;//Lake/Swamp/Marsh

        } else if (this.color.equals(new Color(71, 51, 3))) {

            return 0.1;//Paved road

        } else if (this.color.equals(new Color(0, 0, 0))) {

            return 0.1;//Footpath

        } else {

            return 999.9;//out of bounds

        }

    }

    public double getTerrainLevel() {
        return terrainLevel;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return this.x == point.x && this.y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public int compareTo(Point o) {
        if (this.f < o.f) {

            return 1;

        } else if (this.f > o.f) {

            return -1;

        }
        return 0;
    }
}
