package compiler_project;

import java.util.ArrayList;

class Parser {
    
    ArrayList<Token> token_list = new ArrayList<Token>();
    BNFGrammarAST bnfGrammarAST = new BNFGrammarAST();

    public Parser(String inputFileName,ArrayList<Token> token_list) {
        this.token_list = token_list;
        bnfGrammarAST.BNFGrammarASTStructure(inputFileName,this.token_list);                
    }
    
}
