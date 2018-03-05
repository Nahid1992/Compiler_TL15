package compiler_project;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Scanner {

    public ArrayList<Token> token_list;
    String name;

    Scanner(String inputFileName) throws FileNotFoundException, IOException {
        this.name = inputFileName;
        Tokenizer tokenizer = new Tokenizer(inputFileName);
        token_list = tokenizer.getTokens();
        write_all_tokens();
        create_token_file();
        
        Parser parser = new Parser(inputFileName,token_list);
    }

    void write_all_tokens() throws IOException {
        //writes converted tokens.
        String filename = "Token_Collection.txt";
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < token_list.size(); i++) {
            outputWriter.write(token_list.get(i).type + " <=> " + token_list.get(i).value);
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }

    private void create_token_file() throws IOException {
        //Creating .tok file.
        int baseNameOffset = name.length() - 3;
        String filename = name.substring(0, baseNameOffset);
        filename = filename + ".tok";

        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(filename));
        if (Compiler_Project.error_flag == 0) {
            for (int i = 0; i < token_list.size(); i++) {
                outputWriter.write(token_list.get(i).stringvalue);
                outputWriter.newLine();
            }
            System.out.println("Token file created successfully.");
        } else {
            for (int i = 0; i < Compiler_Project.error_list.size(); i++) {
                outputWriter.write(Compiler_Project.error_list.get(i));
                outputWriter.newLine();
            }
            System.err.println("Lexical Error Found. Please check the .tok file for detailed information.");
        }
        outputWriter.flush();
        outputWriter.close();
    }
}
