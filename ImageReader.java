import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

/**Reads an image's pixel data and writes the data to a file in a 2D array
 * 
 * @author Henrik Ackler
 */
public class ImageReader
{
   private String imgFileName;
   private Path imgFilePath;
   private File outputFile;
   private File inputfile;
   
   /**Constructor
    * 
    * @param filename sets filename of image to grab
    */
   public ImageReader(String filename)
   {
      imgFileName = filename;
      imgFilePath = Path.of(imgFileName);
      inputfile = new File(imgFileName);
   }

   /**Setter method for filename
    * 
    * @param filename sets file to grab
    */
   public void filePicker(String filename)
   {
      imgFileName = filename;
      imgFilePath = Path.of(imgFileName);
      inputfile = new File(imgFileName);
   }

   /**Creates output file
    * 
    * TODO: Add try-catch
    */
   public void makeOutput(String filename)
   {
      outputFile = new File("./tmp/" + filename + ".txt");
   }

   public void readImage()
   {
      int luminosity;
      Color pixelColor;
      

      try {
         BufferedImage inputImage = ImageIO.read(inputfile);
         FileWriter myWriter = new FileWriter(outputFile);

         for(int row = 0; row < inputImage.getHeight(); row++)
         {
            for(int col = 0; col < inputImage.getWidth(); col++)
            {
               pixelColor = new Color(inputImage.getRGB(col, row));
               luminosity = ( pixelColor.getRed() + pixelColor.getBlue() + pixelColor.getGreen() ) / 3;

               if(luminosity < 100)
               {
                  myWriter.write(luminosity + "  ");
               }else if(luminosity < 10)
               {
                  myWriter.write(luminosity + "  ");
               }else
               {
                  myWriter.write(luminosity + " ");
               }
               
            }
            myWriter.write("\n");
         }
         myWriter.close();

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   

}