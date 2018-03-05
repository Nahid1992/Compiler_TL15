package compiler_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ReadInputFile {

    public static String[] readInputFile(String FileName) {
        String filecontent = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FileName));

            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (-1 != line.indexOf("%")) {
                    line = line.substring(0, line.indexOf("%"));
                }
                stringBuffer.append(line).append(" ");
            }

            filecontent = stringBuffer.toString();
            filecontent.trim();
            bufferedReader.close();
        } catch (IOException e) {            
            e.printStackTrace();
        }		  
        String symbol[] = filecontent.split("\\s+");
        
        return symbol;
    }
}
