
package compiler_project;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileILOCCFG {
public static void writeToFileILOCCFG(String pFilename, StringBuffer pData) throws IOException { 
	
        int baseNameOffset = pFilename.length() - 3;
        
        String baseName;
        if (pFilename.substring(baseNameOffset).equals(".tl"))
            baseName = pFilename.substring(0,baseNameOffset);
        else
            throw new RuntimeException("inputFileName does not end in .tl");

        String parseOutName = baseName + ".3A.cfg.dot";
		
		try {
			 
		      File file = new File(parseOutName);
	 
		      if (file.createNewFile()){
		        //System.out.println("File is created!");
		      }else{
		    	  file.delete();
		       // System.out.println("File already exists.");
		      }
	 
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
        BufferedWriter out = new BufferedWriter(new FileWriter(parseOutName,false));  
        out.write(pData.toString());
        //out.newLine();
        out.write("}");
        out.flush();  
        out.close();
        System.out.println("CFG .dot file created successfully");
	}
}
