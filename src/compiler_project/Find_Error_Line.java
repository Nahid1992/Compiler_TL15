/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Find_Error_Line {
    
    int find_error_line(String filename, String lookup) {        
        int count_line = 0;        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                count_line++;
                String [] words = line.split("[\\s']");
                for(String s:words){
                    if(s.contains(lookup)) return count_line;
                }
            }            
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
