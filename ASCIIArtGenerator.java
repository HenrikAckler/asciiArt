/**Driver class for generating ASCII art.
 *
 * @author Henrik Ackler
 */
public class ASCIIArtGenerator
{
   public static void main(String[] args)
   {
      ImageReader myReader = new ImageReader("surprisedEmoji.png");
      myReader.makeOutput();
      myReader.readImage();
   }
}
