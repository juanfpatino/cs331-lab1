import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;

//Juan Francisco Patino

public class lab1 {

    public static void main(String[] args) throws IOException {//terrain.png, mpp.txt, redOut.png

        BufferedImage terrain = ImageIO.read(new File(args[0]));
        File elevationFile = new File(args[1]);
        File pathFile = new File(args[2]);
        String outFileName = args[3];

        Double[][] elevation = new Double[395][500];
        Scanner s = new Scanner(elevationFile);

        ConfigElevation(elevation, s);

        ArrayList<Point> path = new ArrayList<>();
        s = new Scanner(pathFile);

        ConfigPath(s, path, terrain, elevation);

        //A*

        Point current = aStar(terrain, elevation, path);

        //out png
        File outputFile = new File(outFileName);

        Point p = current;
        ArrayList<Point> finalPath = new ArrayList<>();

        finalPath.add(p);
        while (p.getParent() != null) {

            p = p.getParent();
            finalPath.add(p);

        }

        for (int i = finalPath.size() - 1; i >= 0; i--) {

            Point j = finalPath.get(i);

            terrain.setRGB(j.x, j.y, 255);

            for (Point n : j.getNeighbors()
            ) {

                if (n == null) continue;

                terrain.setRGB(n.x, n.y, 255);

            }

        }

        System.out.println("Total Distance: " + finalPath.get(0).getG() + " meters");
        ImageIO.write(terrain, "png", outputFile);

    }

    private static Point aStar(BufferedImage terrain, Double[][] elevation, ArrayList<Point> path) {
        Point current = path.get(0);//first stop
        path.remove(0);

        Queue<Point> frontier = new PriorityQueue<>(5, new PointComparator()); //frontier
        LinkedList<Point> explored = new LinkedList<>(); //explored set

        explored.add(current);

        Point next = path.get(0);//second stop

        while (!path.isEmpty()) {

            Point[] children = current.getNeighbors();

            for (Point c : children
            ) {


                if (c == null || inExplored(c, explored)) continue;


                c.setColor(terrain.getRGB(c.x, c.y)); //terrain level is set here
                if (c.getTerrainLevel() == 999.9)
                    continue;

                assert next != null;
                c.setElevation(elevation[c.x][c.y]); //get the elevation of this pixel from the elevation array[]

                setHeuristic(current, next, c);//set f(n) for c

                //add child to frontier
                if (frontier.contains(c)) {

                    ArrayList<Point> arr = new ArrayList<>(frontier);

                    int otherCidx = arr.indexOf(c);

                    if (c.getF() > arr.get(otherCidx).getF()) {

                        continue;

                    }//else
                    frontier.remove(c);

                }
                frontier.add(c);

            }

            current = frontier.poll(); //take off frontier
            assert current != null;     //this is always true anyway. just annoying to see yellow warnings.
            if (current.equals(next)) {

                path.remove(0);
                if (path.isEmpty()) break;
                next = path.get(0);

                //empty frontier queue and explored set. We are doing a completely new A* search to the next point in the path
                frontier = new PriorityQueue<>(5, new PointComparator());
                explored = new LinkedList<>();
                frontier.add(current);
                explored.add(current);
                continue;

            }

            if (!inExplored(current, explored))
                explored.add(current); //add it to explored set


        }
        return current;
    }

    private static void setHeuristic(Point current, Point next, Point c) {
        double distance = getDistance(c, next);
        double h = distance * current.getTerrainLevel();
        c.setF(h + c.getG()); //f(n) = g(n) + h(n)
    }

    private static double getDistance(Point current, Point next) {

        double nextX = next.x * 10.29;
        double nextY = next.y * 7.55;
        double nextZ = next.elevation;

        double currentX = current.x * 10.29;
        double currentY = current.y * 7.55;
        double currentZ = current.elevation;

        double xDiff = nextX - currentX;
        double yDiff = nextY - currentY;
        double zDiff = nextZ - currentZ;

        double X = Math.pow(xDiff, 2);
        double Y = Math.pow(yDiff, 2);
        double Z = Math.pow(zDiff, 2);

        Double dist = Math.sqrt(X + Y + Z);
        return dist;
    }

    public static boolean inExplored(Point c, LinkedList<Point> explored) {

        for (Point e : explored
        ) {

            if (e.x == c.x && e.y == c.y) return true; //normal equals() function isn't working

        }

        return false;
    }

    private static void ConfigPath(Scanner s, ArrayList<Point> path, BufferedImage terrain, Double[][] elevation) {
        String ss = s.nextLine();

        while (true) {

            String xString = ss.split("\\s+")[0];
            String yString = ss.split("\\s+")[1];
            int x = Integer.parseInt(xString);
            int y = Integer.parseInt(yString);

            Point p = new Point(x, y);
            path.add(p);
            int c = terrain.getRGB(x, y);
            p.setColor(c);
            p.setElevation(elevation[x][y]);

            try {

                ss = s.nextLine();

            } catch (NoSuchElementException n) {

                break;

            }

        }
    }

    private static void ConfigElevation(Double[][] elevation, Scanner s) {
        for (int i = 0; i < 500; i++) {

            String[] line = s.nextLine().trim().split("\\s+");
            for (int j = 0; j < 395; j++) {

                String valAsString = line[j].substring(0, 8);

                //last two is how many decimal places to move to the right

                String exponent = line[j].substring(11, 13);

                int exp = Integer.parseInt(exponent);

                double val = Math.pow(10, exp) * Double.parseDouble(valAsString);

                elevation[j][i] = val;

            }

        }
    }

}
