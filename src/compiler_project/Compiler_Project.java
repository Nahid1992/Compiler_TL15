package compiler_project;
import java.io.IOException;
import java.util.ArrayList;

public class Compiler_Project {
    
    public static int error_flag = 0;
    public static ArrayList<String> error_list = new ArrayList<String>();
    public static void main(String[] args) throws IOException{
                
        String inputFileName = args[0];
        //String inputFileName = "test/simple1.tl";
        
        int baseNameOffset = inputFileName.length() - 3;
        
        String baseName;
        if (inputFileName.substring(baseNameOffset).equals(".tl"))
            baseName = inputFileName.substring(0,baseNameOffset);
        else
            throw new RuntimeException("inputFileName does not end in .tl");

	System.out.println("Input file: " + inputFileName);       
        Scanner scanner = new Scanner(inputFileName);        
        
    }    
}
