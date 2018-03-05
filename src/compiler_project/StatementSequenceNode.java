package compiler_project;

import java.util.ArrayList;
import java.util.Collections;

public class StatementSequenceNode extends StatementNode {

    public boolean typeOk;
    BNFGrammarAST bgAST;
    IdentNode idNode;
    public ArrayList<StatementNode> statementSequence;

    public StatementSequenceNode() {
        this.typeOk = true;
        statementSequence = new ArrayList<StatementNode>();
    }

    public void addToStatementSequence(StatementNode stmt) {
        statementSequence.add(stmt);
        if (!TypeCheck()) {
            this.typeOk = false;
        }
    }

    public ArrayList<StatementNode> getAllStatementSequence() {
        return this.statementSequence;
    }

    public void drawStatetementSequenceNode(int seek_index) {
        String fillcolor;
        int graph_first_index;
        WriteIntNode writeInt;
        AssignmentNode asgn;
        IfStatementNode ifstmt;
        WhileStatementNode whilestmt;
        bgAST = BNFGrammarAST.getInstance();
        //bgAST.graphAST.append("n"+seek_index + " [label=\""+"decl list"+"\",fillcolor=\"/x11/white\",shape=box]").append(System.getProperty("line.separator"));
        graph_first_index = bgAST.graph_indexAST;
        idNode = new IdentNode("stmt list");
        idNode.drawIdentNode(seek_index, "/x11/white", "box");

        Collections.reverse(statementSequence);
        for (StatementNode stmt : statementSequence) {
            if (stmt.getClass().getName().equals("compiler_project.WriteIntNode")) {
                writeInt = (WriteIntNode) stmt;
                writeInt.drawWriteIntNode(graph_first_index);
            }

            if (stmt.getClass().getName().equals("compiler_project.AssignmentNode")) {
                asgn = (AssignmentNode) stmt;
                asgn.drawAssignmentNode(graph_first_index);
            }

            if (stmt.getClass().getName().equals("compiler_project.IfStatementNode")) {
                ifstmt = (IfStatementNode) stmt;
                ifstmt.drawIfStatementNode(graph_first_index);
            }

            if (stmt.getClass().getName().equals("compiler_project.WhileStatementNode")) {
                whilestmt = (WhileStatementNode) stmt;
                whilestmt.drawWhiltStatementNode(graph_first_index);
            }
        }
    }

    boolean TypeCheck() {
        boolean checkType = true;
        WriteIntNode writeInt;
        AssignmentNode asgn;
        IfStatementNode ifstmt;
        WhileStatementNode whilestmt;
        for (StatementNode stmt : statementSequence) {
            if (stmt.getClass().getName().equals("compiler_project.WriteIntNode")) {
                writeInt = (WriteIntNode) stmt;
                checkType = checkType && writeInt.typeOk;
            }

            if (stmt.getClass().getName().equals("compiler_project.AssignmentNode")) {
                asgn = (AssignmentNode) stmt;
                checkType = checkType && asgn.typeOk;
            }

            if (stmt.getClass().getName().equals("compiler_project.IfStatementNode")) {
                ifstmt = (IfStatementNode) stmt;
                checkType = checkType && ifstmt.typeOk;
            }

            if (stmt.getClass().getName().equals("compiler_project.WhileStatementNode")) {
                whilestmt = (WhileStatementNode) stmt;
                checkType = checkType && whilestmt.typeOk;
            }
        }
        return checkType;

    }

}
