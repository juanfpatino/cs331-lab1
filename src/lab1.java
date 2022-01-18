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
        BufferedImage out = ImageIO.read(new File(args[3]));

        

    }

}
