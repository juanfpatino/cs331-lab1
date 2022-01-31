public class Point implements Comparable<Point> {

    public int x;
    public int y;
    public double elevation;
    private Color color;
    private double terrainLevel;

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

        this.color = new Color(red,green,blue);
        this.terrainLevel = setTerrainLevel();

    }

    public double setTerrainLevel(){

        if(this.color.equals(new Color(248, 148, 18))){

            return 0.0;// Open land

        }else if(this.color.equals(new Color(255, 192, 0))){

            return 3.0;//Rough meadow

        }else if(this.color.equals(new Color(255, 255, 255))){

            return 1.5;//Easy movement forest

        }else if(this.color.equals(new Color(2, 208, 60))){

            return 3.5;//Slow run forest

        }else if(this.color.equals(new Color(2, 136, 40))){

            return 4.4;//Walk forest

        }else if(this.color.equals(new Color(5, 73, 24))){

            return 10.0;//Impassable vegetation

        }else if(this.color.equals(new Color(0, 0, 255))){

            return 9.0;//Lake/Swamp/Marsh

        }else if(this.color.equals(new Color(71, 51, 3))){

            return 0.0;//Paved road

        }else if(this.color.equals(new Color(0, 0, 0))){

            return 0.0;//Footpath

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
    public int compareTo(Point o) {
        return 0;
    }


}
