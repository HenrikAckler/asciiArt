
public class asciiGenerator {
   private int[][] imgLuminosity;
   private char[][] asciiArray;
   private CharMapper asciiMapper;
   private StringBuilder output;

   public asciiGenerator(int[][] imgLuminosity, CharMapper asciiMapper)
   {
      this.imgLuminosity = imgLuminosity;
      this.asciiMapper = asciiMapper;
      asciiArray = new char[imgLuminosity.length][imgLuminosity[0].length];
      output = new StringBuilder();
   }

   /**Creates char array of appropriate ascii symbols and returns as 2d array
    * Also creates string that is same as 2d array
    * @return
    */
   public char[][] getAsciiArt()
   {
      for(int row = 0; row < imgLuminosity.length; row++)
      {
         for(int col = 0; col < imgLuminosity[row].length; col++)
         {
            asciiArray[row][col] = asciiMapper.getCharForLum(imgLuminosity[row][col]);
            output.append(asciiMapper.getCharForLum(imgLuminosity[row][col]));

            if( col % 2 == 0)
            {
               output.append(" ");
            }
         }
         output.append("\n");
      }

      return asciiArray;
   }

   /** returns the output string. 
    * getAsciiArt() MUST BE RAN BEFORE
    * TODO: fix this garabge dependency issue
    * @return string with ascii art in it
    */
   public String getAsciiArtString()
   {
      return output.toString();
   }

}
