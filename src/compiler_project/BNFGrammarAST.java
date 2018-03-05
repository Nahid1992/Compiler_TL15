package compiler_project;

import java.io.IOException;
import java.util.ArrayList;

class BNFGrammarAST {

    public static int current_index;
    public static int graph_index;
    public static int pre_graph_index;
    public static int current_indexAST;
    public static int graph_indexAST;
    public static int pre_graph_indexAST;
    public static int current_blockNum = 0;
    public static int ilocRegister;
    private static BNFGrammarAST bnfAST = null;

    Token sym;
    static StringBuffer graph;
    static StringBuffer graphAST;
    static StringBuffer graphILOC;
    static StringBuffer graphMIPS;
    public ArrayList<Token> tokens;

    String OutFileName;
    String InputFileName;

    public static BNFGrammarAST getInstance() {
        if (bnfAST == null) {
            bnfAST = new BNFGrammarAST();
        }
        return bnfAST;
    }

    void BNFGrammarASTStructure(String inputFileName, ArrayList<Token> token_list) {
        graph = new StringBuffer();
        this.tokens = token_list;
        graphILOC = new StringBuffer();
        graphAST = new StringBuffer();
        graphMIPS = new StringBuffer();
        
        graphAST.append("digraph AST {").append(System.getProperty("line.separator"));
        graphAST.append("\t ordering=out;").append(System.getProperty("line.separator"));
        graphAST.append(" node [shape = box, style = filled];").append(System.getProperty("line.separator"));

        graphILOC.append("digraph ilocTree {").append(System.getProperty("line.separator"));
        graphILOC.append("node [shape = none];").append(System.getProperty("line.separator"));
        graphILOC.append("edge [tailport = s];").append(System.getProperty("line.separator"));
        graphILOC.append("entry").append(System.getProperty("line.separator"));
        graphILOC.append("subgraph cluster {").append(System.getProperty("line.separator"));
        graphILOC.append("color=\"/x11/white\"").append(System.getProperty("line.separator"));
        graphMIPS.append(".data").append(System.getProperty("line.separator"));
        current_index = 0;
        graph_indexAST = 1;
        pre_graph_indexAST = 1;
        pre_graph_index = 1;

        BlockNode block = new BlockNode();
        ProgramNode pr;
        if ((pr = program()) != null) {
            try {
                pr.drawProgramNode(pre_graph_index);
                WriteToFileAST.writeToFileAST(inputFileName, graphAST);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("AST .dot file created Succesfully");
          
        } else {
            System.err.println("ERROR: Syntex error found");
            System.err.println("ERROR: Could not create DOT file");
        }

        if (pr.typeOk) {
            System.out.println("No Type Error Found.");
            //MIPS            
            graphMIPS.append("newline:	.asciiz \"\\n\" ").append(System.getProperty("line.separator"));
            graphMIPS.append(".text").append(System.getProperty("line.separator"));
            graphMIPS.append(".globl main").append(System.getProperty("line.separator"));
            graphMIPS.append("main:").append(System.getProperty("line.separator"));
            graphMIPS.append("li $fp, 0x7ffffffc").append(System.getProperty("line.separator"));

            //ILOC
            block = BuildILOC.ILOCDeclarationsNode(block, pr.declarations);
            BlockNode exit = BuildILOC.ILOCStatementSequence(block, pr.statementSequence);
            try {
                BNFGrammarAST.graphILOC.append("<tr><td align=\"left\">exit</td><td align=\"left\"></td><td align=\"left\"></td></tr>");
                BNFGrammarAST.graphILOC.append("</table>>,fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
                graphILOC.append("}").append(System.getProperty("line.separator"));
                graphILOC.append("entry -> n" + block.blkNum).append(System.getProperty("line.separator"));
                graphILOC.append("n" + exit.blkNum + "-> exit").append(System.getProperty("line.separator"));

                WriteToFileILOCCFG.writeToFileILOCCFG(inputFileName, graphILOC);
            } catch (IOException e) {                
                e.printStackTrace();
            }

            try {
                graphMIPS.append("# exit ").append(System.getProperty("line.separator"));
                graphMIPS.append("exit: ").append(System.getProperty("line.separator")); //added for exit
                exit.addMipsInstruction(new MIPSInstructionFromat("li", "10", null, "$v0"));
                exit.addMipsInstruction(new MIPSInstructionFromat("syscall", null, null, null));
                graphMIPS.append("li $v0, 10").append(System.getProperty("line.separator"));
                graphMIPS.append("syscall").append(System.getProperty("line.separator"));
                WriteToFileMIPS.writeToFileMIPS(inputFileName, graphMIPS);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.err.println("Type Error Found: Please check the AST .dot file/image for detail.");
            System.err.println("Type Error Found: Could not create CFG file.");
            System.err.println("Type Error Found: Could not create MIPS file.");
        }
    }

    public String peek() {
        sym = tokens.get(current_index);
        if (!sym.value.equals("")) {
            return sym.type;
        }
        return "";
    }

    public boolean peekValue(String symbol) {
        sym = tokens.get(current_index);
        if (sym.type.equals(symbol)) {
            current_index++;
            return true;
        }
        return false;
    }

    public ProgramNode program() {
        DeclarationsNode decl;
        StatementSequenceNode stmt_seq;
        int graph_first_index = graph_index;
        boolean checkProgram;

        if (peekValue("PROGRAM") && ((decl = declarations()) != null) && peekValue("BEGIN") && ((stmt_seq = statementSequence()) != null) && peekValue("END")) {
            return new ProgramNode(decl, stmt_seq);
        } else {
            System.err.println("ERROR: Syntex Error in program rule");
            return null;
        }
    }

    public DeclarationsNode declarations() {
        TypeNode typeNode;
        boolean checkProgram;
        int declarations_graph_index;
        int graph_first_index = graph_index;
        String idName;

        if (peek().equals("BEGIN")) {
            return DeclarationsNode.getInsatnce();
        }

        if (peekValue("VAR") && (!(idName = nextValue("ident")).equals("")) && peekValue("AS") && ((typeNode = type()) != null) && peekValue("SC") && (declarations() != null)) {
            IdentNode idNode = new IdentNode(idName, typeNode.typeNode);
            DeclaredVar de_var = new DeclaredVar(idNode, typeNode);
            DeclarationsNode.getInsatnce().addToDeclarationsNode(de_var);
            return DeclarationsNode.getInsatnce();
        } else {
            System.err.println("ERROR: Syntex Error in declartions rule");
            return null;
        }
    }

    public StatementSequenceNode statementSequence() {
        boolean checkProgram;
        int declarations_graph_index;
        int graph_first_index = graph_index;
        StatementSequenceNode stmtSeq = null;
        StatementNode stmt = null;

        if ((peek().equals("ELSE")) || (peek().equals("END"))) {
            //System.out.println(peek());
            return new StatementSequenceNode();
        } else if (((stmt = statement()) != null) && peekValue("SC") && ((stmtSeq = statementSequence()) != null)) {
            stmtSeq.addToStatementSequence(stmt);
            return stmtSeq;
        } else {
            System.err.println("Error: Syntex Error in statementSequence rule");
            return null;
        }
    }

    public TypeNode type() {
        boolean checkInt;
        boolean checkBool;

        checkInt = peek().equals("INT") && peekValue("INT");
        checkBool = peek().equals("BOOL") && peekValue("BOOL");
        if (checkInt) {
            return new TypeNode("INT");
        } else if (checkBool) {
            return new TypeNode("BOOL");
        } else {
            System.err.println("ERROR: Syntex Error in type rule");
            return null;
        }
    }

    public StatementNode statement() {
        if (peek().equals("ident")) {
            return assignment();
        } else if (peek().equals("IF")) {
            return ifStatement();
        } else if (peek().equals("WHILE")) {
            return whileStatement();
        } else if (peek().equals("WRITEINT")) {
            return writeInt();
        } else {
            System.out.println("Syntex Error in statement rule");
            return null;
        }
    }

    public StatementNode assignment() {
        boolean checkAssignment;
        String idName;
        String asgn = null;
        ASTtree stmt = null;
        String ident;

        IdentNode idNode;
        AssignmentNode asgnNode = null;
        AdditiveNode op;
        checkAssignment = (!((idName = nextValue("ident")).equals(""))) && (!((asgn = nextValue("ASGN")).equals(""))) && ((stmt = assignmentPrime()) != null);
        //System.out.println(stmt);
        op = new AdditiveNode(asgn, "ASGN");
        ident = HashTableforSymbol.getInsatnce().getIdentName(idName);
        idNode = new IdentNode(idName, ident);
        //System.out.println("CHECK " + idNode.idName);

        if (checkAssignment) {
            if (stmt.getClass().getName().equals("compiler_project.ExpressionNode")) {
                ExpressionNode exp = (ExpressionNode) stmt;
                asgnNode = new AssignmentNode(idNode, op, exp);
            }
            if (stmt.getClass().getName().equals("compiler_project.IdentNode")) {
                IdentNode identifier = (IdentNode) stmt;
                asgnNode = new AssignmentNode(idNode, op, identifier);
                //System.out.println(idNode.idName+ " " + op.opName + " " + identifier.idName);
            }
            return asgnNode;
        } else {
            System.err.println("Syntex Error in assignment rule");
            return null;
        }
    }

    public ASTtree assignmentPrime() {
        boolean checkAssignmentPrime;
        IdentNode idNode = null;
        if (peek().equals("ident") || peek().equals("num") || peek().equals("boollit") || peek().equals("LP")) {
            return expression();
        } else if (peek().equals("READINT") && peekValue("READINT")) {
            idNode = new IdentNode("READINT", "INT");
            return idNode;
        } else {
            System.err.println("Syntex Error in assignmentPrime rule");
            return null;
        }
    }

    public IfStatementNode ifStatement() {
        boolean checkifStatement;
        ExpressionNode expNode = null;
        StatementSequenceNode stmtSeq = null;
        StatementSequenceNode elsestmtSeq = null;
        IfStatementNode ifstmtNode = null;
        checkifStatement = peekValue("IF") && ((expNode = expression()) != null) && peekValue("THEN") && ((stmtSeq = statementSequence()) != null) && ((elsestmtSeq = elseClause()) != null) && peekValue("END");
        if (checkifStatement) {
            ifstmtNode = new IfStatementNode(expNode, stmtSeq, elsestmtSeq);
            return ifstmtNode;
        } else {
            System.err.println("ERROR: Syntex Error in ifStatement rule");
            return null;
        }
    }

    public StatementSequenceNode elseClause() {
        boolean checkelseClause;
        int elseClause_graph_index;
        int graph_first_index = graph_index;

        StatementSequenceNode stmtSeq = null;
        StatementSequenceNode stmt = null;

        if (peek().equals("END")) {
            stmt = new StatementSequenceNode();
            return stmt;
        } else if (peekValue("ELSE") && ((stmtSeq = statementSequence()) != null)) {
            return stmtSeq;
        } else {
            System.err.println("ERROR: Syntex Error in elseClause rule");
            return null;
        }
    }

    public WhileStatementNode whileStatement() {
        boolean checkwhileStatement;
        int graph_first_index = graph_index;
        ExpressionNode expNode = null;
        StatementSequenceNode stmtSeqNode = null;
        WhileStatementNode whilestmtNode = null;

        checkwhileStatement = peekValue("WHILE") && ((expNode = expression()) != null) && peekValue("DO") && ((stmtSeqNode = statementSequence()) != null) && peekValue("END");
        if (checkwhileStatement) {
            whilestmtNode = new WhileStatementNode(expNode, stmtSeqNode);
            return whilestmtNode;
        } else {
            System.err.println("ERROR: Syntex Error in whileStatement rule");
            return null;
        }
    }

    public WriteIntNode writeInt() {
        boolean checkwriteInt;
        int graph_first_index = graph_index;
        ExpressionNode expNode = null;
        WriteIntNode writeInt = null;
        checkwriteInt = peekValue("WRITEINT") && ((expNode = expression()) != null);
        if (checkwriteInt) {
            writeInt = new WriteIntNode(expNode);
            return writeInt;
        } else {
            System.err.println("ERROR: Syntex Error in writeInt rule");
            return null;
        }
    }

    public ExpressionNode expression() {
        boolean checkexpression;
        //int elseClause_graph_index;
        int graph_first_index = graph_index;

        SimpleExpressionNode smExpNode = null;
        ExpressionPrimeNode exPrimeNode = null;
        ExpressionNode expNode = null;

        checkexpression = ((smExpNode = simpleExpression()) != null) && ((exPrimeNode = expressionPrime()) != null);

        if (checkexpression) {
            expNode = new ExpressionNode(smExpNode, exPrimeNode.opNodesm, exPrimeNode.smExpressionNode);
            return expNode;
        } else {
            System.err.println("ERROR: Syntex Error in expression rule");
            return null;
        }
    }

    public ExpressionPrimeNode expressionPrime() {
        boolean checkexpressionPrime;
        SimpleExpressionNode smexpNode = null;
        String opNode;
        ExpressionPrimeNode exPrimeNode = null;
        AdditiveNode op;
        if (peek().equals("THEN") || peek().equals("DO") || peek().equals("SC") || peek().equals("RP")) {
            exPrimeNode = new ExpressionPrimeNode();
            return exPrimeNode;
        } else if ((!((opNode = nextValue("COMPARE")).equals(""))) && ((smexpNode = simpleExpression()) != null)) {
            op = new AdditiveNode(opNode, "COMPARE");
            exPrimeNode = new ExpressionPrimeNode(smexpNode, op);
            return exPrimeNode;
        } else {
            System.err.println("Syntex Error in expressionPrime rule");
            return null;
        }
    }

    public SimpleExpressionNode simpleExpression() {
        boolean checksimpleExpression;
        int graph_first_index = graph_index;
        TermNode termNode = null;
        SimpleExpressionPrimeNode smExpPrimeNode = null;
        SimpleExpressionNode smExpNode = null;

        checksimpleExpression = ((termNode = term()) != null) && ((smExpPrimeNode = simpleExpressionPrime()) != null);
        if (checksimpleExpression) {
            smExpNode = new SimpleExpressionNode(termNode, smExpPrimeNode.opTerm, smExpPrimeNode.termNode);
            return smExpNode;
        } else {
            System.err.println("ERROR: Syntex Error in simpleExpression rule");
            return null;
        }
    }

    public SimpleExpressionPrimeNode simpleExpressionPrime() {
        boolean checksimpleExpressionPrime;
        String op;
        TermNode termNode = null;
        SimpleExpressionPrimeNode smExpNode = null;
        AdditiveNode opNode = null;
        SimpleExpressionNode smEx = null;

        if (peek().equals("THEN") || peek().equals("DO") || peek().equals("SC") || peek().equals("RP") || peek().equals("COMPARE")) {
            smExpNode = new SimpleExpressionPrimeNode();
            return smExpNode;
        } else if ((!((op = nextValue("ADDITIVE")).equals(""))) && ((termNode = term()) != null)) {
            opNode = new AdditiveNode(op, "ADDITIVE");
            smExpNode = new SimpleExpressionPrimeNode(opNode, termNode);
            //System.out.println("CHECK: "+opNode.opName+" "+termNode.fNode1.numNodeF.numVal);
            return smExpNode;
        } else {
            System.err.println("ERROR: Syntex Error in simpleExpressionPrime rule");
            return null;
        }
    }

    public TermNode term() {
        boolean checkterm;
        FactorNode factNode = null;
        TermPrimeNode termPNode = null;
        TermNode termNode = null;

        checkterm = ((factNode = factor()) != null) && ((termPNode = termPrime()) != null);
        if (checkterm) {
            termNode = new TermNode(factNode, termPNode.opNodePTerm, termPNode.factNodePTerm);
            //System.out.println(factNode.idFactorF.idName);
            return termNode;
        } else {
            System.err.println("ERROR: Syntex Error in term rule");
            return null;
        }
    }

    public TermPrimeNode termPrime() {
        boolean checktermPrime;
        String op;
        FactorNode factNode = null;
        TermPrimeNode termPNode = null;
        AdditiveNode opNode = null;

        if (peek().equals("THEN") || peek().equals("DO") || peek().equals("SC") || peek().equals("RP") || peek().equals("COMPARE") || peek().equals("ADDITIVE")) {
            termPNode = new TermPrimeNode();
            return termPNode;
        } else if ((!((op = nextValue("MULTIPLICATIVE")).equals(""))) && ((factNode = factor()) != null)) {
            opNode = new AdditiveNode(op, "MULTIPLICATIVE");
            termPNode = new TermPrimeNode(opNode, factNode);
            return termPNode;
        } else {
            System.err.println("ERROR: Syntex Error in termPrime rule");
            return null;
        }
    }

    public FactorNode factor() {
        boolean checkfactor;
        int graph_first_index = graph_index;

        ExpressionNode expNode;
        FactorNode factNode = null;
        IdentNode idNode = null;
        NumNode numNode = null;
        BoollitNode boolNode = null;
        String idName, idType;

        if (peek().equals("LP") && peekValue("LP") && ((expNode = expression()) != null) && peekValue("RP")) {
            factNode = new FactorNode(expNode);
            return factNode;
        } else if (peek().equals("ident") && (!(idName = nextValue("ident")).equals(""))) {
            idType = HashTableforSymbol.getInsatnce().getIdentName(idName);
            idNode = new IdentNode(idName, idType);
            factNode = new FactorNode(idNode);
            return factNode;
        } else if (peek().equals("num") && (!(idName = nextValue("num")).equals(""))) {
            numNode = new NumNode(Long.parseLong(idName));
            factNode = new FactorNode(numNode);
            return factNode;
        } else if (peek().equals("boollit") && (!(idName = nextValue("boollit")).equals(""))) {
            boolNode = new BoollitNode(Boolean.parseBoolean(idName));
            factNode = new FactorNode(boolNode);
            return factNode;
        } else {
            System.err.println("ERROR: Syntex Error in factor rule");
            return null;
        }
    }

    public boolean next(String symbol, int seek_index) {
        sym = tokens.get(current_index);
        if (sym.type.equals(symbol)) {
            current_index++;
            graph.append("n" + graph_index + " [label=\"" + sym.stringvalue + "\",fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
            graph.append("n" + seek_index + " -> n" + graph_index).append(System.getProperty("line.separator"));
            graph_index++;
            pre_graph_index = graph_index - 1;
            return true;
        } else {
            return false;
        }
    }

    public String nextValue(String symbol) {
        sym = tokens.get(current_index);
        if (sym.type.equals(symbol)) {
            current_index++;
            return sym.value;
        } else {
            return "";
        }
    }

}
