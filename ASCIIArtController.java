import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Driver class for generating ASCII art.
 *
 * @author Henrik Ackler
 */
public class ASCIIArtController {
   public static void main(String[] args) {
      if (args.length != 2) {
         System.out.println("\nImproper usage. Format: $ java ASCIIArtController [recalculate] [image]\n");
         System.out.println("\tRecalculate: \t0 to use existing ASCII calculations, 1 to recalculate");
         System.out.println("\tImage: \t\tName of image file located within ./images");
         System.out.println("\tExample: \t$ java ASCIIArtController 0 cat.png");
      } else {
         try {
            CharMapper myMapper = new CharMapper(new Font("Arial", Font.PLAIN, 10));
            if(Integer.parseInt(args[0]) == 1)
            {
               myMapper.makeImgSet();
            }
            myMapper.makeValueMaps();

            ImageReader myReader = new ImageReader("./images/" + args[1]);
            myReader.readImage();

            asciiGenerator myGenerator = new asciiGenerator(myReader.getLumArray(), myMapper);
            myGenerator.getAsciiArt();

            BufferedWriter artOut = new BufferedWriter(new FileWriter("output.txt"));
            artOut.write(myGenerator.getAsciiArtString());
            artOut.close();
            System.out.println("Generation complete.");
         } catch (IOException e)
         {
            System.out.println(e.toString() + "\nPlease check input file, or recalculate ASCII");
         } catch (NumberFormatException e)
         {
            System.out.println(e.toString() + "\nPlease use a [recalculate] value of 0 or 1");
         }
      }

   }
}
