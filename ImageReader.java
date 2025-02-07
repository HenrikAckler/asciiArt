import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

/**Reads an image's pixel data and writes the data to a file in a 2D array
 * //TODO: strip out file output from default behavior
 * @author Henrik Ackler
 */
public class ImageReader
{
   private String imgFileName;
   //private Path imgFilePath;// TODO: finish removing these
   //private File outputFile;
   private File inputfile;
   private int[][] outputLum;
   
   /**Constructor
    * 
    * @param filename sets filename of image to grab
    */
   public ImageReader(String filename)
   {
      imgFileName = filename;
      //imgFilePath = Path.of(imgFileName); //TODO: remove this?
      inputfile = new File(imgFileName);
   }

   /**Setter method for filename
    * 
    * @param filename sets file to grab
    */
   public void filePicker(String filename)
   {
      imgFileName = filename;
      //imgFilePath = Path.of(imgFileName);// TODO: remove this?
      inputfile = new File(imgFileName);
   }

   /**Creates output file
    * 
    * TODO: Add try-catch
    * Deprecating this for now.....TODO: Remove?
    */
   public void makeOutput(String filename)
   {
      //outputFile = new File("./tmp/" + filename + ".txt");
   }

   public void readImage() throws IOException
   {      
      BufferedImage inputImage = ImageIO.read(inputfile);
      outputLum = new int[inputImage.getHeight()][inputImage.getWidth()];

      for(int row = 0; row < inputImage.getHeight(); row++)
      {
         for(int col = 0; col < inputImage.getWidth(); col++)
         {
            int pixRGB = inputImage.getRGB(col, row);
            
            outputLum[row][col] = (((pixRGB >> 16) & 0xff) + ((pixRGB >> 8) & 0xff) + (pixRGB & 0xff)) / 3;

         }
      }
   }

   /**Retrieves 2d array output of luminosity values
    * @return
    */
   public int[][] getLumArray()
   {
      return outputLum;
   }

   /** Averages the luminosity of outputLum[][]
    *
    * @return average luminosity of img
    */
   public int getAvgLum()
   {
      int sum = 0;
      int count = 0;
      for (int row = 0; row < outputLum.length; row++)
      {
         for (int col = 0; col < outputLum[row].length; col ++)
         {
            sum += outputLum[row][col];
            count++;
         }
      }

      return sum / count;
   }
   
}