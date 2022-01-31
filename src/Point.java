import java.util.Objects;

public class Point implements Comparable<Point> {

    public int x;
    public int y;
    public double elevation;
    private Color color;
    private double terrainLevel;

    private double f;
    private int g = 0;

    public Point(int x, int y){

        this.x = x;
        this.y = y;

    }

    public Point[] getNeighbors(){//returns coordinates* of neighbors. algorithm will ignore negative

        Point[] p = new Point[8];

        if(x == 0 || y == 0){

            p[0] = null;    //these are to prevent out of bounds

        }
        else{

            p[0] = new Point(x-1, y-1);

        }
        if(y == 0){

            p[1] = null;

        }
        else{

            p[1] = new Point(x, y-1);

        }
        if(x >= 394 || y == 0){

            p[2] = null;

        }
        else{

            p[2] = new Point(x+1, y-1);

        }
        if(x == 0){

            p[3] = null;

        }
        else{

            p[3] = new Point(x-1, y);

        }
        if(x >= 394){

            p[4] = null;

        }
        else{

            p[4] = new Point(x+1, y);

        }
        if(x == 0 || y >= 499){

            p[5] = null;

        }
        else{

            p[5] = new Point(x-1, y+1);

        }
        if(y >= 499){

            p[6] = null;

        }
        else{

            p[6] = new Point(x, y+1);

        }
        if(x >= 394 || y >= 499){

            p[7] = null;

        }
        else{

            p[7] = new Point(x+1, y+1);

        }

        return p;

    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getG() {
        return g;
    }

    public void setElevation(double d){

        this.elevation = d;

    }

    public void setColor(int d){

        int blue = d & 0xff;
        int green = (d & 0xff00) >> 8;
        int red = (d & 0xff0000) >> 16;

        this.color = new Color(red,green,blue);
        this.terrainLevel = setTerrainLevel();

    }

    public double setTerrainLevel(){

        if(this.color.equals(new Color(248, 148, 18))){

            return 0.1;// Open land

        }else if(this.color.equals(new Color(255, 192, 0))){

            return 3.0;//Rough meadow

        }else if(this.color.equals(new Color(255, 255, 255))){

            return 1.5;//Easy movement forest

        }else if(this.color.equals(new Color(2, 208, 60))){

            return 3.5;//Slow run forest

        }else if(this.color.equals(new Color(2, 136, 40))){

            return 4.4;//Walk forest

        }else if(this.color.equals(new Color(5, 73, 24))){

            return 999.9;//Impassable vegetation

        }else if(this.color.equals(new Color(0, 0, 255))){

            return 9.0;//Lake/Swamp/Marsh

        }else if(this.color.equals(new Color(71, 51, 3))){

            return 0.1;//Paved road

        }else if(this.color.equals(new Color(0, 0, 0))){

            return 0.1;//Footpath

        }else {

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public int compareTo(Point o) {
        if(this.f < o.f){

            return 1;

        }else if(this.f > o.f){

            return -1;

        }
        return 0;
    }
}
