package compiler_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

class Tokenizer {

    ArrayList<Token> TokenInfo = new ArrayList<Token>(); //Objects of all collected Tokens
    String filename;
    public Tokenizer(String inputFileName) throws FileNotFoundException, IOException {
        this.filename = inputFileName;
        String symbol[] = ReadInputFile.readInputFile(inputFileName);
        write_all_symbols(symbol);
        check_regularExpresion(symbol);
    }

    void check_regularExpresion(String[] symbol) {
        for (String lookup : symbol) {

            if (!RegexMatch.Regexcompare(lookup).equals("")) {

                if (RegexMatch.Regexcompare(lookup).contains("num") || RegexMatch.Regexcompare(lookup).contains("ident") || RegexMatch.Regexcompare(lookup).contains("boollit")) {
                    TokenInfo.add(new Token(RegexMatch.Regexcompare(lookup), lookup, RegexMatch.Regexcompare(lookup)+"("+ lookup+")"));
                } else if (RegexMatch.Regexcompare(lookup).contains("MULTIPLICATIVE") || RegexMatch.Regexcompare(lookup).contains("ADDITIVE") || RegexMatch.Regexcompare(lookup).contains("COMPARE") || RegexMatch.Regexcompare(lookup).contains("LP") || RegexMatch.Regexcompare(lookup).contains("RP")) {
                    TokenInfo.add(new Token(RegexMatch.Regexcompare(lookup), lookup, RegexMatch.Regexcompare(lookup)+"("+ lookup+")"));
                } else {
                    TokenInfo.add(new Token(RegexMatch.Regexcompare(lookup), lookup, RegexMatch.Regexcompare(lookup)));
                }

            } else {
                Compiler_Project.error_flag = 1;
                Find_Error_Line error_line = new Find_Error_Line();                
                int line = error_line.find_error_line(filename,lookup);
                String error_msg = "Lexical Error Found: \""+lookup+ "\"";
                Compiler_Project.error_list.add(error_msg);
                //System.err.println("Lexical Error Found => \""+lookup+ "\" in line : "+line);                
            }
        }
    }

    public ArrayList<Token> getTokens() {
        return TokenInfo;

    }

    void write_all_symbols(String[] symbol) throws IOException {
        //writes each words without spaces.
        String filename = "Symbol_Collection.txt";
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < symbol.length; i++) {
            outputWriter.write(symbol[i] + "");
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}
