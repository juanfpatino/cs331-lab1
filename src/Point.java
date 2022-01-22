public class Point {

    public int x;
    public int y;
    public double elevation;
    public Color c;

    public Point(int x, int y){

        this.x = x;
        this.y = y;

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
