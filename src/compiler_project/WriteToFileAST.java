package compiler_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class WriteToFileAST {

    static void writeToFileAST(String inputFileName, StringBuffer graphAST) throws IOException {
        int baseNameOffset = inputFileName.length() - 3;
        String baseName;
        if (inputFileName.substring(baseNameOffset).equals(".tl")) {
            baseName = inputFileName.substring(0, baseNameOffset);
        } else {
            throw new RuntimeException("inputFileName does not end in .tl");
        }

        String parseOutName = baseName + ".ast.dot";
        try {
            File file = new File(parseOutName);

            if (file.createNewFile()) {               
            } else {
                file.delete();               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedWriter out = new BufferedWriter(new FileWriter(parseOutName,false));  
        out.write(graphAST.toString());
        //out.newLine();
        out.write("}");
        out.flush();  
        out.close();

    }
}
