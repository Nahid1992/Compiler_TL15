package compiler_project;

class ProgramNode {

    public DeclarationsNode declarations;
    public StatementSequenceNode statementSequence;
    public boolean typeOk;

    BNFGrammarAST bAST;

    public ProgramNode(DeclarationsNode decl, StatementSequenceNode stmt_seq) {

        this.declarations = decl;
        this.statementSequence = stmt_seq;
        if (TypeCheck()) {
            this.typeOk = true;
        } else {
            this.typeOk = false;
        }
    }

    public void drawProgramNode(int seek_index) {
        bAST = BNFGrammarAST.getInstance();
        String fillcolor = "/x11/white";
        if (!TypeCheck()) {
            fillcolor = "/pastel13/1";
        }
        bAST.graphAST.append("n" + seek_index + " [label=\"" + "program" + "\",fillcolor=\"" + fillcolor + "\",shape=box]").append(System.getProperty("line.separator"));
        int graph_first_index = bAST.graph_indexAST;
        bAST.graph_indexAST++;
        declarations.drawDecalrationsNode(graph_first_index);
        statementSequence.drawStatetementSequenceNode(graph_first_index);
    }

    boolean TypeCheck() {
        boolean checkType;
        checkType = declarations.typeOk && statementSequence.typeOk;
        return checkType;
    }
}
