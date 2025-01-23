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
 */
public class CharMapper {
   private Font usedFont;
   private int resolution;

   public CharMapper(Font fontToUse, int mappingResolution)
   {
      usedFont = fontToUse;
      resolution = mappingResolution;
   }

   public void imgGenerator(char charForProcess)
   {
      int charVal = charForProcess - 0;
      File outputFile = new File("./tmp/" + charVal + ".png");
      FontMetrics myMetrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics().getFontMetrics(usedFont);
      BufferedImage outImg = new BufferedImage(myMetrics.stringWidth(String.valueOf(charForProcess)), myMetrics.getHeight(), BufferedImage.TYPE_INT_ARGB);

      Graphics2D graphicOut = outImg.createGraphics();
      graphicOut.setFont(usedFont);
      graphicOut.setColor(Color.BLACK);
      
      graphicOut.drawString(String.valueOf(charForProcess), 0, myMetrics.getAscent()); 
      graphicOut.dispose();

      try {
         ImageIO.write(outImg, "png", outputFile);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
