import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.*;
import java.math.*;
import java.io.*;

//Juan Francisco Patino

//h(n) will be a number averaged by the difference in elevation from g(n) and a number derived from the difficulty of the terrain

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

        Point[] G = new Point[197500];
        ConfigGraph(terrain, elevation, G);

        //A*

        Point current = path.get(0);
        path.remove(0);

        Queue<Point> frontier = new PriorityQueue<>(); //frontier
        ArrayList<Point> explored = new ArrayList<>(); //explored set

        explored.add(current);

        Point next = path.get(0);

        while (!path.isEmpty()) {

            assert current != null;
            if (current.equals(next)) {

                path.remove(0);
                next = path.get(0);

            }

            Point[] children = current.getNeighbors();


            for (Point c : children
            ) {


                if (c == null || inExplored(c, explored)) continue;


                c.setColor(terrain.getRGB(c.x, c.y)); //terrain level is set here
                if (c.getTerrainLevel() == 999.9)
                    continue;


                assert next != null;
                c.setElevation(elevation[c.x][c.y]);
                double distance = getDistance(current, next);

                double h = distance * current.getTerrainLevel(); //f(n) will just be this heuristic?

                c.setF(h);
                frontier.add(c);

            }

            current = frontier.poll(); //take off frontier
            explored.add(current); //add it to explored set
            assert current != null;
            terrain.setRGB(current.x, current.y, 0xFF0000);
            System.out.println(current);

        }

        //out png
        File outputFile = new File(outFileName);


        ImageIO.write(terrain, "png", outputFile);


    }

    private static double getDistance(Point current, Point next) {
        int nextX = next.x;
        int nextY = next.y;
        double nextZ = next.elevation;

        int currentX = current.x;
        int currentY = current.y;
        double currentZ = current.elevation;

        Double dist = Math.sqrt(Math.pow(nextX - currentX, 2) + Math.pow(nextY - currentY, 2) + Math.pow(nextZ - currentZ, 2));
        return dist;
    }

    public static boolean inExplored(Point c, ArrayList<Point> explored) {

        for (Point e : explored
        ) {

            if (c.equals(e)) return true;

        }

        return false;
    }

    private static void ConfigGraph(BufferedImage terrain, Double[][] elevation, Point[] G) {
        int count = 0;

        for (int i = 0; i < terrain.getWidth(); i++) {

            for (int j = 0; j < terrain.getHeight(); j++) {

                Point p = new Point(i, j);
                p.setElevation(elevation[i][j]);
                int c = terrain.getRGB(i, j);
                p.setColor(c);

                G[count] = p;
                count++;

            }

        }
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

                //remove the e+02 at the end then multiply by 100

                double val = 100.0 * Double.parseDouble(valAsString);//primative?

                elevation[j][i] = val;

            }

        }
    }

}
