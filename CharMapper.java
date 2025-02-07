import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**maps all ascii characters to their valueMap
 * 
 * @author Henrik Ackler
 */
public class CharMapper {
   private Font usedFont;
   private int[][] charLumIndex; //row is index, col is luminosity
   private int charMinVal;
   private int charMaxVal;

   /**Creates CharMapper object with font and resolution input.
    * 
    * @param fontToUse pick a font, 10pt reccomended
    */
   public CharMapper(Font fontToUse)
   {
      usedFont = fontToUse;
      charMinVal = 33;
      charMaxVal = 255;
      charLumIndex = new int[charMaxVal-charMinVal][2];
   }

   /**Creates a single img file in the tmp directory based on char input
    *
    * @param charForProcess input character to generate image of
    * @throws IOException 
    */
   private void imgGenerator(char charForProcess) throws IOException
   {
      int charVal = charForProcess - 0;
      File outputFile = new File("./tmp/" + charVal + ".png");
      FontMetrics myMetrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics().getFontMetrics(usedFont);
      BufferedImage outImg = new BufferedImage(myMetrics.stringWidth(String.valueOf(charForProcess)), myMetrics.getHeight(), BufferedImage.TYPE_INT_ARGB);

      Graphics2D graphicOut = outImg.createGraphics();
      graphicOut.setFont(usedFont);
      graphicOut.setColor(Color.BLACK);
      graphicOut.setBackground(Color.WHITE);
      
      graphicOut.drawString(String.valueOf(charForProcess), 0, myMetrics.getAscent()); 
      graphicOut.dispose();

      ImageIO.write(outImg, "png", outputFile);
   }

   /**Generates a full set of ascii images
    * @throws IOException 
    * 
    */
   public void makeImgSet() throws IOException
   {
      for (int i = charMinVal; i <= charMaxVal; i++)
      {
         char myChar = (char) i;
         imgGenerator(myChar);
      }
   }

   
   /**Reads and maps values of all ascii images into charLumIndex[][]
    * Also sorts chars from dim to bright
    * MUST NOT BE CALLED BEFORE makeImgSet()
    * TODO: implement check for img pregeneration
    */
   public void makeValueMaps() throws IOException
   {
      for (int i = 0; i < charLumIndex.length; i++)
      {
         ImageReader tmpReader = new ImageReader("./tmp/"+ (i + charMinVal) + ".png");
         tmpReader.readImage();
         charLumIndex[i][0] = i + charMinVal;
         charLumIndex[i][1] = tmpReader.getAvgLum();
      }

      //TODO: This stinks. But it works and only runs once. It'll do for now.
      int[] tmpArray = new int[2];
      for (int i = 0; i < charLumIndex.length; i++)
      {
         for(int j = i; j < charLumIndex.length; j++)
         {
            if (charLumIndex[j][1] < charLumIndex[i][1])
            {
               tmpArray[0] = charLumIndex[j][0];
               tmpArray[1] = charLumIndex[j][1];
               charLumIndex[j] = charLumIndex[i];
               charLumIndex[i] = tmpArray;
            }
         }
      }
   }

   /**Used to determine an appropriate character to use for a given luminosity value
    * 
    * @param lum must be 0-255, inclusive
    * @return char value appropriate for luminosity
    */
   public char getCharForLum(int lum)
   {
      //terrible integer math to adjust domain from 255 to whatever charLumIndex is
      return (char) charLumIndex[lum * (charLumIndex.length - 1) / 255][0];
   }
}
