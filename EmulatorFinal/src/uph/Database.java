package uph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Database {
	public static String checkDatabase(int index,String filePath) throws IOException{
	    String line = null;
        // FileReader reads text files in the default encoding.
        FileReader readFile = new FileReader(filePath);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(readFile);
        for (int i=0; i<index;i++){
            line=bufferedReader.readLine();
        }
        // Always close files.
        bufferedReader.close();
        return line;
	}
	public static int countDatabase(String filePath) throws IOException{
		// FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(filePath);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int count=0;
        while (bufferedReader.readLine() != null){
            count++;
        }   

        // Always close files.
        bufferedReader.close();
        return count;
	}
	public static void databaseWriter(String input,String filePath) throws IOException{
	
		try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true))){
		out.write(input);
		out.write("\n");
		}
		catch (IOException ioe){
			 System.out.println("Exception occurred:");
	    	 ioe.printStackTrace();
		}
	}
	public static void databaseRemove (String input,String filePath){
		   new Database().removeLineFromFile(filePath,input );
	}
	public static void databaseRemoveAll (String filePath){

	    try {
	 
	      File inFile = new File(filePath);
	      
	      if (!inFile.isFile()) {
	        System.out.println("Parameter is not an existing file");
	        return;
	      }
	       
	      //Construct the new file that will later be renamed to the original filename. 
	      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
	      
	      BufferedReader br = new BufferedReader(new FileReader(filePath));
	      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
	      
	      String line = null;
	 
	      //Read from the original file and write to the new 
	      //unless content matches data to be removed.
	      while ((line = br.readLine()) != null) {
	    	  if (!line.trim().equals(line)) {
	          pw.println(line);
	          pw.flush();
	    	  }
	      }
	      pw.close();
	      br.close();
	      
	      //Delete the original file
	      if (!inFile.delete()) {
	        System.out.println("Could not delete file");
	        return;
	      } 
	      
	      //Rename the new file to the filename the original file had.
	      if (!tempFile.renameTo(inFile))
	        System.out.println("Could not rename file");
	      
	    }
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex) {
	      ex.printStackTrace();
	    }
	}
	/*
	 * Special method
	 */
	 public void removeLineFromFile(String file, String lineToRemove) {
		 
		    try {
		 
		      File inFile = new File(file);
		      
		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }
		       
		      //Construct the new file that will later be renamed to the original filename. 
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		      
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		      
		      String line = null;
		 
		      //Read from the original file and write to the new 
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {
		        
		        if (!line.trim().equals(lineToRemove)) {
		 
		          pw.println(line);
		          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();
		      
		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      } 
		      
		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");
		      
		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		  }
}
