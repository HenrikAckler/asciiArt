import java.awt.Font;

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

      CharMapper myMapper = new CharMapper(new Font("Arial", Font.PLAIN, 10), 13);
      myMapper.imgGenerator('█');
   }
}
