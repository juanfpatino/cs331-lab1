import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    }

    private static void ConfigElevation(Double[][] elevation, Scanner s) {
        for(int i = 0; i < 500; i++){

            String[] line = s.nextLine().split(" ");
            for(int j = 0; j < 395; j++){

                String valAsString = line[j].substring(0, 8);

                //remove the e+02 at the end then multiply by 100

                double val = Double.parseDouble(valAsString);//primative?

                elevation[i][j] = val;

            }

        }
    }

}
