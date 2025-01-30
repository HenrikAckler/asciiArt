import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**Driver class for generating ASCII art.
 *
 * @author Henrik Ackler
 */
public class ASCIIArtController
{
   public static void main(String[] args)
   {
      CharMapper myMapper = new CharMapper(new Font("Arial", Font.PLAIN, 10), 13);
      //myMapper.makeImgSet();
      myMapper.makeValueMaps();
      
      long timerStart = System.currentTimeMillis();
      
      ImageReader myReader = new ImageReader("cat.png");
      //myReader.makeOutput("catLuminosity");
      myReader.readImage();

      

      asciiGenerator myGenerator = new asciiGenerator(myReader.getLumArray(), myMapper);
      myGenerator.getAsciiArt();//this line is horribly slow
      
      
      long timerLap1 = System.currentTimeMillis();
      //System.out.println(myGenerator.getAsciiArtString());

      

      try {
         BufferedWriter artOut = new BufferedWriter(new FileWriter("output.txt"));
         artOut.write(myGenerator.getAsciiArtString());
         artOut.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      long timerFinish = System.currentTimeMillis();

      System.out.println("The program took " + ( (timerLap1 - timerStart)) + "ms to generate the frame.");
      System.out.println("The program took " + ( (timerFinish - timerStart)) + "ms to generate and export the frame.");
   }
}
