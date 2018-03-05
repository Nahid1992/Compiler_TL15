/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_project;

public class WhileStatementNode extends StatementNode {

    public ExpressionNode whileExpressionNode;
    public StatementSequenceNode whileStmtSeqNode;
    BNFGrammarAST bgAST;
    public boolean typeOk;

    public WhileStatementNode(ExpressionNode exp, StatementSequenceNode stmtSeq) {
        this.whileExpressionNode = exp;
        this.whileStmtSeqNode = stmtSeq;
        if (TypeCheck()) {
            this.typeOk = true;
        } else {
            this.typeOk = false;
        }
    }

    public void drawWhiltStatementNode(int seek_index) {
        String fillcolor = "/x11/white";
        if (!TypeCheck()) {
            fillcolor = "/pastel13/1";
        }
        String shape = "box";
        bgAST = BNFGrammarAST.getInstance();
        //bgAST.graphAST.append("n"+seek_index + " [label=\""+"decl list"+"\",fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        int graph_first_index = bgAST.graph_indexAST;
        //if(whileExpressionNode!=null){
        bgAST.graphAST.append("n" + graph_first_index + " [label=\"" + "while" + "\",fillcolor=\"" + fillcolor + "\",shape=" + shape + "]").append(System.getProperty("line.separator"));
        //int graph_first_index = bgAST.graph_indexAST;
        bgAST.graph_indexAST++;
        bgAST.graphAST.append("n" + seek_index + " -> n" + graph_first_index).append(System.getProperty("line.separator"));
        whileExpressionNode.drawExpressionNode(graph_first_index);
        if (whileStmtSeqNode != null) {
            whileStmtSeqNode.drawStatetementSequenceNode(graph_first_index);
        }
    }

    boolean TypeCheck() {
        boolean checkType = false;
        if (whileExpressionNode != null) {
            checkType = whileExpressionNode.typeOk && whileStmtSeqNode.typeOk;
        }
        return checkType;

    }
}
