import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.math.*;
import java.io.*;

public class lab1 {

    public static void main(String[] args)throws IOException {//terrain.png, mpp.txt, redOut.png

        BufferedImage terrain = ImageIO.read(new File(args[0]));
        File elevationFile = new File(args[1]);
        File pathFile = new File(args[2]);
        String outFileName = args[3];

        Double[][] elevation = new Double[395][500];
        Scanner s = new Scanner(elevationFile);

        ConfigElevation(elevation, s);

        ArrayList<Point> path = new ArrayList<>();
        s = new Scanner(pathFile);

        ConfigPath(s, path);



    }

    private static void ConfigPath(Scanner s, ArrayList<Point> path) {
        String ss = s.nextLine();

        while(true){

            String xString = ss.split("\\s+")[0];
            String yString = ss.split("\\s+")[1];
            Point p = new Point(Integer.parseInt(xString), Integer.parseInt(yString));
            path.add(p);

            try{

                ss = s.nextLine();

            }catch (NoSuchElementException n){

                break;

            }

        }
    }

    private static void ConfigElevation(Double[][] elevation, Scanner s) {
        for(int i = 0; i < 500; i++){

            String[] line = s.nextLine().trim().split("\\s+");
            for(int j = 0; j < 395; j++){

                String valAsString = line[j].substring(0, 8);

                //remove the e+02 at the end then multiply by 100

                double val = 100.0*Double.parseDouble(valAsString);//primative?

                elevation[j][i] = val;

            }

        }
    }

}
